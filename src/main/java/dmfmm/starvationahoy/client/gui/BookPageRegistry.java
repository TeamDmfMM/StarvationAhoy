package dmfmm.starvationahoy.client.gui;


import dmfmm.starvationahoy.core.init.CoreItemRegistry;
import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.core.util.DualObjectLink;
import dmfmm.starvationahoy.crops.init.CropBlockRegistry;
import dmfmm.starvationahoy.crops.item.DirtyItem;
import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

class BookPageRegistry {


    /**
     * Array of INFO_BOOK pages, replaces ClickTab from ExtraFood
     * Itemstack is item that is rendered next to name, String is used for localization
     * if Itemstack is Null, the INFO_BOOK renders a centered title
     * Pages should be added in order wanted
     */
    private static Map<String, DualObjectLink<ItemStack, Boolean>> bookTabs;

    public BookPageRegistry(){
        bookTabs = new LinkedHashMap<>();

        addTab(new BookPages().setPageName("baseModule"));
        addTab(new BookPages().setPageName("gettingStarted").setDisplayImage(CoreItemRegistry.INFO_BOOK));
        addTab(new BookPages().setPageName("starvePotion").setDisplayImage(CoreItemRegistry.HUNGER_POTION));
        addTab(new BookPages().setPageName("healthArmor").setDisplayImage(CoreItemRegistry.STAT_HELM));

        boolean displaySet = ConfigHandler.useMeatOverride();
        addTab(new BookPages().setPageName("moduleMeat").shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("prepingFood").setDisplayImage(MeatBlockRegistry.MEAT_HANGER).shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("animalKilling").setDisplayImage(MeatItemRegistry.BUTCHERS_KNIFE).shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("animalSkinning").setDisplayImage(MeatItemRegistry.FILET_KNIFE).shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("animalCooking").setDisplayImage(MeatBlockRegistry.HOLDING_STICK).shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("pigLeg").setDisplayImage(MeatItemRegistry.COOKED_PIG_LEG).shouldDisplayPage(displaySet));

        displaySet = ConfigHandler.useCropwash();
        addTab(new BookPages().setPageName("moduleCropwash").shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("washBarrel").setDisplayImage(CropBlockRegistry.CROP_WASHER).shouldDisplayPage(displaySet));
        addTab(new BookPages().setPageName("dirtyItems").setDisplayImage(DirtyItem.createDirtyItem(new ItemStack(Items.CARROT, 1))).shouldDisplayPage(displaySet));


        addTab("moduleMisc", new DualObjectLink(null, true));
        addTab("levelReduction", new DualObjectLink(new ItemStack(Items.WHEAT), true));
        addTab("editLevels", new DualObjectLink(new ItemStack(Blocks.COMMAND_BLOCK), true));

    }


    public static void addTab(String string, DualObjectLink obj){
        bookTabs.put(string, obj);
    }

    public static void addTab(BookPages bookPages){
        bookTabs.put(bookPages.getName(), bookPages.getRegistryObject());
    }

    public static Map<String, DualObjectLink<ItemStack, Boolean>> getBookTabs(){
        return bookTabs;
    }
}
