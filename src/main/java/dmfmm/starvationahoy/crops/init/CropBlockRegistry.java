package dmfmm.starvationahoy.crops.init;

import dmfmm.starvationahoy.base.BlockRegister;
import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.crops.block.BlockCropWasher;
import dmfmm.starvationahoy.crops.block.tilentity.TileEntityCropWasher;
import jline.internal.Log;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;

/**
 * Created by dmf444 on 12/20/2017. Code originally written for StarvationAhoy.
 */
public class CropBlockRegistry {

    public static Block CROP_WASHER = new BlockCropWasher();


    public static void registerBlocks() {
        if(ConfigHandler.useCropwash()) {

            for (Field field : CropBlockRegistry.class.getDeclaredFields()) {
                if (field.getType() == Block.class) {
                    try {
                        Block block = (Block) field.get(null);
                        BlockRegister.addBlock(block, block.getRegistryName().getResourcePath());
                    } catch (IllegalAccessException e) {
                        Log.warn("Somehow failed to get a block from its registrator. WAT?");
                    }
                }
            }

            initTileEntity();
        }
    }

    public static void initTileEntity() {
        GameRegistry.registerTileEntity(TileEntityCropWasher.class, "tentity_CropWashBlock");

    }
}
