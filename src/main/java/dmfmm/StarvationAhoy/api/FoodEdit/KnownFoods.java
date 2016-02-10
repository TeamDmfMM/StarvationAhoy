package dmfmm.StarvationAhoy.api.FoodEdit;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class KnownFoods {
	
	
	/*
	This is here because reasons.
	 */
	
	public static ArrayList<ArrayList<Object>> knownFoods = new ArrayList<ArrayList<Object>>();
	public static ArrayList<ArrayList<Object>> knownFoodsActive = new ArrayList<ArrayList<Object>>();

	public static void swapToServer(ArrayList<ArrayList<Object>> swapTo) {
		knownFoodsActive = (ArrayList<ArrayList<Object>>) swapTo.clone();
	}

	public static void leaveServer() {
		knownFoodsActive = (ArrayList<ArrayList<Object>>) knownFoods.clone();
	}

	public static void changeFood(ItemStack i, int hunger, float saturation){
		boolean found = false;
		for(ArrayList<Object> foods : KnownFoods.knownFoodsActive) {
			ItemStack bob = (ItemStack) foods.get(0);

			if (bob == i){
				int aboutToEdit = KnownFoods.knownFoodsActive.indexOf(foods);
				ArrayList<Object> food = new ArrayList<>();

				food.add(i);
				food.add(hunger);
				food.add(saturation);
				found = true;
				KnownFoods.knownFoodsActive.set(aboutToEdit, food);
			}

		}
		if (found == false){
			KnownFoods.insertFoodActive(i, hunger, saturation);
		}

	}

	public static void insertFood(ItemStack foods, int HungerHunch, float saturation){
		insertFoodPassive(foods, HungerHunch, saturation);
		insertFoodActive(foods, HungerHunch, saturation);
	}
	
	public static void insertFoodPassive(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		for (ArrayList<Object> Foodk : knownFoods){
			if (((ItemStack)Foodk.get(0)).getItem() == foods.getItem()){
				knownFoods.remove(Foodk);
				break;
			}
		}
		KnownFoods.knownFoods.add(Food);
	}

	
	public static void insertFoodActive(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (knownFoodsActive.contains(Food)){
			knownFoodsActive.remove(Food);
		}
		for (ArrayList<Object> Foodl : knownFoodsActive){
			if (((ItemStack)Foodl.get(0)).getItem() == foods.getItem()){
				knownFoodsActive.remove(Foodl);
				break;
			}
		}
		knownFoodsActive.add(Food);
	}
	
	public static int getFoodHunger(ItemStack stack){
		for(ArrayList<Object> foods : knownFoodsActive){
			ItemStack bob =  (ItemStack) foods.get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}

	public static float getFoodSaturation(ItemStack stack){
		for(ArrayList<Object> foods : knownFoodsActive){
			ItemStack bob =  (ItemStack) foods.get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return  (float) foods.get(2);
			}
		}
		return -1;
	}

}
