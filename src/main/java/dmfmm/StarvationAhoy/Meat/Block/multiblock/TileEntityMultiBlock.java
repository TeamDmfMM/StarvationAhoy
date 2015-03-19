package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.net.PacketMultiBlock;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Matthew on 2/7/2015.
 */
public abstract class TileEntityMultiBlock extends TileEntity{

    private int timeAfter = 0;

    public boolean r = false;

    public MultiBlockStructure multiBlockStructure;

    public TileEntityMultiBlock(){
        multiBlockStructure = null;
    }

    public void updateEntity(){

        if (multiBlockStructure != null){SALog.error(multiBlockStructure.bPos);}
        if (multiBlockStructure != null) multiBlockStructure.onUpdate(worldObj);
        if (timeAfter == 120 && multiBlockStructure != null ) { StarvationAhoy.MultiBlockChannel.sendToAllAround(new PacketMultiBlock(multiBlockStructure.bPos, multiBlockStructure.orient, multiBlockStructure.sharedData, multiBlockStructure.x, multiBlockStructure.y, multiBlockStructure.z), new NetworkRegistry.TargetPoint(0, xCoord, yCoord, zCoord, 40)); }
        timeAfter++;
        if (timeAfter == 240){
            timeAfter = 0;
        }

    }

    public TileEntityMultiBlock(MultiBlockStructure struct){
        multiBlockStructure = struct;
    }

    public void onSync(){}

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        if (multiBlockStructure == null) return;

        nbtTagCompound.setTag("MultiBlockShared", multiBlockStructure.sharedData);
        nbtTagCompound.setInteger("MultiBlockIndex", multiBlockStructure.bPos);
        nbtTagCompound.setInteger("MultiBlockOrient", multiBlockStructure.orient);

    }

    public abstract Class<? extends MultiBlockStructure> getMultiBlock();

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        try {
            if (nbtTagCompound.hasKey("MultiBlockIndex")) {
                multiBlockStructure = getMultiBlock().newInstance();
            }
            else {
                return;
            }
        } catch (Exception e){
            SALog.fatal("Failed to load multiblock structure for a tileentity. If you are a user of this mod, please notify mincrmatt12 of this error.");
        }
        try {
            multiBlockStructure.sharedData = nbtTagCompound.getCompoundTag("MultiBlockShared");
            multiBlockStructure.bPos = nbtTagCompound.getInteger("MultiBlockIndex");
            multiBlockStructure.x = this.xCoord;
            multiBlockStructure.y = this.yCoord;
            multiBlockStructure.z = this.zCoord;
            multiBlockStructure.orient = nbtTagCompound.getInteger("MultiBlockOrient");


                SALog.error("I think i should send something");
               // StarvationAhoy.MultiBlockChannel.sendToAll(new PacketMultiBlock(multiBlockStructure.bPos, multiBlockStructure.orient, multiBlockStructure.sharedData, multiBlockStructure.x, multiBlockStructure.y, multiBlockStructure.z));

        }
        catch (NullPointerException e){
            multiBlockStructure = null;
        }

    }
}
