package dmfmm.StarvationAhoy.Meat;

import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;


public class ModuleMeat {
	
	
	public static void preinit(){
		MBlockLoader.initiateBlocks();
	}
	
	public static void init(){
		 MBlockLoader.initTileEntity();
		}
	
	
	public static void postinit(){
	
	}
	
	
	
	
	
}