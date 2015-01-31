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
        VillagerTradeAdditions.addVillager();
        
	}
	
	public static void init(Side side){
		 MinecraftForge.EVENT_BUS.register(new Event_KillAnimal());


		MeatType meatType = new MeatType(1);
		meatType.doDeadEntity(EntityCow.class, MItemLoader.deadCow, MItemLoader.skinlessCow);
		if(side == Side.CLIENT){meatType.doMeatType(new ModelCowSA(), "minecraft:textures/entity/cow/cow.png", "starvationahoy:textures/entity/skinnedCow.png", "starvationahoy:textures/entity/rottenCow.png");}
		registry.addMeatType(meatType);

		meatType = new MeatType(2);
		meatType.doDeadEntity(EntityPig.class, MItemLoader.deadPig, MItemLoader.skinlessPig);
		if(side == Side.CLIENT){meatType.doMeatType(new ModelPigSA(), "minecraft:textures/entity/pig/pig.png", "starvationahoy:textures/entity/skinnedPig.png", "starvationahoy:textures/entity/rottenPig.png");}
		registry.addMeatType(meatType);

		meatType = new MeatType(3);
		meatType.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, MItemLoader.skinlessChicken);
		if(side == Side.CLIENT){meatType.doMeatType(new ModelPigSA(), "minecraft:textures/entity/chicken.png", "starvationahoy:textures/entity/skinnedChicken.png", "starvationahoy:textures/entity/rottenChicken.png");}
		registry.addMeatType(meatType);
		}
	
	
	public static void postinit(){
	
	}
	
	
	
	
}