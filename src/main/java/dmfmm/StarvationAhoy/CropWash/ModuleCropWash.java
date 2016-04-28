package dmfmm.StarvationAhoy.CropWash;

import dmfmm.StarvationAhoy.Core.Init.CropwashTextureRegistry;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.CropWash.Block.BlockCropWasher;
import dmfmm.StarvationAhoy.CropWash.Block.tilentity.TileEntityCropWasher;
import dmfmm.StarvationAhoy.CropWash.Crossmod.CrossMod;
import dmfmm.StarvationAhoy.CropWash.item.CropItemLoader;
import dmfmm.StarvationAhoy.CropWash.modelbake.DirtyItemSmartModel;
import dmfmm.StarvationAhoy.CropWash.modelbake.TextureInjector;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class ModuleCropWash {

    public static CropItemLoader cropItemLoader = new CropItemLoader();

    public static Block blockCropWasher;
    public static DirtyBlocks d;

    public static void preinit(Side side) {

        if (CRef.useCropwash() == false){
            return;
        }

        blockCropWasher = new BlockCropWasher();
        blockCropWasher.setRegistryName("cropwashblock");
        GameRegistry.register(blockCropWasher);
        GameRegistry.register(new ItemBlock(blockCropWasher).setRegistryName(blockCropWasher.getRegistryName()));
        MinecraftForge.EVENT_BUS.register(new VillagerCropOverride());
        if(side == Side.CLIENT) {
            ModelLoaderRegistry.registerLoader(DirtyItemSmartModel.Loader.instance);
            CropwashTextureRegistry.doDirtyItem();
        }
        //GameRegistry.registerBlock(blockCropWasher, "cropwashblock");

    }

    public static void init(Side side) {

        if (CRef.useCropwash() == false){
            return;
        }
        if (side == Side.CLIENT) {
            //MinecraftForge.EVENT_BUS.register(new ModelBakeInjector());
            MinecraftForge.EVENT_BUS.register(new TextureInjector());
        }
        cropItemLoader.load();
        MinecraftForge.EVENT_BUS.register(new Events_CropWash());
        d = new DirtyBlocks();
        CrossMod.init();
        GameRegistry.registerTileEntity(TileEntityCropWasher.class, "tentity_CropWashBlock");

        CropCraftingRecipies.registerRecipies();

    }

    public static void postnit() {
        CrossMod.postinit();
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
                            SALog.fatal("Successfully switched breaking " + I18n.translateToLocal(ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(0)).getItem().getUnlocalizedName()) + " with " + I18n.translateToLocal(ItemStack.loadItemStackFromNBT(tagz.getCompoundTagAt(1)).getItem().getUnlocalizedName()));
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
