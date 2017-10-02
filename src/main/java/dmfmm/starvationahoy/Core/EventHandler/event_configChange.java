package dmfmm.starvationahoy.Core.EventHandler;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dmfmm.starvationahoy.Core.lib.ModInfo;
import dmfmm.starvationahoy.Core.util.ConfigHandler;

public class event_configChange {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e){
		if(e.getModID().equalsIgnoreCase(ModInfo.MOD_ID)){
			ConfigHandler.loadConfig();
		}
	}

}
