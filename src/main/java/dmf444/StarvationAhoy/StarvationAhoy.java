package dmf444.StarvationAhoy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmf444.StarvationAhoy.Common.CommonProxy;
@Mod(modid="starvationahoy", name="Starvation Ahoy!", version="0.1a")
public class StarvationAhoy {
	
	@Instance(value="StarvationAhoy")
	public static StarvationAhoy instance;
	
	@SidedProxy(clientSide="dmf444.StarvationAhoy.Common.CommonProxy", serverSide="dmf444.StarvationAhoy.Client.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
