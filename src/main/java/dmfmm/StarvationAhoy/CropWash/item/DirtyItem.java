package dmfmm.StarvationAhoy.CropWash.item;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class DirtyItem extends Item {


//    IIcon dirtyOverlay;

    public DirtyItem() {
        //this.setTextureName("starvationahoy:dirty_overlay");
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem() instanceof IPlantable) {
            if (side != EnumFacing.UP) {
                return false;
            } else if (!player.canPlayerEdit(pos.offset(side), side, stack)) {
                return false;
            } else if (world.getBlockState(pos).getBlock().canSustainPlant(world, pos, EnumFacing.UP, (IPlantable) ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem()) && world.isAirBlock(pos.up())) {
                Item l = ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem();
                Block b = ModuleCropWash.d.getBlockFromDrop(l);
                world.setBlockState(pos.up(), b.getDefaultState());
                --stack.stackSize;
                return true;
            } else {
                return false;
            }
        }
        return false;
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
