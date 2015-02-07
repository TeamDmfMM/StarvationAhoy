package dmfmm.StarvationAhoy.FoodEdit.FoodSet;


import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModuleVanilla extends Module{

	public void init(KnownFoods kf) {
		kf.insertFoodI(new ItemStack(Items.apple), 1, 1.2F);
		kf.insertFoodI(new ItemStack(Items.mushroom_stew), 6, 5);
		kf.insertFoodI(new ItemStack(Items.bread), 6, 5);
		kf.insertFoodI(new ItemStack(Items.porkchop), 6, 5);
		kf.insertFoodI(new ItemStack(Items.cooked_porkchop), 6, 5);
		kf.insertFoodI(new ItemStack(Items.golden_apple), 6, 5); //Normal Apple
		kf.insertFoodI(new ItemStack(Items.golden_apple, 1, 1), 6, 5); //Notch Apple
		kf.insertFoodI(new ItemStack(Items.fish, 1, 0), 6, 5); //Raw Fish
		kf.insertFoodI(new ItemStack(Items.fish, 1, 1), 6, 5); //Raw Salmon
		kf.insertFoodI(new ItemStack(Items.fish, 1, 2), 6, 5); //Clownfish
		kf.insertFoodI(new ItemStack(Items.fish, 1, 3), 6, 5); //Pufferfish
		kf.insertFoodI(new ItemStack(Items.cooked_fished, 1, 0), 6, 5);  //Cooked Fish
		kf.insertFoodI(new ItemStack(Items.cooked_fished, 1, 1), 6, 5);  //Cooked Salmon
		//kf.insertFood(new ItemStack(Items.cake, 1, 0), 6, 5);
		kf.insertFoodI(new ItemStack(Items.cookie), 6, 5);
		kf.insertFoodI(new ItemStack(Items.melon), 6, 5);
		kf.insertFoodI(new ItemStack(Items.beef), 6, 5);
		kf.insertFoodI(new ItemStack(Items.cooked_beef), 6, 5);
		kf.insertFoodI(new ItemStack(Items.chicken), 6, 5);
		kf.insertFoodI(new ItemStack(Items.cooked_chicken), 6, 5);
		//Rotten Flesh
		//Spider Eye
		kf.insertFoodI(new ItemStack(Items.carrot), 6, 5);
		kf.insertFoodI(new ItemStack(Items.potato), 1000, 250);
		kf.insertFoodI(new ItemStack(Items.baked_potato), 6, 5);
		//Poison Potato
		kf.insertFoodI(new ItemStack(Items.golden_carrot), 6, 5);
		kf.insertFoodI(new ItemStack(Items.pumpkin_pie), 6, 5);
		
		
		
		
	}

}
