package dmfmm.StarvationAhoy.Meat;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Client.Renderer.ModelCowSA;
import dmfmm.StarvationAhoy.Client.Renderer.ModelPigSA;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.Village.BHHandler;
import dmfmm.StarvationAhoy.Meat.Village.ButcherHouse;
import dmfmm.StarvationAhoy.Meat.Village.VillagerTradeAdditions;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;


public class ModuleMeat {

	public static MeatRegistry registry = new MeatRegistry();

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



		
		}
	
	
	public static void postinit(){
	
	}
	
	
	
	
}