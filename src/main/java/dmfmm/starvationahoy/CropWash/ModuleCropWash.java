package dmfmm.starvationahoy.CropWash;

import dmfmm.starvationahoy.Core.Init.CropwashTextureRegistry;
import dmfmm.starvationahoy.Core.util.CRef;
import dmfmm.starvationahoy.Core.util.SALog;
import dmfmm.starvationahoy.CropWash.Block.BlockCropWasher;
import dmfmm.starvationahoy.CropWash.Block.tilentity.TileEntityCropWasher;
import dmfmm.starvationahoy.CropWash.Crossmod.CrossMod;
import dmfmm.starvationahoy.CropWash.item.CropItemLoader;
import dmfmm.starvationahoy.CropWash.modelbake.TextureInjector;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
            //ModelLoaderRegistry.registerLoader(DirtyItemSmartModel.Loader.instance);
            CropwashTextureRegistry.preInitTexture();
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

    public static void postInit() {
        CrossMod.postinit();
    }


    public static void imc(FMLInterModComms.IMCMessage message) {

        if (CRef.useCropwash() == false){
            return;
        }

        if (message.key.equalsIgnoreCase("cropwash-add-replace")) {
            if (message.isNBTMessage()) {
                NBTTagCompound tag = message.getNBTValue();
                if (tag.hasKey("add-replace")) {
                    NBTTagList tagz = (NBTTagList) tag.getTag("add-replace");
                    if (tagz.tagCount() == 2) {
                        Block possibleBlock = Block.getBlockFromItem(new ItemStack(tagz.getCompoundTagAt(0)).getItem());
                        Item possibleItem = new ItemStack(tagz.getCompoundTagAt(1)).getItem();
                        if (possibleBlock instanceof Block && possibleItem instanceof Item) {
                            DirtyBlocks.addReplace(possibleBlock, possibleItem);
                            SALog.fatal("Successfully switched breaking " + I18n.format(possibleBlock.getUnlocalizedName()) + " with " + possibleItem.getUnlocalizedName());
                        } else {
                            SALog.fatal("Error overriding crop, Either not block or Item");
                        }
                    } else {
                        SALog.fatal("Error overriding crop, Tag is not 2");
                    }
                } else {
                    SALog.fatal("Error overriding crop, add-replace is non-existant");
                }
            } else {
                SALog.fatal("Error overriding crop, message is not NBT");
            }
        } else {
            SALog.fatal("WAT M8?");
        }
    }

}
