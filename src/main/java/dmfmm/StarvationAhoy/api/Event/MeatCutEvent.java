package dmfmm.StarvationAhoy.api.Event;

import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
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
        public final BlockPos position;
        public final boolean burnt;
        public final Item itemOut;
        public SpitRoast(World world, int meat, BlockPos pos, boolean Burnt, Item outputItem){
            super(meat);
            this.world = world;
            this.position = pos;
            this.burnt = Burnt;
            this.itemOut = outputItem;

        }
    }

    /**
     * This event is called when a player interacts with the Meat Hanger
     * Precisely, When a player attempts to cut a skinned entity off.
     *
     *  This event is {@link Cancelable}.
     *  If this event is cancelled, a skinned version of the entity will not be produced, and the entity will
     *  remain on the meat hanger
     *
     *  This event is fired on the {@link MinecraftForge#EVENT_BUS}.
     */
    @Cancelable
    public static class MeatHanger extends MeatCutEvent{
        public final World world;
        public final BlockPos position;
        public MeatHanger(World world, int meat, BlockPos pos){
            super(meat);
            this.world = world;
            this.position = pos;
        }
    }

    /**
     * This event is called when a player interacts with the Meat Hanger
     * Precisely, When a player attempts to skin an entity.
     *
     *  This event is {@link Cancelable}.
     *  Cancelling this will stop the drops the entity has.
     *
     *  This event is fired on the {@link MinecraftForge#EVENT_BUS}.
     */
    @Cancelable
    public static class MeatSkinned extends MeatCutEvent{
        public final World world;
        public final BlockPos position;
        public MeatSkinned(World world, int meat, BlockPos pos){
            super(meat);
            SALog.error(this.isCancelable());
            this.world = world;
            this.position = pos;
        }
    }
}
