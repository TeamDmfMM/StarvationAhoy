package dmfmm.StarvationAhoy.EventHandler;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Event.HungerEvent;

	public class FoodEatenResult
	{
		@SubscribeEvent
		public void onFoodEaten(PlayerUseItemEvent.Start event)
		{
			//SALog.error("IMMATRY");
			if(event.item.getItem() instanceof ItemFood){
				ItemFood usingFood = (ItemFood) event.item.getItem();
				int HealAmount = usingFood.func_150905_g(event.item);
				float SaturationAmt = usingFood.func_150906_h(event.item);
			}
		}
	}