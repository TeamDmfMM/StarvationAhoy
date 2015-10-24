package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodEdit.FileReader.FileLoader;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.FoodOverride;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.FoodOverrideHelper;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.ModOverrides;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.Overrides;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


import java.io.*;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class FoodChanger {


    public static class FoodNotFoundException extends Exception {
        public FoodNotFoundException(String s){
            super(s);

        }
    }

    public static void change(Item i, int hunger, float sturan) throws IOError, IOException, FoodNotFoundException {
        // Read gson
        File fil = new File(FileLoader.getCfgPath(), "food.json");


        BufferedReader buff = new BufferedReader(new FileReader(fil));
        Overrides stuffies = new Gson().fromJson(buff, Overrides.class);
        String[] comps = Item.itemRegistry.getNameForObject(i).split(":");
        int sind = 0;
        int find = 0;
        boolean found = false;
        boolean modfound = false;
        try {
            for (ModOverrides m : stuffies.foods) {
                if (m.mod.equals(comps[0])) {
                    modfound = true;
                    for (FoodOverride fo : m.foods) {
                        if (fo.name.equals(comps[1])) {
                           //SALog.error("om nom nom " + find + " " + sind);
                            fo.saturation = sturan;
                            fo.hunger = hunger;
                            fo.name = comps[1];
                            found = true;
                            break;
                        }
                        find += 1;
                    }
                    //if (found){
                        break;


                }
                sind += 1;
            }


            stuffies.foods[sind].foods[find].hunger = hunger;
            stuffies.foods[sind].foods[find].saturation = sturan;
        }
        catch (ArrayIndexOutOfBoundsException e){
            if (found == false && modfound == true){

                int newsize = stuffies.foods[sind].foods.length + 1;
                FoodOverride[] newfoods = new FoodOverride[newsize];
                System.arraycopy(stuffies.foods[sind].foods, 0, newfoods, 0, newsize-1 );
                newfoods[newsize-1] = FoodOverrideHelper.makeFoodOverride(comps[1], hunger, sturan);
                stuffies.foods[sind].foods = newfoods;

            }
            else if (found == false && modfound == false){

                int newsize = stuffies.foods.length+1;
                ModOverrides[] newmods = new ModOverrides[newsize];
                System.arraycopy(stuffies.foods, 0, newmods, 0, newsize-1 );
                ModOverrides newmod = FoodOverrideHelper.makeModOverride(comps[0]);
                FoodOverrideHelper.addFoodOverrideTo(FoodOverrideHelper.makeFoodOverride(comps[1], hunger, sturan), newmod);
                newmods[newsize-1] = newmod;

            }
        }
        String testgggg = new GsonBuilder().setPrettyPrinting().create().toJson(stuffies);
        BufferedWriter buf = new BufferedWriter(new FileWriter(fil));
        buf.write(testgggg);
        dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods.changeFood(new ItemStack(i), hunger, sturan);
        buf.flush();
        buf.close();

    }

}
