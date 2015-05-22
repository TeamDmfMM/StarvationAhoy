package dmfmm.StarvationAhoy.CropWash.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

/**
 * Created by matthew on 22/05/15.
 */
public class DirtyItem extends Item {


    IIcon dirtyOverlay;

    public DirtyItem() {

    }

    @Override
    public int getRenderPasses(int metadata) {
        return 2;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }


    public static ItemStack createDirtyItem(ItemStack original){

        ItemStack dirty = new ItemStack(, original.stackSize);
        dirty.stackTagCompound.setTag("Original", original.writeToNBT(new NBTTagCompound()));
        return dirty;

    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {

        ItemStack original = ItemStack.loadItemStackFromNBT(stack.stackTagCompound.getCompoundTag("Original"));

        if (renderPass == 0){
            return original.getItem().getIcon(original, renderPass, player, usingItem, useRemaining);
        }

        else {
            return dirtyOverlay;
        }


    }

    @Override
    public void registerIcons(IIconRegister p_94581_1_) {
        dirtyOverlay = p_94581_1_.registerIcon("starvationahoy:dirty_overlay");
    }
}
