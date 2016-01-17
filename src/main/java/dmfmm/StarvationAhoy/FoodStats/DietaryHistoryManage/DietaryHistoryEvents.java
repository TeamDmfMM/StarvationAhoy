package dmfmm.StarvationAhoy.FoodStats.DietaryHistoryManage;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import dmfmm.StarvationAhoy.FoodStats.PlayerInstanceHolder;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DietaryHistoryEvents {


    // Do not be alarmed, this runs from other events. It helps with **memory usage** (imagine that's italicized)


    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e){
        PlayerInstanceHolder.instance.playerJoin((EntityPlayerMP)e.player);
    }

}
