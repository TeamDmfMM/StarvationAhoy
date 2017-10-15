package dmfmm.starvationahoy.Meat;

import dmfmm.starvationahoy.Core.Init.MeatTextureRegistry;
import dmfmm.starvationahoy.Meat.events.Event_KillAnimal;
import dmfmm.starvationahoy.Meat.events.event_meatCutRoaster;
import dmfmm.starvationahoy.Meat.events.event_meatSkinned;
import dmfmm.starvationahoy.Meat.item.MItemLoader;
import dmfmm.starvationahoy.Meat.village.BHHandler;
import dmfmm.starvationahoy.Meat.village.ButcherHouse;
import dmfmm.starvationahoy.Meat.village.VillagerTradeAdditions;
import dmfmm.starvationahoy.StarvationAhoy;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;


public class ModuleMeat {

	public static MeatRegistry registry = new MeatRegistry();



	public static void preinit(Side side){
		MItemLoader.initiateItems();
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