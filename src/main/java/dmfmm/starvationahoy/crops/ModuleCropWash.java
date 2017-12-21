package dmfmm.starvationahoy.crops;

import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.core.util.SALog;
import dmfmm.starvationahoy.crops.crossmod.CrossMod;
import dmfmm.starvationahoy.crops.events.CropWashEvents;
import dmfmm.starvationahoy.crops.events.VillagerCropOverride;
import dmfmm.starvationahoy.crops.init.CropBlockRegistry;
import dmfmm.starvationahoy.crops.init.CropItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class ModuleCropWash {

    public static DirtyBlocks replaceableBlockRegistry;

    public static void preinit() {

        if (!ConfigHandler.useCropwash()){
            return;
        }

        CropBlockRegistry.registerBlocks();
        CropItemRegistry.registerItems();

        MinecraftForge.EVENT_BUS.register(new VillagerCropOverride());

    }

    public static void init() {

        if (!ConfigHandler.useCropwash()){
            return;
        }


        MinecraftForge.EVENT_BUS.register(new CropWashEvents());
        replaceableBlockRegistry = new DirtyBlocks();
        CrossMod.init();

        CropCraftingRecipies.registerRecipies();

    }

    public static void postInit() {
        CrossMod.postinit();
    }


    public static void imc(FMLInterModComms.IMCMessage message) {

        if (ConfigHandler.useCropwash() == false){
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
