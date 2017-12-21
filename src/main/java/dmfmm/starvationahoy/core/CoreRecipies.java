package dmfmm.starvationahoy.core;

import dmfmm.starvationahoy.core.init.CoreItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by David on 2015-08-03.
 */
public class CoreRecipies {

    public static void registerRecipies(){
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvationahoy"), CoreItemRegistry.STAT_HELM,
                new Object[] {
                        "   ",
                        "ihi",
                        "rgr",
                        'i', "ingotIron", 'h', new ItemStack(Items.IRON_HELMET),
                        'r', "dustRedstone", 'g', "paneGlass"}).setRegistryName("starvationahoy:stat_helm"));
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvationahoy"), CoreItemRegistry.STAT_CHEST,
                new Object[]{
                        "bnb",
                        "lcl",
                        "lel",
                        'b', new ItemStack(Items.BLAZE_ROD), 'n', "ingotBrickNether",
                        'l', new ItemStack(Items.LEATHER), 'c', new ItemStack(Items.IRON_CHESTPLATE),
                        'e', new ItemStack(Items.ENDER_EYE)}).setRegistryName("starvationahoy:stat_chest"));
    }


}
