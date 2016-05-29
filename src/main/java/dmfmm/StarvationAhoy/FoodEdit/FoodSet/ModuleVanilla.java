package dmfmm.StarvationAhoy.FoodEdit.FoodSet;


import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModuleVanilla extends Module{

	public void init(KnownFoods kf) {
		kf.insertFood(new ItemStack(Items.APPLE), 1, 1.2F);
		kf.insertFood(new ItemStack(Items.MUSHROOM_STEW), 6, 5);
		kf.insertFood(new ItemStack(Items.BREAD), 6, 5);
		kf.insertFood(new ItemStack(Items.PORKCHOP), 6, 5);
		kf.insertFood(new ItemStack(Items.COOKED_PORKCHOP), 6, 5);
		kf.insertFood(new ItemStack(Items.GOLDEN_APPLE), 6, 5); //Normal Apple
		kf.insertFood(new ItemStack(Items.GOLDEN_APPLE, 1, 1), 6, 5); //Notch Apple
		kf.insertFood(new ItemStack(Items.FISH, 1, 0), 6, 5); //Raw Fish
		kf.insertFood(new ItemStack(Items.FISH, 1, 1), 6, 5); //Raw Salmon
		kf.insertFood(new ItemStack(Items.FISH, 1, 2), 6, 5); //Clownfish
		kf.insertFood(new ItemStack(Items.FISH, 1, 3), 6, 5); //Pufferfish
		kf.insertFood(new ItemStack(Items.COOKED_FISH, 1, 0), 6, 5);  //Cooked Fish
		kf.insertFood(new ItemStack(Items.COOKED_FISH, 1, 1), 6, 5);  //Cooked Salmon
		//kf.insertFoodActive(new ItemStack(Items.cake, 1, 0), 6, 5);
		kf.insertFood(new ItemStack(Items.COOKIE), 6, 5);
		kf.insertFood(new ItemStack(Items.MELON), 6, 5);
		kf.insertFood(new ItemStack(Items.BEEF), 6, 5);
		kf.insertFood(new ItemStack(Items.COOKED_BEEF), 6, 5);
		kf.insertFood(new ItemStack(Items.CHICKEN), 6, 5);
		kf.insertFood(new ItemStack(Items.COOKED_CHICKEN), 6, 5);
		//Rotten Flesh
		//Spider Eye
		kf.insertFood(new ItemStack(Items.CARROT), 6, 5);
		kf.insertFood(new ItemStack(Items.POTATO), 1000, 250);
		kf.insertFood(new ItemStack(Items.BAKED_POTATO), 6, 5);
		//Poison Potato
		kf.insertFood(new ItemStack(Items.GOLDEN_CARROT), 6, 5);
		kf.insertFood(new ItemStack(Items.PUMPKIN_PIE), 6, 5);
		
		
		
		
	}

}
