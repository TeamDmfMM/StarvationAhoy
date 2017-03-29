package dmfmm.StarvationAhoy.FoodStats.PlayerDiet;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodStats.FileFormats.IntLoadFormat;
import dmfmm.StarvationAhoy.FoodStats.FileManage.SaveFileLoad;
import dmfmm.StarvationAhoy.FoodStats.FoodStat;
import dmfmm.StarvationAhoy.FoodStats.FoodStatRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;

import java.io.IOException;
import java.util.*;

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

    public float weight;

    public String playername;
    public UUID playeruuid;

    public IntLoadFormat t;

    public Diet(String playername, UUID playeruuid, Random r){
        this.playername = playername;
        this.playeruuid = playeruuid;

        t = new IntLoadFormat(SaveFileLoad.getFileFrom("stats/" + playeruuid.toString()));

        try {
            this.load();
        } catch (IOException | NullPointerException e) {
            try {
                calories = (float) MathHelper.nextDouble(r, 1500, 1700);

                nutrient1 = 70;
                nutrient2 = 70;

                fat = 0;

                calculateNutrient();
                calculateWeight();

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
        calculateWeight();
        System.out.println(weight);
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

        weight = t.data.get("wht");

        calculateNutrient();



    }

    public void save() throws IOException {

        Map<String, Float> data = new HashMap<>();

        data.put("nut1", nutrient1);
        data.put("nut2", nutrient2);
        data.put("cal", calories);
        data.put("fat", fat);
        data.put("wht", weight);

        t.data = data;

        t.save();

    }

    public void calculateWeight(){

        weight = 200;
        float dif = 0;



        if (1200 <= calories && calories <= 2000){

            if (calories < 1600){
                dif = (float) -(0.00006 * Math.pow((calories - 1600), 2));
            }
            else {
                dif = (float) (0.00006 * Math.pow((calories - 1600), 2));
            }

        }
        else if (calories > 2000){
            dif = (float) ((0.0009 * Math.pow((calories - 2000), 2)) + 10);
        }
        else {
            dif = (float) -((0.0009 * Math.pow((calories - 1200), 2)) + 10);
        }



        weight += dif;

        dif = 0;
        dif = (float) (0.0004 * (Math.pow(fat, 2)));



        weight += dif;

        dif = 0;

        if (nutrient > 100){
            dif = (float) (0.0005 * Math.pow((nutrient - 100), 2));
        }
        else if (nutrient < 50){
            dif = (float) (0.004 * Math.pow((nutrient - 50), 2));
        }

        System.out.println(dif);

        weight += dif;








    }

    public void calculateNutrient(){
        float nut2min = ((40f / 100f) * nutrient1);
        float nut1min = ((40f / 100f) * nutrient2);
        float nut2minhar = ((10f / 100f) * nutrient1);
        float nut1minhar = ((10f / 100f) * nutrient2);
        ArrayList<Float> vals = new ArrayList<>();

        if (nutrient1 < nut1min){
            vals.add(nutrient1);
        }
        if (nutrient1 < nut1minhar){
            vals.add(nutrient1);
        }
        vals.add(nutrient1);

        if (nutrient2 < nut2min){
            vals.add(nutrient2);
        }
        if (nutrient2 < nut2minhar){
            vals.add(nutrient2);
        }
        vals.add(nutrient2);

        float sumy = 0;
        for (float i : vals) { sumy += i; }

        nutrient = sumy / (float) vals.size();

    }





}
