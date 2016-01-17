package dmfmm.StarvationAhoy.CropWash.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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



    public DirtyItem() {
        //this.setTextureName("starvationahoy:dirty_overlay");
    }


    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String dirty = StatCollector.translateToLocal("starvationahoy.misc.dirty");
        String original =  ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getDisplayName();
        return dirty + " " + original;
    }


    public static ItemStack createDirtyItem(ItemStack original){

        ItemStack dirty = new ItemStack(ModuleCropWash.cropItemLoader.items.get("dirty_item"), original.stackSize);
        dirty.setTagCompound(new NBTTagCompound());
        dirty.getTagCompound().setTag("Original", original.writeToNBT(new NBTTagCompound()));
        return dirty;

    }



    /*@Override
    public IIcon getIcon(ItemStack stack, int renderPass) {


    ItemStack original = ItemStack.loadItemStackFromNBT(stack.stackTagCompound.getCompoundTag("Original"));

        if (renderPass == 0){
            return original.getItem().getIcon(stack, renderPass);
        }

        else {
            return dirtyOverlay;
        }


    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister){
        super.registerIcons(iconRegister);
        dirtyOverlay = iconRegister.registerIcon("starvationahoy:dirty_overlay");
    }*/
}
