package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FileReader.FileLoader;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.FoodOverride;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.ModOverrides;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.Overrides;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.*;

/**
 * Created by Matthew on 3/19/2015.
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
        try {
            for (ModOverrides m : stuffies.foods) {
                if (m.mod.equals(comps[0])) {
                    for (FoodOverride fo : m.foods) {
                        if (fo.name.equals(comps[1])) {
                            SALog.error("om nom nom " + find + " " + sind);
                            fo.saturation = sturan;
                            fo.hunger = hunger;
                            fo.name = comps[1];
                            break;
                        }
                        find += 1;
                    }

                    break;
                }
                sind += 1;
            }

            if (find == -1) {
                throw new FoodNotFoundException("Food not found");
            }
            stuffies.foods[sind].foods[find].hunger = hunger;
            stuffies.foods[sind].foods[find].saturation = sturan;
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new FoodNotFoundException("Food not found");
        }
        String testgggg = new GsonBuilder().setPrettyPrinting().create().toJson(stuffies);
        BufferedWriter buf = new BufferedWriter(new FileWriter(fil));
        buf.write(testgggg);
        buf.flush();
        buf.close();

    }

}
