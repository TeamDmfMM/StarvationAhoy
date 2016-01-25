package dmfmm.StarvationAhoy.api.FoodEdit;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class KnownFoods {


	public static ArrayList<ArrayList<Object>> activeKnownFoods = new ArrayList<ArrayList<Object>>();

	public static ArrayList<ArrayList<Object>> myKnownFoods = new ArrayList<ArrayList<Object>>();

	public static void joinS(){
		activeKnownFoods = (ArrayList<ArrayList<Object>>) myKnownFoods.clone();
	}

	public static void joinM(ArrayList<ArrayList<Object>> n) {
		activeKnownFoods = n;
	}


	public void insertFoodI(String foodname, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foodname);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (KnownFoods.myKnownFoods.contains(Food)){
			myKnownFoods.remove(Food);
		}
		KnownFoods.myKnownFoods.add(Food);
	}

	public static void ok(ItemStack is){
		for (ArrayList<Object> Food : myKnownFoods){
			if (((ItemStack)Food.get(0)).getItem() == is.getItem()){
				myKnownFoods.remove(Food);
				break;
			}
		}
	}

	public void insertFoodI(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		ok(foods);
		KnownFoods.myKnownFoods.add(Food);
	}

	public static void insertFood(String foodname, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foodname);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (myKnownFoods.contains(Food)){
			myKnownFoods.remove(Food);
		}
		myKnownFoods.add(Food);
	}

	public static void insertFood(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (myKnownFoods.contains(Food)){
			myKnownFoods.remove(Food);
		}
		ok(foods);
		myKnownFoods.add(Food);
	}

	public static void changeFood(ItemStack i, int hunger, float saturation){
		boolean found = false;
		for(ArrayList<Object> foods : KnownFoods.myKnownFoods) {
			ItemStack bob = (ItemStack) foods.get(0);

			if (bob == i){
				int aboutToEdit = KnownFoods.myKnownFoods.indexOf(foods);
				ArrayList<Object> food = new ArrayList<>();

				food.add(i);
				food.add(hunger);
				food.add(saturation);
				found = true;
				KnownFoods.myKnownFoods.set(aboutToEdit, food);
			}

		}
		if (found == false){
			KnownFoods.insertFood(i, hunger, saturation);
		}

	}

	public static int getFoodHunger(ItemStack stack){
		for(ArrayList<Object> foods : activeKnownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);

			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}
	public static int getFoodHunger(String name){
		for(ArrayList<Object> foods : activeKnownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			ItemStack stack = OreDictionary.getOres(name).get(0);

			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}
	public static float getFoodSaturation(ItemStack stack){
		for(ArrayList<Object> foods : activeKnownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);

			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return  (float) foods.get(2);
			}
		}
		return -1;
	}
	public static int getFoodSaturation(String name){
		for(ArrayList<Object> foods : activeKnownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			ItemStack stack = OreDictionary.getOres(name).get(0);

			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(2);
			}
		}
		return -1;
	}
}
