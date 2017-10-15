package dmfmm.starvationahoy.core.util;

public class CRef {
	
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
