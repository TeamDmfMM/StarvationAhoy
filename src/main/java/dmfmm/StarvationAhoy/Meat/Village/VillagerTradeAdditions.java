package dmfmm.StarvationAhoy.Meat.Village;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillagerTradeAdditions{

	public static void addVillager(){
		VillagerRegistry.instance().registerVillagerId(getVID());
		VillagerRegistry.instance().registerVillagerSkin(getVID(), new ResourceLocation("starvationahoy:textures/entity/VillageButcher"));
		VillagerRegistry.instance().registerVillageTradeHandler(getVID(), handler);
	}
	private static int getVID(){
		return 44442;
	}

}
