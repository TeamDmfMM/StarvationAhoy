package dmfmm.starvationahoy.Meat;

import dmfmm.starvationahoy.Core.Init.MeatTextureRegistry;
import dmfmm.starvationahoy.Meat.Events.Event_KillAnimal;
import dmfmm.starvationahoy.Meat.Events.event_meatCutRoaster;
import dmfmm.starvationahoy.Meat.Events.event_meatSkinned;
import dmfmm.starvationahoy.StarvationAhoy;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import dmfmm.starvationahoy.Meat.Block.MBlockLoader;
import dmfmm.starvationahoy.Meat.Village.BHHandler;
import dmfmm.starvationahoy.Meat.Village.ButcherHouse;
import dmfmm.starvationahoy.Meat.Village.VillagerTradeAdditions;
import dmfmm.starvationahoy.Meat.item.MItemLoader;
import net.minecraftforge.fml.relauncher.Side;


public class ModuleMeat {

	public static MeatRegistry registry = new MeatRegistry();



	public static void preinit(Side side){
		MItemLoader.initiateItems();
		MBlockLoader.initiateBlocks();
		MBlockLoader.initTileEntity();
		VillagerRegistry.instance().registerVillageCreationHandler(new BHHandler()); 
        try { 
            MapGenStructureIO.registerStructureComponent(ButcherHouse.class, "starvationahoy:ButcherHouse");
        } catch (Throwable e) { } 
        VillagerTradeAdditions.addVillager(side);

        if(side == Side.CLIENT){
			MeatTextureRegistry.preinitTextures();
		}
	}
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new Event_KillAnimal());
        MinecraftForge.EVENT_BUS.register(new event_meatSkinned());
        //MinecraftForge.EVENT_BUS.register(new event_meatCutHanger());
        MinecraftForge.EVENT_BUS.register(new event_meatCutRoaster());
        StarvationAhoy.proxy.registerMeatTypes();

		MeatRecipieHandler.registerCraftingRecipies();

		
	}
	
	
	public static void postinit(){
	
	}
	
	
	
	
}