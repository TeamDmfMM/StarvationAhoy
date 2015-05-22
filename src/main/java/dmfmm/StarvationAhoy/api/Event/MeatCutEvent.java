package dmfmm.StarvationAhoy.api.Event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.Item;
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

    //Event posted when a player right clicks on the Spit Roast with a butcher's knife and
    //the entity is cooked, This is only for supplimentary items being added to a entity
    public static class SpitRoast extends MeatCutEvent{
        public final World world;
        public final int xPos, yPos, zPos;
        public final boolean burnt;
        public final Item itemOut;
        public SpitRoast(World world, int meat, int x, int y, int z, boolean Burnt, Item outputItem){
            super(meat);
            this.world = world;
            this.xPos = x;
            this.yPos = y;
            this.zPos = z;
            this.burnt = Burnt;
            this.itemOut = outputItem;
        }
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
