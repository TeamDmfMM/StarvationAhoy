package dmfmm.starvationahoy.meat.village;

import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.Random;

public class VillagerTradeAdditions {


	public static void addVillager() {
		VillagerRegistry.VillagerProfession prof = new SAButcher();
		ForgeRegistries.VILLAGER_PROFESSIONS.register(prof);
		VillagerRegistry.VillagerCareer carrer = new VillagerRegistry.VillagerCareer(prof, "butcher");
		carrer.addTrade(1, TradeHandler.trades[0][0]);
		carrer.addTrade(2, TradeHandler.trades[0][1]);
		carrer.addTrade(3, TradeHandler.trades[0][2]);
		//if(side == Side.CLIENT){VillagerRegistry.instance().registerVillagerSkin(getVID(), new ResourceLocation("starvationahoy:textures/entity/villagebutcher.png"));}
		//VillagerRegistry.instance().registerVillageTradeHandler(getVID(), TradeHandler.INSTANCE);
	}

	protected static int getVID() {
		return 44442;
	}


	public static class TradeHandler {
		private static final EntityVillager.ITradeList[][] trades =
				{
						{
								new EntityVillager.ListItemForEmeralds(MeatItemRegistry.DEAD_CHICKEN, new EntityVillager.PriceInfo(3, 4)),
								new EntityVillager.ListItemForEmeralds(MeatItemRegistry.DEAD_PIG, new EntityVillager.PriceInfo(5, 6)),
								new EntityVillager.ListItemForEmeralds(MeatItemRegistry.DEAD_COW, new EntityVillager.PriceInfo(7, 8))
						},
						{
								new EntityVillager.ListItemForEmeralds(MeatItemRegistry.DEAD_RABBIT, new EntityVillager.PriceInfo(10, 11)),
								new EntityVillager.ListItemForEmeralds(MeatItemRegistry.DEAD_SHEEP, new EntityVillager.PriceInfo(3, 8))
						},
						{
								new EntityVillager.ListItemForEmeralds(new ItemStack(Items.BEEF, getRandomStackNumber(7, 10)), new EntityVillager.PriceInfo(2, 3)),
								new EntityVillager.ListItemForEmeralds(new ItemStack(MeatBlockRegistry.MEAT_HANGER), new EntityVillager.PriceInfo(3, 9)),
								new EntityVillager.ListItemForEmeralds(new ItemStack(Items.COOKED_PORKCHOP, getRandomStackNumber(4, 9)), new EntityVillager.PriceInfo(3, 3))
						}

				};
	}

	private static int getRandomStackNumber(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}


	public static class SAButcher extends VillagerRegistry.VillagerProfession{

		public SAButcher() {
			//TODO: ZOMBIE TEXTURE?
			super("starvationahoy:SAButcher", "starvationahoy:textures/entity/villagebutcher.png", "starvationahoy:textures/entity/ded.png");
		}
	}
}
