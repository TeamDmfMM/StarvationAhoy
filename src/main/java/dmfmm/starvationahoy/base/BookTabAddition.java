package dmfmm.starvationahoy.base;

import dmfmm.starvationahoy.core.SATabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.GuiButtonRecipeTab;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.client.util.RecipeBookClient;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dmf444 on 12/23/2017. Code originally written for StarvationAhoy.
 */
public class BookTabAddition {

    private static boolean hasRegistered = false;

    public static List<IRecipe> hackTheBook(Set<String> s) {
        List<IRecipe> returningRecipes = new ArrayList<>();
        List<IRecipe> recipes = ForgeRegistries.RECIPES.getValues();
        for(IRecipe recipe : recipes) {
            String recStr = recipe.getGroup().split(":")[0];
            if(s.contains(recStr)){
                returningRecipes.add(recipe);
            }
        }
        return returningRecipes;
    }

    private static RecipeList getRecipeIfExist(IRecipe recipe) {
        for(RecipeList recipeList : RecipeBookClient.ALL_RECIPES) {
            if (recipeList.getRecipes().contains(recipe)) {
                return recipeList;
            }
        }
        return null;
    }

    @SubscribeEvent
    public void hackTheGui(GuiScreenEvent.InitGuiEvent.Pre event) {
        if (event.getGui() instanceof IRecipeShownListener) {
            if (!hasRegistered) {
                IRecipeShownListener listener = (IRecipeShownListener) event.getGui();
                GuiRecipeBook book = listener.func_194310_f();

                GuiButtonRecipeTab saTab = new GuiButtonRecipeTab(0, SATabs.INSTANCE);
                try {
                    Field field = book.getClass().getDeclaredField("recipeTabs");
                    field.setAccessible(true);
                    List<GuiButtonRecipeTab> type = (List<GuiButtonRecipeTab>) field.get(book);
                    type.add(saTab);

                    ArrayList<RecipeList> arrayList = new ArrayList<>();

                    //Method method = RecipeBookClient.class.getDeclaredMethod("newRecipeList", CreativeTabs.class);
                    //method.setAccessible(true);
                    //RecipeList list = (RecipeList) method.invoke(null, SATabs.INSTANCE);
                    HashSet<String> groupNames = new HashSet<>();
                    groupNames.add("starvationahoy");
                    List<IRecipe> rec = hackTheBook(groupNames);
                    for (IRecipe recipe : rec) {
                        RecipeList rList = getRecipeIfExist(recipe);
                        if (rList == null) {
                            rList = new RecipeList();
                            rList.add(recipe);
                        }
                        Minecraft.getMinecraft().player.getRecipeBook().unlock(recipe);
                        rList.updateKnownRecipes(Minecraft.getMinecraft().player.getRecipeBook());
                        arrayList.add(rList);
                    }
                    RecipeBookClient.RECIPES_BY_TAB.put(SATabs.INSTANCE, arrayList);

                } catch (Exception e) {
                }
                hasRegistered = true;
            }
        }
    }

    @SubscribeEvent
    public void resetTheVar(GuiOpenEvent event) {
        if(event.getGui() == null && this.hasRegistered) {
            this.hasRegistered = false;
        }
    }
}
