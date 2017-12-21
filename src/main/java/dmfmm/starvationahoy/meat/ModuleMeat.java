package dmfmm.starvationahoy.meat;

import dmfmm.starvationahoy.StarvationAhoy;
import dmfmm.starvationahoy.meat.events.AnimalDeathEvent;
import dmfmm.starvationahoy.meat.events.MeatCutRoasterEvent;
import dmfmm.starvationahoy.meat.events.MeatSkinnedEvent;
import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.init.MeatEnchantRegistry;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import dmfmm.starvationahoy.meat.village.BHHandler;
import dmfmm.starvationahoy.meat.village.ButcherHouse;
import dmfmm.starvationahoy.meat.village.VillagerTradeAdditions;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.VillagerRegistry;


public class ModuleMeat {

	public static MeatRegistry registry = new MeatRegistry();



	public static void preinit(){
		MeatBlockRegistry.registerBlocks();
		MeatItemRegistry.registerItems();
		MeatEnchantRegistry.registerEnchantments();

		VillagerRegistry.instance().registerVillageCreationHandler(new BHHandler()); 
        try { 
            MapGenStructureIO.registerStructureComponent(ButcherHouse.class, "starvationahoy:ButcherHouse");
        } catch (Throwable e) { } 
        VillagerTradeAdditions.addVillager();

	}
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new AnimalDeathEvent());
        MinecraftForge.EVENT_BUS.register(new MeatSkinnedEvent());
        //MinecraftForge.EVENT_BUS.register(new MeatCutHangerEvent());
        MinecraftForge.EVENT_BUS.register(new MeatCutRoasterEvent());
        StarvationAhoy.proxy.registerMeatTypes();

		MeatRecipieHandler.registerCraftingRecipies();

		
	}
	
	
	public static void postinit(){
	
	}
	
	
	
	
}