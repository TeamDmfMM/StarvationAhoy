package dmfmm.starvationahoy.base;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * File created by mincrmatt12 on 12/24/2017.
 * Originally written for StarvationAhoy.
 * <p>
 * See LICENSE.txt for license information.
 */

@IFMLLoadingPlugin.Name("StarvationAhoyRecipeBookHack")
@IFMLLoadingPlugin.DependsOn("forge")
@IFMLLoadingPlugin.SortingIndex(1001)
public class RecipeBookTransformerPlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] {RecipeBookTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static class RecipeBookTransformer implements IClassTransformer {

        @Override
        public byte[] transform(String name, String transformedName, byte[] basicClass) {
            if (transformedName.equals("net.minecraft.client.gui.recipebook.GuiRecipeBook")) {
                return transformRecipeBook(basicClass);
            }
            else if (transformedName.equals("net.minecraft.client.util.RecipeBookClient")) {
                return transformRecipeBookClient(basicClass);
            }

            return basicClass;
        }

        private byte[] transformRecipeBookClient(byte[] clazz) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(clazz);
            classReader.accept(classNode, 0);

            for (MethodNode node : classNode.methods) {
                if (!node.name.equals("getItemStackTab")) continue;

                InsnList insnList = node.instructions;
                AbstractInsnNode aStorePos = null;

                for (AbstractInsnNode insn : insnList.toArray()) {
                    if (insn.getOpcode() == Opcodes.ASTORE) {
                        aStorePos = insn;
                        break;
                    }
                }

                if (aStorePos == null) break;

                while (aStorePos.getOpcode() != Opcodes.ALOAD) {
                    aStorePos = aStorePos.getNext();
                }

                if (((VarInsnNode) (aStorePos)).var != 1) break;

                LabelNode toJumpTo = ((JumpInsnNode) (aStorePos.getNext().getNext())).label;
                if (toJumpTo == null) break;

                insnList.insertBefore(aStorePos, getNewInstructionsForCondition(toJumpTo));
            }

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        private static byte[] transformRecipeBook(byte[] clazz) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(clazz);
            classReader.accept(classNode, 0);

            for (MethodNode node : classNode.methods) {
                if (!node.name.equals("<init>")) continue;
                InsnList insnList = node.instructions;
                int currentLength = 0;
                AbstractInsnNode beginPos = null;

                for (AbstractInsnNode insn : insnList.toArray()) {
                    if (insn.getOpcode() == Opcodes.ICONST_5 ||
                            insn.getOpcode() == Opcodes.BIPUSH) {
                        if (insn.getNext().getOpcode() == Opcodes.ANEWARRAY) {
                            beginPos = insn;

                            switch (insn.getOpcode()) {
                                case Opcodes.ICONST_5:
                                    currentLength = 5;
                                    break;
                                case Opcodes.BIPUSH:
                                    currentLength = ((IntInsnNode)(insn)).operand;
                                    break;
                                default:
                                    break;
                            }
                            break;
                        }
                    }
                }
                if (currentLength == 0) { System.out.println("Error loading RecipeBookTransformer: couldn't find init location!"); break; }

                AbstractInsnNode current = beginPos.getNext();
                insnList.set(beginPos, new IntInsnNode(Opcodes.BIPUSH, currentLength + 1));
                while (current.getOpcode() != Opcodes.INVOKESTATIC) {
                    current = current.getNext();
                }
                MethodInsnNode invokeStaticNode = (MethodInsnNode) current;
                if (!invokeStaticNode.name.equals("newArrayList")) System.out.println("rror loading RecipeBookTransformer: bad staticinfoke");
                insnList.insertBefore(invokeStaticNode, getNewInstructionsForRecipeBook(currentLength));
            }

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        private static InsnList getNewInstructionsForRecipeBook(int index)  {
            InsnList insnList = new InsnList();
            insnList.add(new InsnNode(Opcodes.DUP));
            if (index == 5) {
                insnList.add(new InsnNode(Opcodes.ICONST_5));
            }
            else {
                insnList.add(new IntInsnNode(Opcodes.BIPUSH, index));
            }
            insnList.add(new TypeInsnNode(Opcodes.NEW, "net/minecraft/client/gui/recipebook/GuiButtonRecipeTab"));
            insnList.add(new InsnNode(Opcodes.DUP));
            insnList.add(new InsnNode(Opcodes.ICONST_0));
            insnList.add(new FieldInsnNode(Opcodes.GETSTATIC,  "dmfmm/starvationahoy/core/SATabs", "INSTANCE", "Ldmfmm/starvationahoy/core/SATabs;"));
            insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "net/minecraft/client/gui/recipebook/GuiButtonRecipeTab", "<init>", "(ILnet/minecraft/creativetab/CreativeTabs;)V", false));
            insnList.add(new InsnNode(Opcodes.AASTORE));
            return insnList;
        }

        private static InsnList getNewInstructionsForCondition(LabelNode theLabel) {
            InsnList insnList = new InsnList();

            insnList.add(new VarInsnNode(Opcodes.ALOAD, 1));
            insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, "dmfmm/starvationahoy/core/SATabs", "INSTANCE", "Ldmfmm/starvationahoy/core/SATabs;"));
            insnList.add(new JumpInsnNode(Opcodes.IF_ACMPEQ, theLabel));

            return insnList;
        }
    }
}
