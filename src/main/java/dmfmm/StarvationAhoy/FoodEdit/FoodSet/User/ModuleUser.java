package dmfmm.StarvationAhoy.FoodEdit.FoodSet.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.gson.Gson;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.FoodEdit.FileReader.FileLoader;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;




public class ModuleUser extends Module {

	@Override
	public void init(KnownFoods food) {
		File stuufs_dump = new File(FileLoader.getCfgPath(), "food_dump.json");
		Overrides allFoods = new Overrides();
		Map<String, ArrayList<FoodOverride>> mods = new HashMap<>();
		//SALog.error(KnownFoods.knownFoods.size());
		for (ArrayList<Object> foodies : KnownFoods.knownFoods){
			String[] comps = Item.itemRegistry.getNameForObject(((ItemStack) foodies.get(0)).getItem()).toString().split(":");


			if (true){
				if (!mods.containsKey(comps[0])){
					mods.put(comps[0], new ArrayList<FoodOverride>());
				}
				FoodOverride f = new FoodOverride();
				f.name = comps[1];
				f.hunger = (int) foodies.get(1);
				f.saturation = (float) foodies.get(2);
				mods.get(comps[0]).add(f);

			}

		}
		allFoods.foods = new ModOverrides[mods.keySet().size()];
		for (int index = 0; index < mods.keySet().size(); index++) {

			ModOverrides modOverrides = new ModOverrides();
			modOverrides.mod = (String) mods.keySet().toArray()[index];
			//SALog.error(modOverrides.mod);
			FoodOverride[] fodsdsds = new FoodOverride[mods.get(modOverrides.mod).size()];
			//SALog.info(mods.get(modOverrides.mod).size());
			int idofcurrent = 0;
			for (FoodOverride fodds : mods.get(modOverrides.mod)){
				fodsdsds[idofcurrent] = fodds;
				idofcurrent++;
			}
			modOverrides.foods = fodsdsds;
			allFoods.foods[index] = modOverrides;
		}

		File stuufs = new File(FileLoader.getCfgPath(), "food.json");

		if (stuufs.exists() == false){
			try {
				stuufs.createNewFile();
				Overrides over = new Overrides();
				over.foods = new ModOverrides[1];
				ModOverrides m = new ModOverrides();
				m.mod = "minecraft";
				m.foods = new FoodOverride[1];
				FoodOverride fd = new FoodOverride();
				fd.name = "apple";
				fd.hunger = 1;
				fd.saturation = 1.2F;
				m.foods[0] = fd;
				over.foods[0] = m;
				String testgggg = new GsonBuilder().setPrettyPrinting().create().toJson(over);
				BufferedWriter buf = new BufferedWriter(new FileWriter(stuufs));
				buf.write(testgggg);
				buf.flush();
				buf.close();



			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (stuufs_dump.exists() == false){
			try {
				stuufs_dump.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String test = new GsonBuilder().setPrettyPrinting().create().toJson(allFoods);

		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(stuufs_dump));
			buf.write(test);
			buf.flush();
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//SALog.error(test);

		BufferedReader buff;
		try {
			buff = new BufferedReader(new FileReader(stuufs));
		} catch (FileNotFoundException e) {
			buff = null;
			return;
		}
		Overrides stuffies = new Gson().fromJson(buff, Overrides.class);
		for (ModOverrides mod : stuffies.foods){
			String modname = mod.mod;
			for (FoodOverride foodie : mod.foods){
				ItemStack istack = new ItemStack(Item.itemRegistry.getObject(new ResourceLocation(modname, foodie.name)));
				food.insertFood(istack, foodie.hunger, foodie.saturation);
			}
		}




		
	}

}