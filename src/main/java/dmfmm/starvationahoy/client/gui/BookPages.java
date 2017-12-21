package dmfmm.starvationahoy.client.gui;

import dmfmm.starvationahoy.core.util.DualObjectLink;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by dmf444 on 12/20/2017. Code originally written for StarvationAhoy.
 */
public class BookPages {

    private String pageName;
    private ItemStack displayImage = null;
    private boolean displayPage = true;

    protected BookPages setPageName(String name) {
        this.pageName = name;
        return this;
    }

    protected BookPages setDisplayImage(Item item) {
        this.displayImage = new ItemStack(item);
        return this;
    }

    protected BookPages setDisplayImage(Block block) {
        this.displayImage = new ItemStack(block);
        return this;
    }

    protected BookPages setDisplayImage(ItemStack stack) {
        this.displayImage = stack;
        return this;
    }

    protected BookPages shouldDisplayPage(boolean bool) {
        this.displayPage = bool;
        return this;
    }

    protected String getName() {
        return this.pageName;
    }

    protected DualObjectLink<ItemStack, Boolean> getRegistryObject() {
        if (this.displayImage != null) {
            return new DualObjectLink<>(this.displayImage, this.displayPage);
        } else {
            return new DualObjectLink<>(null, this.displayPage);
        }
    }
}
