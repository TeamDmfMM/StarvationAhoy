package dmfmm.StarvationAhoy.Meat.Events;

import dmfmm.StarvationAhoy.FoodEdit.PacketOverride;
import dmfmm.StarvationAhoy.StarvationAhoy;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class Event_DoConfig {

    @SubscribeEvent
    public void serverJoin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        StarvationAhoy.MultiBlockChannel.sendToServer(new PacketOverride());
    }

    @SubscribeEvent void serverdejoin(FMLNetworkEvent.ClientDisconnectionFromServerEvent event){
        KnownFoods.joinS();
    }
}
