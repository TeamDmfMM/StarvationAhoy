package dmfmm.StarvationAhoy.FoodEdit.FoodSet.User;


public class FoodOverrideHelper {

    public static FoodOverride makeFoodOverride(String name, int hunger, float sturan){
        FoodOverride returner = new FoodOverride();
        returner.name = name;
        returner.hunger = hunger;
        returner.saturation = sturan;
        return returner;
    }

    public static void addFoodOverrideTo(FoodOverride f, ModOverrides m){
        int newsize = m.foods.length + 1;
        FoodOverride[] newfoods = new FoodOverride[newsize];
        System.arraycopy(m.foods, 0, newfoods, 0, newsize-1 );
        newfoods[newsize-1] = f;
        m.foods = newfoods;
    }

    public static ModOverrides makeModOverride(String name){
        ModOverrides toReturn = new ModOverrides();
        toReturn.mod = name;
        toReturn.foods = new FoodOverride[0];
        return toReturn;
    }

}
