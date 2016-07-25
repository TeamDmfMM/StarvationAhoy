package dmfmm.StarvationAhoy.CropWash.Block.tilentity;

import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.TileFluidHandler;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class TileEntityCropWasher extends TileFluidHandler{


    public TileEntityCropWasher(){
    }

    public ItemStack wash(ItemStack item){

        if (this.tank.getFluidAmount() >= 20 * item.stackSize){
            if (item.getItem() == ModuleCropWash.cropItemLoader.getItem("dirty_item")){
                ItemStack original = ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("Original"));
                original.stackSize = item.stackSize;
                this.tank.drain(20 * item.stackSize, true);
                return original;
            }
        }
        return item;
    }


    public float getFluidAmount() {
        return this.tank.getFluidAmount();
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setTag("fluid", tank.writeToNBT(new NBTTagCompound()));
        return new SPacketUpdateTileEntity(this.pos, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        tank.readFromNBT(pkt.getNbtCompound().getCompoundTag("fluid"));
    }
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill)
    {
        int fill = tank.fill(resource, doFill);
        worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos), 2);
        return fill;
    }
}
