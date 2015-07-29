package dmfmm.StarvationAhoy.Meat.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class event_meatCutHanger {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.MeatHanger e){
        Item item = null;
        if(e.meattype == ModuleMeat.MEATTYPE_COW){
            item = MItemLoader.skinlessCow;
        }else if(e.meattype == ModuleMeat.MEATTYPE_PIG){
            item = MItemLoader.skinlessPig;
        }else if(e.meattype == ModuleMeat.MEATTYPE_CHICK){
            item = MItemLoader.skinlessChicken;
        }
        if(!e.world.isRemote){
            e.world.spawnEntityInWorld(new EntityItem(e.world, e.xPos, e.yPos, e.zPos, new ItemStack(item)));
        }
    }
}
