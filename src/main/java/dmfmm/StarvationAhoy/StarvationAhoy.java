package dmfmm.StarvationAhoy;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodEdit.EventHandler.FoodEatenResult;
import dmfmm.StarvationAhoy.proxy.CommonProxy;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class StarvationAhoy {
	
	@Instance(value = "StarvationAhoy")
	public static StarvationAhoy instance;
	
	@SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SALog.error("We have Launced");
		MinecraftForge.EVENT_BUS.register(new FoodEatenResult());
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
		
	}
}
