package dmfmm.StarvationAhoy.CropWash.Block.tilentity;

import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class TileEntityCropWasher extends TileEntity implements IFluidTank{


    FluidTank theTank;

    public TileEntityCropWasher(){
        theTank = new FluidTank( 1000 );
    }

    public ItemStack wash(ItemStack item){

        if (theTank.getFluidAmount() >= 200 * item.stackSize){
            if (item.getItem() == ModuleCropWash.cropItemLoader.getItem("dirty_item")){
                ItemStack original = ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("Original"));
                original.stackSize = item.stackSize;
                theTank.drain(200, true);
                return original;
            }
        }
        return item;
    }


    @Override
    public FluidStack getFluid() {
        return theTank.getFluid();
    }

    @Override
    public int getFluidAmount() {
        return theTank.getFluidAmount();
    }

    @Override
    public int getCapacity() {
        return theTank.getCapacity();
    }

    @Override
    public FluidTankInfo getInfo() {
        return theTank.getInfo();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return theTank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }
}
