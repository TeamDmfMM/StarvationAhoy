package dmfmm.starvationahoy.meat.events;

import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerData;
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
public class MeatSkinnedEvent {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.MeatSkinned e){
         if(e.getMeattype() == MeatHangerData.MEATTYPE_RABBIT) {
             e.setCanceled(true);
             if (!e.getWorld().isRemote) {
                 e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPosition().getX(), e.getPosition().getY()+2, e.getPosition().getZ(), new ItemStack(Items.RABBIT_HIDE, 1)));
             }
         }
    }
}
