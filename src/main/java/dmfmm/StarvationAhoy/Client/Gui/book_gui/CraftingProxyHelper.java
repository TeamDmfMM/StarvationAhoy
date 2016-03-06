package dmfmm.StarvationAhoy.Client.Gui.book_gui;

import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class CraftingProxyHelper {

    IRecipe recipe;
    ArrayList<ItemStack> items = new ArrayList<>();

    public CraftingProxyHelper(ItemStack target) {
        for (IRecipe r : CraftingManager.getInstance().getRecipeList()) {
            try {
                if (r.getRecipeOutput().getItem() == target.getItem()) {
                    if (!(r instanceof ShapelessRecipes || r instanceof ShapedRecipes)) {
                        continue;
                    }
                    recipe = r;
                }
            }
            catch (NullPointerException e) {

            }
        }
        if (recipe == null) {
            SALog.fatal("could not find recipe. nullpointer will almost certainly follow");
        }
        eat();


    }

    private void eat(){
        if (recipe instanceof ShapedRecipes) {
            Collections.addAll(items, ((ShapedRecipes) recipe).recipeItems);
        }
        else if (recipe instanceof ShapelessRecipes){
            items.addAll(((ShapelessRecipes) recipe).recipeItems);
            while (items.size() < 9) {
                items.add(null);
            }
        }
    }

    public ItemStack getOutput(){
        return recipe.getRecipeOutput();
    }

    public ArrayList<ItemStack> getItems() {
        return this.items;
    }
}
