package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by MM12 on 2/7/2015.
 */
public abstract class TileEntityMultiBlock extends TileEntity{

    public MultiBlockStructure multiBlockStructure;

    public void updateEntity(){if (multiBlockStructure != null) multiBlockStructure.onUpdate(worldObj);

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

    }

    public abstract Class<? extends MultiBlockStructure> getMultiBlock();

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        try {
            multiBlockStructure = getMultiBlock().newInstance();
        } catch (Exception e){
            SALog.fatal("Failed to load multiblock structure for a tileentity. If you are a user of this mod, please notify mincrmatt12 of this error.");
        }
        try {
            multiBlockStructure.sharedData = nbtTagCompound.getCompoundTag("MultiBlockShared");
            multiBlockStructure.bPos = nbtTagCompound.getInteger("MultiBlockIndex");
            if (multiBlockStructure.bPos == 0) {
                multiBlockStructure.syncData(multiBlockStructure, 0, xCoord, yCoord, xCoord, worldObj);
            }
        }
        catch (NullPointerException e){
            multiBlockStructure = null;
        }

    }
}
