package dmfmm.StarvationAhoy.Meat.Block.tileentity;

import dmfmm.StarvationAhoy.Meat.Block.HoldingStick;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.CookerMultiBlock;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.MultiBlockStructure;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class HoldingStickTileEntity extends TileEntityMultiBlock implements IInventory {
    ItemStack meat;

    public HoldingStickTileEntity(){
        multiBlockStructure = null;
    }

    public HoldingStickTileEntity(MultiBlockStructure struct) {
        super(struct);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        if (this.multiBlockStructure == null){return null;}
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setInteger("MultiBlockIndex", multiBlockStructure.bPos);
        syncData.setTag("SharedData", multiBlockStructure.sharedData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        if (this.multiBlockStructure == null){this.multiBlockStructure = new CookerMultiBlock();}
        multiBlockStructure.bPos = pkt.func_148857_g().getInteger("MultiBlockIndex");
        multiBlockStructure.sharedData = pkt.func_148857_g().getCompoundTag("SharedData");
    }
    @Override
    public Class<? extends MultiBlockStructure> getMultiBlock() {
        return CookerMultiBlock.class;

    }

    public void onSync(){
//        meat =ItemStack.loadItemStackFromNBT((NBTTagCompound) multiBlockStructure.sharedData.getTag("MeatItem"));
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
    public ItemStack getStackInSlotOnClosing(int slot) {
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
        multiBlockStructure.syncData(multiBlockStructure, multiBlockStructure.bPos, xCoord, yCoord, zCoord, worldObj);
    }

    @Override
    public String getInventoryName() {
        return "MeatCooker";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {
        multiBlockStructure.sharedData.setTag("MeatItem", meat.writeToNBT(new NBTTagCompound()));
        multiBlockStructure.syncData(multiBlockStructure, multiBlockStructure.bPos, xCoord, yCoord, zCoord, worldObj);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}


