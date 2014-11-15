package dmfmm.StarvationAhoy.Event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.util.FoodStats;

public abstract class HungerEvent extends Event{
	/**
	 * Fired after {@link FoodStats#addStats}, containing the effects and context for the food that was eaten.
	 * 
	 * This event is fired in {@link FoodStats#func_151686_a(ItemFood, ItemStack)}.<br>
	 * <br>
	 * This event is not {@link Cancelable}.<br>
	 * <br>
	 * This event does not have a result. {@link HasResult}<br>
	 */
	public static class FoodEaten extends HungerEvent 
 	{ 
 		//public final FoodValues foodValues; 
 		public final int hungerAdded; 
 		public final float saturationAdded; 
 		public final ItemStack food; 
 		public final EntityPlayer player; 
 
 
 	public FoodEaten(EntityPlayer player, ItemStack itemStack,/* FoodValues foodValues,*/ int hungerAdded, float saturationAdded){ 
 			this.player = player; 
 			this.food = itemStack; 
 			//this.foodValues = foodValues; 
 			this.hungerAdded = hungerAdded; 
 			this.saturationAdded = saturationAdded; 
 		} 
 	} 

}
