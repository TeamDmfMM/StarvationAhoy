package dmfmm.StarvationAhoy.Meat.Village;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;

public class VillagerTradeAdditions{

	//TODO REINSERT VILLAGERS!
	public static void addVillager(Side side){
		VillagerRegistry.instance().registerVillagerId(getVID());
		if(side == Side.CLIENT){VillagerRegistry.instance().registerVillagerSkin(getVID(), new ResourceLocation("starvationahoy:textures/entity/VillageButcher.png"));}
		//VillagerRegistry.instance().registerVillageTradeHandler(getVID(), TradeHandler.INSTANCE);
	}
	protected static int getVID(){
		return 44442;
	}
/*public static class TradeHandler implements IVillageTradeHandler{

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList r, Random random) {
		if(1 == getWeightedRandoms(random))
			r.add(new MerchantRecipe(new ItemStack(Items.emerald, 3), null, new ItemStack(MItemLoader.deadChicken, 1)));
		if(1 == getWeightedRandoms(random))
			r.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), null, new ItemStack(MItemLoader.deadPig, 1)));
		if(1 == getWeightedRandoms(random))
			r.add(new MerchantRecipe(new ItemStack(Items.emerald, 7), null, new ItemStack(MItemLoader.deadCow, 1)));
		if(1 == getWeightedRandoms(random))
			r.add(new MerchantRecipe(new ItemStack(Items.beef, getRStack(random, 10, 7)), null, new ItemStack(Items.emerald, 2)));
		if(1 == getWeightedRandoms(random))
			r.add(new MerchantRecipe(new ItemStack(Items.emerald, getRStack(random, 9, 3)), null, new ItemStack(MBlockLoader.MeatHanger, 1)));
		if(1 == getWeightedRandoms(random))
			r.add(new MerchantRecipe(new ItemStack(Items.cooked_porkchop, getRStack(random, 9, 4)), null, new ItemStack(Items.emerald, 3)));
		
	}
	
	public final static TradeHandler INSTANCE = new TradeHandler();
	
}
	private static int getWeightedRandoms(Random rand){
		return rand.nextInt((1 - 0) + 1) + 0;

	}
	private static int getRStack(Random rand, int max, int min){
		return rand.nextInt((max - min) + 1) + min;

	}*/
}
