package dmfmm.StarvationAhoy.Meat;

import dmfmm.StarvationAhoy.Meat.Events.Event_KillAnimal;
import dmfmm.StarvationAhoy.Meat.Events.event_meatCutHanger;
import dmfmm.StarvationAhoy.Meat.Events.event_meatCutRoaster;
import dmfmm.StarvationAhoy.Meat.Events.event_meatSkinned;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.Village.BHHandler;
import dmfmm.StarvationAhoy.Meat.Village.ButcherHouse;
import dmfmm.StarvationAhoy.Meat.Village.VillagerTradeAdditions;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;


public class ModuleMeat {

	public static MeatRegistry registry = new MeatRegistry();
    public static final int  MEATTYPE_COW = 1, MEATTYPE_PIG = 2, MEATTYPE_CHICK = 3;



	public static void preinit(Side side){
		MItemLoader.initiateItems();
		MBlockLoader.initiateBlocks();
		MBlockLoader.initTileEntity();
		VillagerRegistry.instance().registerVillageCreationHandler(new BHHandler()); 
        try { 
            MapGenStructureIO.func_143031_a(ButcherHouse.class, "StarvationAhoy:ButcherHouse"); 
        } catch (Throwable e) { } 
        VillagerTradeAdditions.addVillager(side);

        
	}
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new Event_KillAnimal());
        MinecraftForge.EVENT_BUS.register(new event_meatSkinned());
        MinecraftForge.EVENT_BUS.register(new event_meatCutHanger());
        MinecraftForge.EVENT_BUS.register(new event_meatCutRoaster());


		
	}
	
	
	public static void postinit(){
	
	}
	
	
	
	
}