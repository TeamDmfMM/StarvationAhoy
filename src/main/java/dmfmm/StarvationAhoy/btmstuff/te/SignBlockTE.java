package dmfmm.StarvationAhoy.btmstuff.te;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

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
            return this.axis_y == null ? EnumFacing.DOWN : this.axis_y;
        }

        public boolean isComplete() {
            return this.complete;
        }
    }

    public enum State {
        SINGULAR,
        COMPLETE;
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

}
