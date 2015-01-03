package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemFood;

public class KnownEffects {
	
	public static Map<ItemFood, ArrayList<Double>> effects = new HashMap<ItemFood, ArrayList<Double>>();
	
	public static void addEffect(ItemFood food, int id, int duration, int amplifier, float prob){
		ArrayList ti = new ArrayList<Float>();
		ti.add((double)id);
		ti.add((double)duration*20);
		ti.add((double)amplifier);
		ti.add((double)prob);
		effects.put(food, ti);
	}
	
}
