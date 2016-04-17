package dmfmm.StarvationAhoy.Meat.Events;

import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
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
public class event_meatCutRoaster {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.SpitRoast e){
        if(e.getMeattype() == ModuleMeat.MEATTYPE_PIG && e.getItemOut() == Items.cooked_porkchop){
            EntityItem p = new EntityItem(e.getWorld(), e.getPosition().getX(), e.getPosition().getY()+2, e.getPosition().getZ(), new ItemStack(MItemLoader.pigleg, 4));
            if (!e.getWorld().isRemote){e.getWorld().spawnEntityInWorld(p);}
        }else if(e.getMeattype() == ModuleMeat.MEATTYPE_RABBIT && e.getItemOut() == Items.cooked_rabbit){
            EntityItem p = new EntityItem(e.getWorld(), e.getPosition().getX(), e.getPosition().getY()+2, e.getPosition().getZ(), new ItemStack(Items.rabbit_foot, 4));
            if (!e.getWorld().isRemote){e.getWorld().spawnEntityInWorld(p);}
        }
    }
}
