package dmfmm.starvationahoy.Client.Gui.book_gui;

import dmfmm.starvationahoy.Core.util.SALog;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class CraftingProxyHelper {

    IRecipe recipe;
    ArrayList<ArrayList<ItemStack>> items = new ArrayList<>();

    public CraftingProxyHelper(ItemStack target) {
        for (IRecipe r : CraftingManager.getInstance().getRecipeList()) {
            try {
                if (r.getRecipeOutput().getItem() == target.getItem()) {
                    if (!(r instanceof ShapelessRecipes || r instanceof ShapedRecipes || r instanceof ShapedOreRecipe || r instanceof ShapelessOreRecipe)) {
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
            for (final ItemStack i : ((ShapedRecipes) recipe).recipeItems) {
                if (i == null) {
                    items.add(new ArrayList<ItemStack>() {{add(null);}});
                }
                else {
                    if (i.getMetadata() != 32767){
                        items.add(new ArrayList<ItemStack>() {{add(i);}});
                    }
                    else {
                        NonNullList<ItemStack> subItems = NonNullList.create();
                        i.getItem().getSubItems(i.getItem(), i.getItem().getCreativeTab(), subItems);
                        items.add(new ArrayList<>(Arrays.asList(subItems.toArray(new ItemStack[subItems.size()]))));
                    }
                }
            }
        }
        else if (recipe instanceof ShapelessRecipes){
            for (final ItemStack i : ((ShapelessRecipes) recipe).recipeItems) {
                if (i == null) {
                    items.add(new ArrayList<ItemStack>() {{add(null);}});
                }
                else {
                    if (i.getMetadata() != 32767){
                        items.add(new ArrayList<ItemStack>() {{add(i);}});
                    }
                    else {
                        NonNullList<ItemStack> subItems = NonNullList.create();
                        i.getItem().getSubItems(i.getItem(), i.getItem().getCreativeTab(), subItems);
                        items.add(new ArrayList<>(Arrays.asList(subItems.toArray(new ItemStack[subItems.size()]))));
                    }
                }
            }
            while (items.size() < 9) {
                items.add(new ArrayList<ItemStack>() {{add(null);}});
            }
        }
        else if (recipe instanceof ShapedOreRecipe){
            for (Object object: ((ShapedOreRecipe) recipe).getInput()){
                if (object instanceof ItemStack) {
                    final ItemStack i = (ItemStack) object;
                    if (i.getMetadata() != 32767){
                        items.add(new ArrayList<ItemStack>() {{add(i);}});
                    }
                    else {
                        NonNullList<ItemStack> subItems = NonNullList.create();
                        i.getItem().getSubItems(i.getItem(), i.getItem().getCreativeTab(), subItems);
                        items.add(new ArrayList<>(Arrays.asList(subItems.toArray(new ItemStack[subItems.size()]))));
                    }
                }
                else if (object instanceof List){
                    ArrayList<ItemStack> newItems = new ArrayList<>();
                    for (ItemStack istack : (List<ItemStack>)object){
                        if (istack.getMetadata() != 32767){
                            newItems.add(istack);
                        }
                        else {
                            NonNullList<ItemStack> subItems = NonNullList.create();
                            istack.getItem().getSubItems(istack.getItem(), istack.getItem().getCreativeTab(), subItems);
                            newItems.addAll(subItems);
                        }
                    }
                    items.add(newItems);
                }
                else if (object == null) {
                    items.add(new ArrayList<ItemStack>() {{add(null);}});
                }
            }
        }
        else if (recipe instanceof ShapelessOreRecipe){
            for (Object object: ((ShapelessOreRecipe) recipe).getInput()){
                if (object instanceof ItemStack) {
                    final ItemStack i = (ItemStack) object;
                    if (i.getMetadata() != 32767){
                        items.add(new ArrayList<ItemStack>() {{add(i);}});
                    }
                    else {
                        NonNullList<ItemStack> subItems = NonNullList.create();
                        i.getItem().getSubItems(i.getItem(), i.getItem().getCreativeTab(), subItems);
                        items.add(new ArrayList<>(Arrays.asList(subItems.toArray(new ItemStack[subItems.size()]))));
                    }
                }
                else if (object instanceof List){
                    ArrayList<ItemStack> newItems = new ArrayList<>();
                    for (ItemStack istack : (List<ItemStack>)object){
                        if (istack.getMetadata() != 32767){
                            newItems.add(istack);
                        }
                        else {
                            NonNullList<ItemStack> subItems = NonNullList.create();
                            istack.getItem().getSubItems(istack.getItem(), istack.getItem().getCreativeTab(), subItems);
                            newItems.addAll(subItems);
                        }
                    }
                    items.add(newItems);
                }
                else if (object == null) {
                    items.add(new ArrayList<ItemStack>() {{add(null);}});
                }
            }
        }
    }

    public ItemStack getOutput(){
        return recipe.getRecipeOutput();
    }

    public ArrayList<ItemStack> getItems(int ticks) {
        ArrayList<ItemStack> output = new ArrayList<>();
        for (ArrayList<ItemStack> itemStacks : items){
            int frame = (ticks / 10) % itemStacks.size();
            output.add(itemStacks.get(frame));
        }
        while (output.size() < 9) {
            output.add(null);
        }
        return output;
    }
}
