package dmfmm.starvationahoy.core.event;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dmfmm.starvationahoy.core.lib.ModInfo;
import dmfmm.starvationahoy.core.util.ConfigHandler;

public class ConfigChangeEvent {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e){
		if(e.getModID().equalsIgnoreCase(ModInfo.MOD_ID)){
			ConfigHandler.loadConfig();
		}
	}

}
