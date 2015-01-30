package dmfmm.StarvationAhoy.Meat.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import net.minecraft.block.Block;

public class MBlockLoader {
	
	public static Block MeatHanger, HoldingStick;
	
	public static boolean Register=false;
	
	public static void initiateBlocks() {
		MeatHanger = new MeatHanger().setBlockName(MeatLib.bMeatHanger);
		HoldingStick = new HoldingStick().setBlockName(MeatLib.Hold_Stick);
		
		
		
		registerBlocks();
	}

	
	
	private static void registerBlocks() {
		if(!Register){
			
			GameRegistry.registerBlock(MeatHanger, MeatLib.bMeatHanger);
			GameRegistry.registerBlock(HoldingStick, MeatLib.Hold_Stick);
			
			
		}
		Register = true;
	}
	
	public static void initTileEntity() {
		GameRegistry.registerTileEntity(MeatHangerTileEntity.class, MeatLib.bMeatHanger);
		GameRegistry.registerTileEntity(HoldingStickTileEntity.class, MeatLib.Hold_Stick);
		
	}
}
