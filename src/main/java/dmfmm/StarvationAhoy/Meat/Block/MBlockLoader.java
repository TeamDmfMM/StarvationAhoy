package dmfmm.StarvationAhoy.Meat.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import net.minecraft.block.Block;

public class MBlockLoader {
	
	public static Block MeatHanger;
	
	public static boolean Register=false;
	
	public static void initiateBlocks() {
		MeatHanger = new MeatHanger().setBlockName(MeatLib.bMeatHanger);
		
		
		
		
		registerBlocks();
	}

	
	
	private static void registerBlocks() {
		if(!Register){
			
			GameRegistry.registerBlock(MeatHanger, MeatLib.bMeatHanger);
			
			
		}
		Register = true;
	}
	
	public static void initTileEntity() {
		GameRegistry.registerTileEntity(MeatHangerTileEntity.class, MeatLib.bMeatHanger);
		
	}
}
