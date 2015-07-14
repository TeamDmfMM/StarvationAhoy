package dmfmm.StarvationAhoy.FoodStats;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class FoodStatRegistry {

    public Map<Item, FoodStat> registry = new HashMap<>();

    public static FoodStatRegistry instance = new FoodStatRegistry();

    public FoodStatRegistry() {
        registry.put(Items.apple, createnew(0, 4, 25, 3));
    }


    public FoodStat createnew(float n1, float n2, float cal, float fat){

        FoodStat t = new FoodStat();
        t.nutrient1 = n1;
        t.nutrient2 = n2;
        t.calories = cal;
        t.fat = fat;

        return t;

    }

}
