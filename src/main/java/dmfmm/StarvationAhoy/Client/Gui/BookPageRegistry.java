package dmfmm.StarvationAhoy.Client.Gui;


import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import dmfmm.StarvationAhoy.CropWash.item.CropItemLoader;
import dmfmm.StarvationAhoy.CropWash.item.DirtyItem;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookPageRegistry {


    /**
     * Array of book pages, replaces ClickTab from ExtraFood
     * Itemstack is item that is rendered next to name, String is used for localization
     * if Itemstack is Null, the book renders a centered title
     * Pages should be added in order wanted
     */
    private static Map<String,ItemStack> bookTabs;

    public BookPageRegistry(){
        bookTabs = new HashMap<>();

        addTab(null, "baseModule");
        addTab(new ItemStack(ItemLoad.infoBook), "gettingStarted");
        addTab(new ItemStack(ItemLoad.HungerPotion), "starvePotion");
        addTab(new ItemStack(ItemLoad.stat_helm), "healthArmor");
        addTab(null, "moduleMeat");
        addTab(new ItemStack(MBlockLoader.MeatHanger), "prepingFood");
        addTab(new ItemStack(MItemLoader.ButcherKnife), "animalKilling");
        addTab(new ItemStack(MItemLoader.filetKnife), "animalSkinning");
        addTab(new ItemStack(MBlockLoader.HoldingStick), "animalCooking");
        addTab(new ItemStack(MItemLoader.cookedpigleg), "pigLeg");
        addTab(null, "moduleCropwash");
        addTab(new ItemStack(ModuleCropWash.blockCropWasher), "washBarrel");
        addTab(DirtyItem.createDirtyItem(new ItemStack(Items.carrot, 1)), "dirtyItems");
        addTab(null, "moduleMisc");
        addTab(new ItemStack(Items.wheat), "levelReduction");
        addTab(new ItemStack(Blocks.command_block), "editLevels");

    }


    public static void addTab(ItemStack stack, String string){
        bookTabs.put(string, stack);
    }

    public static Map<String, ItemStack> getBookTabs(){
        return bookTabs;
    }
}
