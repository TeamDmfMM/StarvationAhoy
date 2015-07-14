package dmfmm.StarvationAhoy.FoodStats.PlayerDiet;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodStats.FileFormats.IntLoadFormat;
import dmfmm.StarvationAhoy.FoodStats.FileManage.SaveFileLoad;
import dmfmm.StarvationAhoy.FoodStats.FoodStat;
import dmfmm.StarvationAhoy.FoodStats.FoodStatRegistry;
import net.minecraft.item.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class Diet {

    public float nutrient1;
    public float nutrient2;

    public float nutrient;

    public float calories;

    public float fat;

    public String playername;
    public UUID playeruuid;

    public IntLoadFormat t;

    public Diet(String playername, UUID playeruuid){
        this.playername = playername;
        this.playeruuid = playeruuid;

        t = new IntLoadFormat(SaveFileLoad.getFileFrom("stats/" + playeruuid.toString()));

        try {
            this.load();
        } catch (IOException | NullPointerException e) {
            try {
                this.save();
            } catch (IOException e1) {
                SALog.fatal("Failed to create a user's diet. THIS IS BAD!!!");
            }
        }

    }

    public void eatFood(Item item){
        FoodStat t = FoodStatRegistry.instance.registry.get(item);
        nutrient1 += t.nutrient1;
        nutrient2 += t.nutrient2;
        calories += t.calories;
        fat += t.fat;
        calculateNutrient();
        System.out.println(nutrient);
    }

    public void dump() {
        t.dump();
    }

    public void load() throws IOException {

        t.dump();
        t.undump();

        t.load();

        nutrient1 = t.data.get("nut1");
        nutrient2 = t.data.get("nut2");

        calories = t.data.get("cal");

        fat = t.data.get("fat");

        calculateNutrient();



    }

    public void save() throws IOException {

        Map<String, Float> data = new HashMap<>();

        data.put("nut1", nutrient1);
        data.put("nut2", nutrient2);
        data.put("cal", calories);
        data.put("fat", fat);

        t.data = data;

        t.save();

    }

    public void calculateNutrient(){
        float nut2min = ((20f / 100f) * nutrient1);
        float nut1min = ((20f / 100f) * nutrient2);
        ArrayList<Float> vals = new ArrayList<>();

        if (nutrient1 < nut1min){
            vals.add(nutrient1);
        }
        vals.add(nutrient1);

        if (nutrient2 < nut2min){
            vals.add(nutrient2);
        }
        vals.add(nutrient2);

        float sumy = 0;
        for (float i : vals) { sumy += i; }

        nutrient = sumy / (float) vals.size();

    }





}
