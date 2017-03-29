package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by Mincrmatt12 on 2/7/2015.
 */
public class CookerTileEntity extends TileEntityMultiBlock implements IInventory {
    ItemStack meat;

    public CookerTileEntity(){
        multiBlockStructure = null;
    }

    public CookerTileEntity(MultiBlockStructure struct) {
        super(struct);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setInteger("MultiBlockIndex", multiBlockStructure.bPos);
        syncData.setTag("SharedData", multiBlockStructure.sharedData);
        return new SPacketUpdateTileEntity(this.getPos(), 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        if (this.multiBlockStructure == null){this.multiBlockStructure = new CookerMultiBlock();}
        multiBlockStructure.bPos = pkt.getNbtCompound().getInteger("MultiBlockIndex");
        multiBlockStructure.sharedData = pkt.getNbtCompound().getCompoundTag("SharedData");
    }
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public Class<? extends MultiBlockStructure> getMultiBlock() {
        return CookerMultiBlock.class;

    }

    public void onSync(){

    }


    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return meat;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        meat = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        multiBlockStructure.sharedData.setTag("MeatItem", meat.writeToNBT(new NBTTagCompound()));
        multiBlockStructure.syncData(multiBlockStructure, multiBlockStructure.bPos, pos, world);
    }

    @Override
    public String getName() {
        return "MeatCooker";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer p) {

    }

    @Override
    public void closeInventory(EntityPlayer p) {
        multiBlockStructure.sharedData.setTag("MeatItem", meat.writeToNBT(new NBTTagCompound()));
        multiBlockStructure.syncData(multiBlockStructure, multiBlockStructure.bPos, pos, world);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
