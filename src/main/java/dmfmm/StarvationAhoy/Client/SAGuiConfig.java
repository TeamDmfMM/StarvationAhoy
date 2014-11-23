package dmfmm.StarvationAhoy.Client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;



public class SAGuiConfig extends GuiConfig{
	public SAGuiConfig(GuiScreen guiscreen){
		super(guiscreen, new ConfigElement(ConfigHandler.config.getCategory("starvationahoy")).getChildElements(), ModInfo.MOD_ID, false, true, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
}
