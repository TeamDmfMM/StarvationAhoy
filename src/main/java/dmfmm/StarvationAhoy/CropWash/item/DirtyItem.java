package dmfmm.StarvationAhoy.CropWash.item;

import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
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

    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        if(ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem() instanceof IPlantable) {
            if (side != EnumFacing.UP) {
                return EnumActionResult.PASS;
            } else if (!player.canPlayerEdit(pos.offset(side), side, stack)) {
                return EnumActionResult.PASS;
            } else if (world.getBlockState(pos).getBlock().canSustainPlant(world.getBlockState(pos), world, pos, EnumFacing.UP, (IPlantable) ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem()) && world.isAirBlock(pos.up())) {
                Item l = ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem();
                Block b = ModuleCropWash.d.getBlockFromDrop(l);
                world.setBlockState(pos.up(), b.getDefaultState());
                --stack.stackSize;
                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.PASS;
    }


    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String dirty = I18n.translateToLocal("starvationahoy.misc.dirty");
        String original = "";
        if(stack.hasTagCompound()) {
             original = ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getDisplayName();
        }
        return dirty + " " + original;
    }

    public static ItemStack createDirtyItem(ItemStack original){

        ItemStack dirty = new ItemStack(ModuleCropWash.cropItemLoader.items.get("dirty_item"), original.stackSize);
        dirty.setTagCompound(new NBTTagCompound());
        dirty.getTagCompound().setTag("Original", original.writeToNBT(new NBTTagCompound()));
        return dirty;

    }




}
