package dmfmm.StarvationAhoy.Meat.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
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
public class event_meatSkinned {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.MeatSkinned e){
        int randomNum = e.world.rand.nextInt((4 - 0) + 1) + 0;
        if(e.meattype == ModuleMeat.MEATTYPE_COW){
            if(!e.world.isRemote){e.world.spawnEntityInWorld(new EntityItem(e.world, e.xPos, e.yPos, e.zPos, new ItemStack(Items.leather, randomNum)));}
        }else if(e.meattype == ModuleMeat.MEATTYPE_CHICK){
            if(!e.world.isRemote){e.world.spawnEntityInWorld(new EntityItem(e.world, e.xPos, e.yPos, e.zPos, new ItemStack(Items.feather, randomNum)));}
        }
    }
}
