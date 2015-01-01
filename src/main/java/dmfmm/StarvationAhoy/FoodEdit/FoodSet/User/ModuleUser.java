package dmfmm.StarvationAhoy.FoodEdit.FoodSet.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import net.minecraft.item.ItemStack;

import com.google.gson.Gson;

import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.FileReader.FileLoader;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.KnownFoods;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.Module;

public class ModuleUser extends Module {

	@Override
	public void init(KnownFoods food) {
		// TODO Auto-generated method stub
			
		File stuufs = new File(FileLoader.getCfgPath() + "StarvationAhoy", "food.cfg");
		BufferedReader buff;
		try {
			buff = new BufferedReader(new FileReader(stuufs));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			buff = null;
			return;
		}
		Overrides stuffies = new Gson().fromJson(buff, Overrides.class);
		for (ModOverrides mod : stuffies.foods){
			String modname = mod.mod;
			for (FoodOverride foodie : mod.foods){
				ItemStack istack = GameRegistry.findItemStack(modname, foodie.name, 1);
				food.insertFoodI(istack, foodie.hunger, foodie.saturation);
			}
		}
		
	}

}
