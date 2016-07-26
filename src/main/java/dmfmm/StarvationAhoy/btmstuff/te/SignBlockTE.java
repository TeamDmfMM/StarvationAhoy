package dmfmm.StarvationAhoy.btmstuff.te;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

/**
 * Created by TeamDMFMM on 7/23/2016. Code originally written
 * for Starvatione Ahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class SignBlockTE extends TileEntity{

    public static class DirectionData {

        EnumFacing axis_x;
        EnumFacing axis_y;

        boolean complete;

        public DirectionData(EnumFacing ax) {
            axis_x = ax;
            complete = false;
        }

        public DirectionData(EnumFacing ax, EnumFacing ay) {
            axis_x = ax;
            axis_y = ay;
            complete = true;
        }

        public void finish(EnumFacing ay) {
            complete = true;
            axis_y = ay;
        }

        public EnumFacing getAxisX() {
            return this.axis_x;
        }

        public EnumFacing getAxisY() {
            return this.axis_y == null ? EnumFacing.UP : this.axis_y;
        }

        public boolean isComplete() {
            return this.complete;
        }

        public void save(NBTTagCompound compound) {
            compound.setInteger("AxisX", this.getAxisX().getIndex());
            compound.setInteger("AxisY", this.getAxisY().getIndex());
        }

        public static DirectionData load(NBTTagCompound compound) {
            return new DirectionData(EnumFacing.VALUES[compound.getInteger("AxisX")], EnumFacing.VALUES[compound.getInteger("AxisY")]);
        }
    }

    public enum State {
        SINGULAR(0),
        COMPLETE(1);

        public int val;

        State(int val) {
            this.val = val;
        }

        public static State byVal(int v) {
            for (State state : State.values()) {
                if (v == state.val) {
                    return state;
                }
            }
            return null;
        }
    }

    public DirectionData direction;
    public State state;
    public EnumFacing front;

    public int offset_x = 0;
    public int offset_y = 0;

    // Only set if offset_x and y are 0
    public int width;
    public int height;

    public SignBlockTE(State state, DirectionData direction) {
        this.state = state;
        this.direction = direction;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("DData")) {
            this.direction = DirectionData.load(compound.getCompoundTag("DData"));
        }

        this.state = State.byVal(compound.getInteger("State"));
        this.front = EnumFacing.VALUES[compound.getInteger("Front")];

        this.offset_x = compound.getInteger("OffsetX");
        this.offset_y = compound.getInteger("OffsetY");

        this.width = compound.getInteger("Width");
        this.height = compound.getInteger("Height");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound compoundDData = new NBTTagCompound();
        if (this.direction != null) {
            this.direction.save(compoundDData);
            compound.setTag("DData", compoundDData);
        }

        compound.setInteger("State", this.state != null ? this.state.val : 0);
        compound.setInteger("Front", this.front.getIndex());

        compound.setInteger("OffsetX", this.offset_x);
        compound.setInteger("OffsetY", this.offset_y);

        compound.setInteger("Width", this.width);
        compound.setInteger("Height", this.height);

        return compound;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        NBTTagCompound compound = tag;

        if (compound.hasKey("DData")) {
            this.direction = DirectionData.load(compound.getCompoundTag("DData"));
        }

        this.state = State.byVal(compound.getInteger("State"));
        this.front = EnumFacing.VALUES[compound.getInteger("Front")];

        this.offset_x = compound.getInteger("OffsetX");
        this.offset_y = compound.getInteger("OffsetY");

        this.width = compound.getInteger("Width");
        this.height = compound.getInteger("Height");
    }
}

