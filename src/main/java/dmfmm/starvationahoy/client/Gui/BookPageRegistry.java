package dmfmm.starvationahoy.client.Gui;


import dmfmm.starvationahoy.core.items.ItemLoad;
import dmfmm.starvationahoy.core.util.CRef;
import dmfmm.starvationahoy.core.util.DualObjectLink;
import dmfmm.starvationahoy.crops.ModuleCropWash;
import dmfmm.starvationahoy.crops.item.DirtyItem;
import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.item.MItemLoader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

class BookPageRegistry {


    /**
     * Array of book pages, replaces ClickTab from ExtraFood
     * Itemstack is item that is rendered next to name, String is used for localization
     * if Itemstack is Null, the book renders a centered title
     * Pages should be added in order wanted
     */
    private static Map<String, DualObjectLink<ItemStack, Boolean>> bookTabs;

    public BookPageRegistry(){
        bookTabs = new LinkedHashMap<>();

        addTab("baseModule", new DualObjectLink(null, true));
        addTab("gettingStarted", new DualObjectLink(new ItemStack(ItemLoad.infoBook), true));
        addTab("starvePotion", new DualObjectLink(new ItemStack(ItemLoad.HungerPotion), true));
        addTab( "healthArmor", new DualObjectLink(new ItemStack(ItemLoad.stat_helm), true));
        addTab( "moduleMeat", new DualObjectLink(null, CRef.useMeatOverride()));
        addTab("prepingFood", new DualObjectLink(new ItemStack(MeatBlockRegistry.MeatHanger), CRef.useMeatOverride()));
        addTab("animalKilling", new DualObjectLink(new ItemStack(MItemLoader.ButcherKnife), CRef.useMeatOverride()));
        addTab("animalSkinning", new DualObjectLink(new ItemStack(MItemLoader.filetKnife), CRef.useMeatOverride()));
        addTab("animalCooking", new DualObjectLink(new ItemStack(MeatBlockRegistry.HoldingStick), CRef.useMeatOverride()));
        addTab("pigLeg", new DualObjectLink(new ItemStack(MItemLoader.cookedpigleg), CRef.useMeatOverride()));
        addTab("moduleCropwash", new DualObjectLink(null, CRef.useCropwash()));
        addTab("washBarrel", new DualObjectLink(new ItemStack(ModuleCropWash.blockCropWasher), CRef.useCropwash()));
        addTab("dirtyItems", new DualObjectLink(DirtyItem.createDirtyItem(new ItemStack(Items.CARROT, 1)), CRef.useCropwash()));
        addTab("moduleMisc", new DualObjectLink(null, true));
        addTab("levelReduction", new DualObjectLink(new ItemStack(Items.WHEAT), true));
        addTab("editLevels", new DualObjectLink(new ItemStack(Blocks.COMMAND_BLOCK), true));

    }


    public static void addTab(String string, DualObjectLink obj){
        bookTabs.put(string, obj);
    }

    public static Map<String, DualObjectLink<ItemStack, Boolean>> getBookTabs(){
        return bookTabs;
    }
}
