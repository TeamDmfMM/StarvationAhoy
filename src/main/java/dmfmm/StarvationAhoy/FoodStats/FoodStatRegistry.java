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
        registry.put(Items.APPLE, createnew(0.05f, 4.3f, 25, 3.28f));
        registry.put(Items.COOKED_BEEF, createnew(4.9f, 0.02f, 60, 0.59f));
        registry.put(Items.BAKED_POTATO, createnew(0.05f, 4.45f, 35, 1.02f));
        registry.put(Items.COOKED_CHICKEN, createnew(3.9f, 0.04f, 50, 0.67f));
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
