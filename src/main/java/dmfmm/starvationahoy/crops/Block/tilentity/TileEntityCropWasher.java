package dmfmm.starvationahoy.crops.Block.tilentity;

import dmfmm.starvationahoy.crops.ModuleCropWash;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.TileFluidHandler;


/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class TileEntityCropWasher extends TileFluidHandler {


    public TileEntityCropWasher(){
    }

    public ItemStack wash(ItemStack item){

        if (this.tank.getFluidAmount() >= 20 * item.getCount()){
            if (item.getItem() == ModuleCropWash.cropItemLoader.getItem("dirty_item")){
                ItemStack original = new ItemStack(item.getTagCompound().getCompoundTag("Original"));
                original.setCount(item.getCount());
                this.tank.drain(20 * item.getCount(), true);
                return original;
            }
        }
        return item;
    }
    public void fill(){
        this.tank.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
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
}
