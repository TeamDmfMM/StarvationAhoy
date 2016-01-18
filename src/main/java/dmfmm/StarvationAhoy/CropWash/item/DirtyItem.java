package dmfmm.StarvationAhoy.CropWash.item;

import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class DirtyItem extends Item {


//    IIcon dirtyOverlay;

    public DirtyItem() {
        //this.setTextureName("starvationahoy:dirty_overlay");
    }



    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String dirty = StatCollector.translateToLocal("starvationahoy.misc.dirty");
        String original = ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getDisplayName();
        return dirty + " " + original;
    }

    public static ItemStack createDirtyItem(ItemStack original){

        ItemStack dirty = new ItemStack(ModuleCropWash.cropItemLoader.items.get("dirty_item"), original.stackSize);
        dirty.setTagCompound(new NBTTagCompound());
        dirty.getTagCompound().setTag("Original", original.writeToNBT(new NBTTagCompound()));
        return dirty;

    }




}
