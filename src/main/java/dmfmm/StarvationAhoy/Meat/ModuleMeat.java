package dmfmm.StarvationAhoy.Meat;

import dmfmm.StarvationAhoy.Core.Init.MeatTextureRegistry;
import dmfmm.StarvationAhoy.Meat.Events.Event_KillAnimal;
import dmfmm.StarvationAhoy.Meat.Events.event_meatCutHanger;
import dmfmm.StarvationAhoy.Meat.Events.event_meatCutRoaster;
import dmfmm.StarvationAhoy.Meat.Events.event_meatSkinned;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.Village.BHHandler;
import dmfmm.StarvationAhoy.Meat.Village.ButcherHouse;
import dmfmm.StarvationAhoy.Meat.Village.VillagerTradeAdditions;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraftforge.fml.relauncher.Side;


public class ModuleMeat {

	public static MeatRegistry registry = new MeatRegistry();
    public static final int  MEATTYPE_COW = 1, MEATTYPE_PIG = 2, MEATTYPE_CHICK = 3, MEATTYPE_SHEEP = 4, MEATTYPE_RABBIT = 5;



	public static void preinit(Side side){
		MItemLoader.initiateItems();
		MBlockLoader.initiateBlocks();
		MBlockLoader.initTileEntity();
		VillagerRegistry.instance().registerVillageCreationHandler(new BHHandler()); 
        try { 
            MapGenStructureIO.registerStructureComponent(ButcherHouse.class, "StarvationAhoy:ButcherHouse");
        } catch (Throwable e) { } 
        VillagerTradeAdditions.addVillager(side);

        if(side == Side.CLIENT){
			MeatTextureRegistry.preinitTextures();
		}
	}
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new Event_KillAnimal());
        MinecraftForge.EVENT_BUS.register(new event_meatSkinned());
        MinecraftForge.EVENT_BUS.register(new event_meatCutHanger());
        MinecraftForge.EVENT_BUS.register(new event_meatCutRoaster());
        StarvationAhoy.proxy.registerMeatTypes();

		MeatRecipieHandler.registerCraftingRecipies();

		
	}
	
	
	public static void postinit(){
	
	}
	
	
	
	
}