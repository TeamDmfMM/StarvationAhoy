package dmfmm.StarvationAhoy.CropWash;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.CropWash.Block.BlockCropWasher;
import dmfmm.StarvationAhoy.CropWash.Block.tilentity.TileEntityCropWasher;
import dmfmm.StarvationAhoy.CropWash.item.CropItemLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class ModuleCropWash {

    public static CropItemLoader cropItemLoader;


    public static Block blockCropWasher;

    public static DirtyBlocks d;

    public static void preinit() {

        if (CRef.useCropwash() == false){
            return;
        }

        cropItemLoader = new CropItemLoader();
        blockCropWasher = new BlockCropWasher();
    }

    public static void init(Side side) {

        if (CRef.useCropwash() == false){
            return;
        }

        cropItemLoader.load();
        MinecraftForge.EVENT_BUS.register(new Events_CropWash());
        d = new DirtyBlocks();
        GameRegistry.registerTileEntity(TileEntityCropWasher.class, "tentity_CropWashBlock");
        GameRegistry.registerBlock(blockCropWasher, "cropwashblock");
    }

    public static void postinit() {

    }


    public static void imc(FMLInterModComms.IMCMessage message) {

        if (CRef.useCropwash() == false){
            return;
        }

        if (message.key.equalsIgnoreCase("cropwash-add-replace")) {
            if (message.isNBTMessage()) {
                NBTTagCompound tag = message.getNBTValue();
                if (tag.getTag("add-replace") != null) {
                    NBTTagList tagz = (NBTTagList) tag.getTag("add-replace");
                    if (tagz.tagCount() == 2) {
                        if (Block.getBlockFromItem(ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(0)).getItem()) instanceof Block && ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(1)).getItem() instanceof Item) {
                            DirtyBlocks.addReplace(Block.getBlockFromItem(ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(0)).getItem()), ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(1)).getItem());
                            SALog.fatal("Successfully switched breaking " + StatCollector.translateToLocal(ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(0)).getItem().getUnlocalizedName()) + " with " + StatCollector.translateToLocal(ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(1)).getItem().getUnlocalizedName()));
                        } else {
                            SALog.fatal("Error overriding crop, Either not block or Item");
                        }
                    } else {
                        SALog.fatal("Error overriding crop, Tag is not 2");
                    }
                } else {
                    SALog.fatal("Error overriding crop, add-replace is null");
                }
            } else {
                SALog.fatal("Error overriding crop, message is not NBT");
            }
        } else {
            SALog.fatal("WAT M8?");
        }
    }

}
