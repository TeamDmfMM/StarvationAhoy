package dmfmm.StarvationAhoy.Meat.Block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class MeatHangerTileEntity extends TileEntity {
	private int MeatType = 0;//0-n|1-Cow|2-Pig|3-Chicken
	private int MeatState = 0;//0-normal|1-Skined|2-Rotten
	
	public MeatHangerTileEntity(){

	}

	public MeatStates getMeatState(){
		return MeatStates.values()[MeatState];
	}
	public void setMeatState(MeatStates type){
		MeatState = type.state;
	}
	public int getMeatType(){
		return MeatType;
	}
	public void setMeatType(int type){
		MeatType = type;
	}
	   @Override
	   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
	   {
	       super.writeToNBT(tagCompound);
	       tagCompound.setInteger("Meattype", MeatType);
	       tagCompound.setInteger("Meatstate", MeatState);
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
	       MeatType = tagCompound.getInteger("Meattype");
	       MeatState = tagCompound.getInteger("Meatstate");
	       
	       // ... continue reading non-syncable data 
	   }

	   @Override
	   public SPacketUpdateTileEntity getUpdatePacket()
	   {
	       NBTTagCompound syncData = new NBTTagCompound();
	       syncData.setInteger("Meattype", MeatType);
	       syncData.setInteger("State", MeatState);
	       return new SPacketUpdateTileEntity(this.pos, 1, syncData);
	   }

	   @Override
	   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	   {
	       MeatState = pkt.getNbtCompound().getInteger("State");
		   MeatType = pkt.getNbtCompound().getInteger("Meattype");
	   }


	public enum MeatStates{
		NORMAL(0),
		SKINNED(1),
		ROTTEN(2);

		private int state;

		private MeatStates(int i){
			this.state = i;
		}

	}
}
