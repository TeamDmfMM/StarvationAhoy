package dmfmm.StarvationAhoy.Core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by David on 2015-08-03.
 */
public class CoreRecipies {

    public static void registerRecipies(){
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoad.stat_helm, new Object[] {"   ", "ihi", "rgr", 'i', "ingotIron", 'h', new ItemStack(Items.IRON_HELMET), 'r', "dustRedstone", 'g', "paneGlass"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoad.stat_chest, new Object[]{"bnb", "lcl", "lel", 'b', new ItemStack(Items.BLAZE_ROD), 'n', "ingotBrickNether", 'l', new ItemStack(Items.LEATHER), 'c', new ItemStack(Items.IRON_CHESTPLATE), 'e', new ItemStack(Items.ENDER_EYE)}));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemLoad.HungerPotion), new ItemStack(Items.POTIONITEM, 1, 0), new ItemStack(Items.ROTTEN_FLESH));
    }


}
