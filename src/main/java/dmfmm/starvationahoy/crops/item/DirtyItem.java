package dmfmm.starvationahoy.crops.item;

import dmfmm.starvationahoy.crops.ModuleCropWash;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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

    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        if(new ItemStack(stack.getTagCompound().getCompoundTag("Original")).getItem() instanceof IPlantable) {
            if (side != EnumFacing.UP) {
                return EnumActionResult.PASS;
            } else if (!player.canPlayerEdit(pos.offset(side), side, stack)) {
                return EnumActionResult.PASS;
            } else if (world.getBlockState(pos).getBlock().canSustainPlant(world.getBlockState(pos), world, pos, EnumFacing.UP, (IPlantable) new ItemStack(stack.getTagCompound().getCompoundTag("Original")).getItem()) && world.isAirBlock(pos.up())) {
                Item l = new ItemStack(stack.getTagCompound().getCompoundTag("Original")).getItem();
                Block b = ModuleCropWash.d.getBlockFromDrop(l);
                world.setBlockState(pos.up(), b.getDefaultState());
                stack.shrink(1);
                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.PASS;
    }


    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String dirty = I18n.format("starvationahoy.misc.dirty");
        String original = "";
        if(stack.hasTagCompound()) {
             original = new ItemStack(stack.getTagCompound().getCompoundTag("Original")).getDisplayName();
        }
        return dirty + " " + original;
    }

    public static ItemStack createDirtyItem(ItemStack original){

        ItemStack dirty = new ItemStack(ModuleCropWash.cropItemLoader.items.get("dirty_item"), original.getCount());
        dirty.setTagCompound(new NBTTagCompound());
        dirty.getTagCompound().setTag("Original", original.writeToNBT(new NBTTagCompound()));
        return dirty;

    }




}
