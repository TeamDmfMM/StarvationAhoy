package dmfmm.starvationahoy.Meat.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MeatHangerTileEntity extends TileEntity {

	private int meatType;  //0-n|1-Cow|2-Pig|3-Chicken
	private int meatState; //0-normal|1-Skined|2-Rotten
	
	public MeatHangerTileEntity(){
		 this.meatType = MeatHangerData.EMPTY.getMeatID();
		 this.meatState = MeatHangerData.MeatStates.EMPTY.getState();
	}

	public MeatHangerData.MeatStates getMeatState(){
		return MeatHangerData.MeatStates.values()[meatState];
	}
	public void setMeatState(MeatHangerData.MeatStates type){
		meatState = type.getState();
	}
	public int getMeatType(){ return meatType; }

	public void setMeatType(int type){
		meatType = type;
	}

	public void updateHanger(int type, MeatHangerData.MeatStates meat){
		this.setMeatState(meat);
		this.setMeatType(type);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Meattype", meatType);
		tagCompound.setInteger("Meatstate", meatState);
		return tagCompound;
	}

    @Override
    public AxisAlignedBB getRenderBoundingBox(){
        return this.INFINITE_EXTENT_AABB;
    }


	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		meatType = tagCompound.getInteger("Meattype");
		meatState = tagCompound.getInteger("Meatstate");
	       
		// ... continue reading non-syncable data
	}

	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		syncData.setInteger("Meattype", meatType);
		syncData.setInteger("State", meatState);
		return new SPacketUpdateTileEntity(this.pos, 1, syncData);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		meatState = pkt.getNbtCompound().getInteger("State");
		meatType = pkt.getNbtCompound().getInteger("Meattype");
	}



}
