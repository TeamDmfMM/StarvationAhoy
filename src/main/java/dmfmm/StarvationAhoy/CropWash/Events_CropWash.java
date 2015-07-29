package dmfmm.StarvationAhoy.CropWash;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.CropWash.item.DirtyItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by Matthew on 3/14/2015.
 */
public class Events_CropWash {

    @SubscribeEvent
    public void breakCropBlock(BlockEvent.HarvestDropsEvent e){

        if (ModuleCropWash.d.isReplace(e.block)){

            for (Item i : ModuleCropWash.d.toReplace(e.block)){
                for (ItemStack itemStack : e.drops){
                    if (itemStack.getItem() == i){
                        e.drops.set(e.drops.indexOf(itemStack), DirtyItem.createDirtyItem(itemStack));
                    }
                }
            }


        }

    }

}
