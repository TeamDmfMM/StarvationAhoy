package dmfmm.starvationahoy.meat.events;

import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerData;
import dmfmm.starvationahoy.meat.item.MItemLoader;
import dmfmm.starvationahoy.api.Event.MeatCutEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class MeatCutRoasterEvent {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.SpitRoast e){
        if(e.getMeattype() == MeatHangerData.MEATTYPE_PIG && e.getItemOut() == Items.COOKED_PORKCHOP){
            EntityItem p = new EntityItem(e.getWorld(), e.getPosition().getX(), e.getPosition().getY()+2, e.getPosition().getZ(), new ItemStack(MItemLoader.pigleg, 4));
            if (!e.getWorld().isRemote){e.getWorld().spawnEntity(p);}
        }else if(e.getMeattype() == MeatHangerData.MEATTYPE_RABBIT && e.getItemOut() == Items.COOKED_RABBIT){
            EntityItem p = new EntityItem(e.getWorld(), e.getPosition().getX(), e.getPosition().getY()+2, e.getPosition().getZ(), new ItemStack(Items.RABBIT_FOOT, 4));
            if (!e.getWorld().isRemote){e.getWorld().spawnEntity(p);}
        }
    }
}
