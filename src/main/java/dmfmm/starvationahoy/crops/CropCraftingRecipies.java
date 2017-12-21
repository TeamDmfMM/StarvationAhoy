package dmfmm.starvationahoy.crops;

import dmfmm.starvationahoy.crops.init.CropBlockRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CropCraftingRecipies {


    public static void registerRecipies(){
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvartionahoy"), new ItemStack(CropBlockRegistry.CROP_WASHER), new Object[] {"   ", "w w", "www", 'w', "logWood"}).setRegistryName("starvationahoy:crop_washer"));
    }
}
