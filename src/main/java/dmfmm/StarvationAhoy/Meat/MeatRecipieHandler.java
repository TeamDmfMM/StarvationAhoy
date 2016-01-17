package dmfmm.StarvationAhoy.Meat;

import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class MeatRecipieHandler {

    public static void registerCraftingRecipies(){
        smelt(MItemLoader.pigleg, MItemLoader.cookedpigleg, 0.5F);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MItemLoader.filetKnife), new Object[]{
                "ii ",
                " i ",
                " s ", 'i', "ingotIron", 's', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MItemLoader.ButcherKnife), new Object[]{
                "ii ",
                "ii ",
                " s ", 'i', "ingotIron", 's', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MBlockLoader.MeatHanger), new Object[]{
                "www",
                "wiw",
                " i ", 'i', "ingotIron", 'w', "plankWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MBlockLoader.HoldingStick), new Object[]{
                " s ",
                " s ",
                "sps", 's', "stickWood", 'p', "plankWood"}));
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
