package dmfmm.StarvationAhoy.Core.EventHandler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;

public class event_configChange {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e){
		if(e.modID.equalsIgnoreCase(ModInfo.MOD_ID)){
			ConfigHandler.loadConfig();
		}
	}

}
