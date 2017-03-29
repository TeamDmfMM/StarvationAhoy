package dmfmm.StarvationAhoy.Meat.Block.multiblock;


import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.net.PacketMultiBlock;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public abstract class TileEntityMultiBlock extends TileEntity implements ITickable {

    private int timeAfter = 0;

    public boolean r = false;

    public MultiBlockStructure multiBlockStructure;

    public TileEntityMultiBlock(){
        multiBlockStructure = null;
    }

    public void update(){


        if (multiBlockStructure != null && r == true) multiBlockStructure.updateStructure(world);
        if (timeAfter == 120 && multiBlockStructure != null ) {
            if (!world.isRemote) StarvationAhoy.MultiBlockChannel.sendToAllAround(new PacketMultiBlock(multiBlockStructure.bPos, multiBlockStructure.orient, multiBlockStructure.sharedData, multiBlockStructure.x, multiBlockStructure.y, multiBlockStructure.z), new NetworkRegistry.TargetPoint(world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 40));
            r = true;}
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
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        if (multiBlockStructure == null) return nbtTagCompound;

        nbtTagCompound.setTag("MultiBlockShared", multiBlockStructure.sharedData);
        nbtTagCompound.setInteger("MultiBlockIndex", multiBlockStructure.bPos);
        nbtTagCompound.setInteger("MultiBlockOrient", multiBlockStructure.orient);

        return nbtTagCompound;
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
            multiBlockStructure.x = this.pos.getX();
            multiBlockStructure.y = this.pos.getY();
            multiBlockStructure.z = this.pos.getZ();
            multiBlockStructure.orient = nbtTagCompound.getInteger("MultiBlockOrient");


               // SALog.error("I think i should send something");
               // StarvationAhoy.MultiBlockChannel.sendToAll(new PacketMultiBlock(multiBlockStructure.bPos, multiBlockStructure.orient, multiBlockStructure.sharedData, multiBlockStructure.x, multiBlockStructure.y, multiBlockStructure.z));

        }
        catch (NullPointerException e){
            multiBlockStructure = null;
        }

    }
}
