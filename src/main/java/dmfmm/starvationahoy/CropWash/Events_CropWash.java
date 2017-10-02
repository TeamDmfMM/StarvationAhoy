package dmfmm.starvationahoy.CropWash;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dmfmm.starvationahoy.CropWash.item.DirtyItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by Matthew on 3/14/2015.
 */
public class Events_CropWash {

    @SubscribeEvent
    public void breakCropBlock(BlockEvent.HarvestDropsEvent e){

        if (ModuleCropWash.d.isReplace(e.getState().getBlock())){

            for (Item i : ModuleCropWash.d.toReplace(e.getState().getBlock())){
                for (ItemStack itemStack : e.getDrops()){
                    if (itemStack.getItem() == i){
                        e.getDrops().set(e.getDrops().indexOf(itemStack), DirtyItem.createDirtyItem(itemStack));
                    }
                }
            }


        }

    }

}
