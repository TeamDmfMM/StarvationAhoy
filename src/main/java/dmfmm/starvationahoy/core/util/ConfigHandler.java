package dmfmm.starvationahoy.core.util;

import net.minecraftforge.common.config.Configuration;

import java.io.File;


public class ConfigHandler {
	
	public static Configuration config;
	
	//ALL CONFIGS MUST BE PROTECTED!!!
	protected static int x;
	protected static int y;
	protected static int foodPrecentage;
	protected static boolean overrideMeat;
	protected static boolean overrideCropwash;
	
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
		overrideMeat = config.get("starvationahoy", "OverrideMeat", true, "Override and change how to prepare meat, making it harder and more complicated").getBoolean(true);
		foodPrecentage = config.get("starvationahoy", "food-Precentage", 50, "When mods do not give a hunger value, divide its hunger by this. (set to 100 to turn it off)").getInt(50);
		overrideCropwash = config.get("starvationahoy", "OverrideCropWash", true, "Override crops and change how to prepare them, requiring you to wash them").getBoolean(true);
		
		if(config.hasChanged()){
			config.save();
		}
	}


	public static int getOSX(){
		return ConfigHandler.x;
	}
	public static int getOSY(){
		return ConfigHandler.y;
	}
	public static int getFoodPrecent(){
		return ConfigHandler.foodPrecentage;
	}
	public static boolean useMeatOverride() {return ConfigHandler.overrideMeat;}
	public static boolean useCropwash() {return ConfigHandler.overrideCropwash;}
}
