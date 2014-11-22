package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import java.util.ArrayList;

import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class KnownFoods {
	
	public static ArrayList<ArrayList<Object>> knownFoods = new ArrayList<ArrayList<Object>>();
	
	
	public static void insertFood(String foodname, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<Object>();
		Food.add(foodname);
		Food.add(HungerHunch);
		Food.add(saturation);
		knownFoods.add(Food);
	}
	
	public static void insertFood(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<Object>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		knownFoods.add(Food);
	}
	
	public static int getFoodHunger(ItemStack stack){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}
	public static int getFoodHunger(String name){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			ItemStack stack = OreDictionary.getOres(name).get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}
	public static float getFoodSaturation(ItemStack stack){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return  (float) foods.get(2);
			}
		}
		return -1;
	}
	public static int getFoodSaturation(String name){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			ItemStack stack = OreDictionary.getOres(name).get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(2);
			}
		}
		return -1;
	}
}
