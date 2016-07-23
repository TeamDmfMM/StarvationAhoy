package dmfmm.StarvationAhoy.btmstuff.te;

import net.minecraft.tileentity.TileEntity;

/**
 * Created by TeamDMFMM on 7/23/2016. Code originally written
 * for Starvatione Ahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class SignBlockTE extends TileEntity{

    public static enum DirectionData {

        ;

        int axis_tex_x;
        int axis_tex_y;

        DirectionData (int atx, int aty) {
            this.axis_tex_x = atx;
            this.axis_tex_y = aty;
        }

    }

}
