package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModuleVanilla {

	public static void init() {
		KnownFoods.insertFood(new ItemStack(Items.apple), 1, 1.2F);
		KnownFoods.insertFood(new ItemStack(Items.mushroom_stew), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.bread), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.porkchop), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.cooked_porkchop), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.golden_apple), 6, 5); //Normal Apple
		KnownFoods.insertFood(new ItemStack(Items.golden_apple, 1, 1), 6, 5); //Notch Apple
		KnownFoods.insertFood(new ItemStack(Items.fish, 1, 0), 6, 5); //Raw Fish
		KnownFoods.insertFood(new ItemStack(Items.fish, 1, 1), 6, 5); //Raw Salmon
		KnownFoods.insertFood(new ItemStack(Items.fish, 1, 2), 6, 5); //Clownfish
		KnownFoods.insertFood(new ItemStack(Items.fish, 1, 3), 6, 5); //Pufferfish
		KnownFoods.insertFood(new ItemStack(Items.cooked_fished, 1, 0), 6, 5);  //Cooked Fish
		KnownFoods.insertFood(new ItemStack(Items.cooked_fished, 1, 1), 6, 5);  //Cooked Salmon
		//KnownFoods.insertFood(new ItemStack(Items.cake, 1, 0), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.cookie), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.melon), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.beef), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.cooked_beef), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.chicken), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.cooked_chicken), 6, 5);
		//Rotten Flesh
		//Spider Eye
		KnownFoods.insertFood(new ItemStack(Items.carrot), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.potato), 1000, 250);
		KnownFoods.insertFood(new ItemStack(Items.baked_potato), 6, 5);
		//Poison Potato
		KnownFoods.insertFood(new ItemStack(Items.golden_carrot), 6, 5);
		KnownFoods.insertFood(new ItemStack(Items.pumpkin_pie), 6, 5);
		
		
		
		
	}

}
