package dmfmm.StarvationAhoy.Meat.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.CookerTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;

public class MBlockLoader {
	
	public static Block MeatHanger, HoldingStick, Cooker, test;
	
	public static boolean Register=false;
	
	public static void initiateBlocks() {
		MeatHanger = new MeatHanger().setBlockName(MeatLib.bMeatHanger);
		HoldingStick = new HoldingStick().setBlockName("DEV__WORLD__hold_stick");
		Cooker = new Cooker().setBlockName("DEV__1__cookerz");
		
		
		registerBlocks();
	}

	
	
	private static void registerBlocks() {
		if(!Register){
			
			GameRegistry.registerBlock(MeatHanger, MeatLib.bMeatHanger);
			GameRegistry.registerBlock(HoldingStick, "DEV__WORLD__2__hold_stick");
			GameRegistry.registerBlock(Cooker, "DEV__1__cooker");
			
			
		}
		Register = true;
	}
	
	public static void initTileEntity() {
		GameRegistry.registerTileEntity(MeatHangerTileEntity.class, MeatLib.bMeatHanger);
		GameRegistry.registerTileEntity(HoldingStickTileEntity.class, MeatLib.Hold_Stick);
		GameRegistry.registerTileEntity(CookerTileEntity.class, "DEV__TILE__cooker");
		
	}
}
