package dmfmm.StarvationAhoy.Core.EventHandler;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;

public class event_configChange {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e){
		if(e.getModID().equalsIgnoreCase(ModInfo.MOD_ID)){
			ConfigHandler.loadConfig();
		}
	}

}
