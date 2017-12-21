package dmfmm.starvationahoy.meat.init;

import dmfmm.starvationahoy.base.BlockRegister;
import dmfmm.starvationahoy.core.lib.MeatLib;
import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.meat.block.Cooker;
import dmfmm.starvationahoy.meat.block.HoldingStick;
import dmfmm.starvationahoy.meat.block.MeatHanger;
import dmfmm.starvationahoy.meat.block.multiblock.CookerTileEntity;
import dmfmm.starvationahoy.meat.block.tileentity.HoldingStickTileEntity;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerTileEntity;
import jline.internal.Log;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;

public class MeatBlockRegistry {

	public static Block MEAT_HANGER = new MeatHanger();
	public static Block HOLDING_STICK = new HoldingStick();
	public static Block COOKER = new Cooker();


	public static void registerBlocks() {
		if(ConfigHandler.useMeatOverride()) {

			for (Field field : MeatBlockRegistry.class.getDeclaredFields()) {
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
		GameRegistry.registerTileEntity(MeatHangerTileEntity.class, MeatLib.MEAT_HANGER);
		GameRegistry.registerTileEntity(HoldingStickTileEntity.class, MeatLib.HOLDING_STICK);
		GameRegistry.registerTileEntity(CookerTileEntity.class, "DEV__TILE__cooker");

	}
}
