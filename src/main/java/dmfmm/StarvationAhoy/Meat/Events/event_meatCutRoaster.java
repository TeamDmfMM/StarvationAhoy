package dmfmm.StarvationAhoy.Meat.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class event_meatCutRoaster {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.SpitRoast e){
        if(e.meattype == ModuleMeat.MEATTYPE_PIG && e.itemOut == Items.cooked_porkchop){
            EntityItem p = new EntityItem(e.world, e.xPos, e.yPos+2, e.zPos, new ItemStack(MItemLoader.pigleg, 4));
            if (!e.world.isRemote){e.world.spawnEntityInWorld(p);}
        }
    }
}
