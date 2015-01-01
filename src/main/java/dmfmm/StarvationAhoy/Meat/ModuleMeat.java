package dmfmm.StarvationAhoy.Meat;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import cpw.mods.fml.common.registry.VillagerRegistry;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.Village.BHHandler;
import dmfmm.StarvationAhoy.Meat.Village.ButcherHouse;


public class ModuleMeat {
	
	
	public static void preinit(){
		MBlockLoader.initiateBlocks();
		MBlockLoader.initTileEntity();
		VillagerRegistry.instance().registerVillageCreationHandler(new BHHandler()); 
        try { 
            MapGenStructureIO.func_143031_a(ButcherHouse.class, "StarvationAhoy:ButcherHouse"); 
        } catch (Throwable e) { 
        } 
	}
	
	public static void init(){
		 
		}
	
	
	public static void postinit(){
	
	}
	
	
	
	
	
}