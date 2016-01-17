package dmfmm.StarvationAhoy.CropWash;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by David on 2015-08-02.
 */
public class CropCraftingRecipies {


    public static void registerRecipies(){
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleCropWash.blockCropWasher), new Object[] {"   ", "w w", "www", 'w', "logWood"}));
    }
}
