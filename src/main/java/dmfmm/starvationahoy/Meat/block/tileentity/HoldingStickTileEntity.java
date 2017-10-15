package dmfmm.starvationahoy.Meat.block.tileentity;

import dmfmm.starvationahoy.Meat.block.multiblock.CookerMultiBlock;
import dmfmm.starvationahoy.Meat.block.multiblock.MultiBlockStructure;
import dmfmm.starvationahoy.Meat.block.multiblock.TileEntityMultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;


public class HoldingStickTileEntity extends TileEntityMultiBlock implements IInventory {
    ItemStack meat;

    public HoldingStickTileEntity(){
        multiBlockStructure = null;
    }

    public HoldingStickTileEntity(MultiBlockStructure struct) {
        super(struct);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        if (this.multiBlockStructure == null){return null;}
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setInteger("MultiBlockIndex", multiBlockStructure.bPos);
        syncData.setTag("SharedData", multiBlockStructure.sharedData);
        return new SPacketUpdateTileEntity(pos, 1, syncData);
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
    @Override
    public AxisAlignedBB getRenderBoundingBox(){
        return this.INFINITE_EXTENT_AABB;
    }

    public void onSync(){
//        meat =ItemStack.loadItemStackFromNBT((NBTTagCompound) multiBlockStructure.sharedData.getTag("MeatItem"));
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return meat;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        List list = new ArrayList<ItemStack>();
        list.add(this.meat);
        return ItemStackHelper.getAndSplit(list, slot, amt);
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        meat = stack;
        if (stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        multiBlockStructure.sharedData.setTag("MeatItem", meat.writeToNBT(new NBTTagCompound()));
        multiBlockStructure.syncData(multiBlockStructure, multiBlockStructure.bPos, this.pos, world);
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
    public boolean isUsableByPlayer(EntityPlayer player) {return false;}

    @Override
    public void openInventory(EntityPlayer e) {

    }

    @Override
    public void closeInventory(EntityPlayer e) {
        multiBlockStructure.sharedData.setTag("MeatItem", meat.writeToNBT(new NBTTagCompound()));
        multiBlockStructure.syncData(multiBlockStructure, multiBlockStructure.bPos, this.pos, world);
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


