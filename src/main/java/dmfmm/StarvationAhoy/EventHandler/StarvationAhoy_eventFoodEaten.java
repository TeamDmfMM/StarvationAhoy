package dmfmm.StarvationAhoy.EventHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Event.HungerEvent.FoodEaten;

public class StarvationAhoy_eventFoodEaten {

	@SubscribeEvent
	public void FoodEaten(FoodEaten event){
		int pie = 1 / 0;
		
		System.out.println("TESTESTESTESTEST");
	}
	
}
