package dmfmm.StarvationAhoy.Meat.Block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class MeatHangerTileEntity extends TileEntity{
	private int MeatType = 0;//0-n|1-Cow|2-Pig|3-Chicken
	private int MeatState = 0;//0-normal|1-Skined|2-Rotten
	
	public MeatHangerTileEntity(){
		
	}
	public int getMeatState(){
		return MeatState;
	}
	public void setMeatState(int type){
		MeatState = type;
	}
	public int getMeatType(){
		return MeatType;
	}
	public void setMeatType(int type){
		MeatType = type;
	}
	   @Override
	   public void writeToNBT(NBTTagCompound tagCompound)
	   {
	       super.writeToNBT(tagCompound);
	       tagCompound.setInteger("Meattype", MeatType);
	       tagCompound.setInteger("Meatstate", MeatState);
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
	   public Packet getDescriptionPacket()
	   {
	       NBTTagCompound syncData = new NBTTagCompound();
	       syncData.setInteger("Meattype", MeatType);
	       syncData.setInteger("State", MeatState);
	       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	   }

	   @Override
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
	       MeatState = pkt.func_148857_g().getInteger("State");
		   MeatType = pkt.func_148857_g().getInteger("Meattype");
	   }

}
