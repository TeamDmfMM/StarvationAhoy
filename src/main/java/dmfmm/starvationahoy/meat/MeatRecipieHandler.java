package dmfmm.starvationahoy.meat;


import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class MeatRecipieHandler {

    public static void registerCraftingRecipies(){
        smelt(MeatItemRegistry.PIG_LEG, MeatItemRegistry.COOKED_PIG_LEG, 0.5F);

        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvartionahoy"), new ItemStack(MeatItemRegistry.FILET_KNIFE),
                new Object[]{
                "ii ",
                " i ",
                " s ", 'i', "ingotIron", 's', "stickWood"}).setRegistryName("starvationahoy:filet_knife"));
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvartionahoy"), new ItemStack(MeatItemRegistry.BUTCHERS_KNIFE),
                new Object[]{
                "ii ",
                "ii ",
                " s ", 'i', "ingotIron", 's', "stickWood"}).setRegistryName("starvationahoy:butcher_knife"));
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvartionahoy"), new ItemStack(MeatBlockRegistry.MEAT_HANGER),
                new Object[]{
                "www",
                "wiw",
                " i ", 'i', "ingotIron", 'w', "plankWood"}).setRegistryName("starvationahoy:meat_hanger"));
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("starvartionahoy"), new ItemStack(MeatBlockRegistry.HOLDING_STICK),
                new Object[]{
                " s ",
                " s ",
                "sps", 's', "stickWood", 'p', "plankWood"}).setRegistryName("starvationahoy:holding_stick"));
    }

    private static void smelt(Object in, Item out, Float value){
        Item i;
        if(in instanceof Block){
            i = Item.getItemFromBlock((Block) in);
        } else{
            i = (Item) in;
        }
        if(i != null) {
            GameRegistry.addSmelting(new ItemStack(i), new ItemStack(out), value);
        }
    }
}
