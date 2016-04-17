package dmfmm.StarvationAhoy.Meat.Village;

import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

public class VillagerTradeAdditions {

	//TODO REINSERT VILLAGERS!
	public static void addVillager(Side side) {
		VillagerRegistry.instance().register(new SAButcher());
		//if(side == Side.CLIENT){VillagerRegistry.instance().registerVillagerSkin(getVID(), new ResourceLocation("starvationahoy:textures/entity/VillageButcher.png"));}
		//VillagerRegistry.instance().registerVillageTradeHandler(getVID(), TradeHandler.INSTANCE);
	}

	protected static int getVID() {
		return 44442;
	}


	public static class TradeHandler extends VillagerRegistry.VillagerCareer {

		public TradeHandler(VillagerRegistry.VillagerProfession parent) {
			super(parent, "SAButcher");
		}

		public EntityVillager.ITradeList[][] getTrades() {
			return this.trades;
		}

		private static final EntityVillager.ITradeList[][] trades =
				{
						{
								new EntityVillager.ListItemForEmeralds(MItemLoader.deadChicken, new EntityVillager.PriceInfo(3, 4)),
								new EntityVillager.ListItemForEmeralds(MItemLoader.deadPig, new EntityVillager.PriceInfo(5, 6)),
								new EntityVillager.ListItemForEmeralds(MItemLoader.deadCow, new EntityVillager.PriceInfo(7, 8))
						},
						{
								new EntityVillager.ListItemForEmeralds(MItemLoader.deadRabbit, new EntityVillager.PriceInfo(10, 11)),
								new EntityVillager.ListItemForEmeralds(MItemLoader.deadSheep, new EntityVillager.PriceInfo(3, 8))
						},
						{
								new EntityVillager.ListItemForEmeralds(new ItemStack(Items.beef, getRandomStackNumber(7, 10)), new EntityVillager.PriceInfo(2, 3)),
								new EntityVillager.ListItemForEmeralds(new ItemStack(MBlockLoader.MeatHanger), new EntityVillager.PriceInfo(3, 9)),
								new EntityVillager.ListItemForEmeralds(new ItemStack(Items.cooked_porkchop, getRandomStackNumber(4, 9)), new EntityVillager.PriceInfo(3, 3))
						}

				};
	}

	private static int getRandomStackNumber(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}


	public static class SAButcher extends VillagerRegistry.VillagerProfession{

		public SAButcher() {
			super("SAButcher", "starvationahoy:textures/entity/VillageButcher.png");
		}
		public VillagerRegistry.VillagerCareer getCareer(int id)
		{
			return new TradeHandler(this);
		}
	}
}
