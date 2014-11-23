package dmfmm.StarvationAhoy.Core.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;


public class ConfigHandler {
	
	public static Configuration config;
	
	//ALL CONFIGS MUST BE PROTECTED!!!
	protected static int x;
	protected static int y;
	
	public static void init(File configFile){
		if(config == null){
			config = new Configuration(configFile);
			loadConfig();
		}
		
	}
	
	public static void loadConfig(){
		//Put config here
		x = config.get("starvationahoy", "x-Position", 3, "X position of the Stats tooltip (when wearing armor)").getInt(3);
		y = config.get("starvationahoy", "y-Position", 3, "Y position of the Stats tooltip (when wearing armor)").getInt(3);
		
		if(config.hasChanged()){
			config.save();
		}
	}
}
