package dmfmm.StarvationAhoy.Meat.Events;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.world.World;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class MeatCutEvent extends Event{

    public final int meattype;
    public MeatCutEvent(int meat)
    {
        this.meattype = meat;
    }

    public static class SpitRoast extends MeatCutEvent{
        public SpitRoast(int meat){super(meat);}
    }

    public static class MeatHanger extends MeatCutEvent{
        public final World world;
        public final int xPos, yPos, zPos;
        public MeatHanger(World world, int meat, int x, int y, int z){
            super(meat);
            this.world = world;
            this.xPos = x;
            this.yPos = y;
            this.zPos = z;
        }
    }

    public static class MeatSkinned extends MeatCutEvent{
        public final World world;
        public final int xPos, yPos, zPos;
        public MeatSkinned(World world, int meat, int x, int y, int z){
            super(meat);
            this.world = world;
            this.xPos = x;
            this.yPos = y;
            this.zPos = z;
        }
    }
}
