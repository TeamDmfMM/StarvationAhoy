package dmfmm.StarvationAhoy.Meat.Block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class MeatHangerTileEntity extends TileEntity{
	public int MeatType = 3;
	public MeatHangerTileEntity(){
		
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
	   }

	   @Override
	   public void readFromNBT(NBTTagCompound tagCompound)
	   {
	       super.readFromNBT(tagCompound);
	       MeatType = tagCompound.getInteger("Meattype");
	       
	       // ... continue reading non-syncable data 
	   }

	   @Override
	   public Packet getDescriptionPacket()
	   {
	       NBTTagCompound syncData = new NBTTagCompound();
	       syncData.setInteger("Meattype", MeatType);
	       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	   }

	   @Override
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
	       MeatType = pkt.func_148857_g().getInteger("Meattype");
	   }

}
