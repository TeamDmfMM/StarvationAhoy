package dmfmm.starvationahoy.Meat.Block;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.starvationahoy.Core.lib.MeatLib;
import dmfmm.starvationahoy.Meat.Block.multiblock.CookerTileEntity;
import dmfmm.starvationahoy.Meat.Block.tileentity.HoldingStickTileEntity;
import dmfmm.starvationahoy.Meat.Block.tileentity.MeatHangerTileEntity;
import net.minecraft.block.Block;

public class MBlockLoader {
	
	public static Block MeatHanger, HoldingStick, Cooker;
	
	public static boolean Register=false;
	
	public static void initiateBlocks() {
		MeatHanger = new MeatHanger().setUnlocalizedName(MeatLib.bMeatHanger);
		HoldingStick = new HoldingStick().setUnlocalizedName(MeatLib.Hold_Stick);
		Cooker = new Cooker().setUnlocalizedName("DEV__1__cooker");
		
		
		registerBlocks();
	}

	
	
	private static void registerBlocks() {
		if(!Register){
			
			registerBlock(MeatHanger, MeatLib.bMeatHanger);
			registerBlock(HoldingStick, MeatLib.Hold_Stick);
			registerBlock(Cooker, "DEV__1__cooker");
			
			
		}
		Register = true;
	}
	
	public static void initTileEntity() {
		GameRegistry.registerTileEntity(MeatHangerTileEntity.class, MeatLib.bMeatHanger);
		GameRegistry.registerTileEntity(HoldingStickTileEntity.class, MeatLib.Hold_Stick);
		GameRegistry.registerTileEntity(CookerTileEntity.class, "DEV__TILE__cooker");
		
	}

	private static void registerBlock(Block block, String name){
		block.setRegistryName(name);
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
}
