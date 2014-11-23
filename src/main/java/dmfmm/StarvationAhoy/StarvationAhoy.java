package dmfmm.StarvationAhoy;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmfmm.StarvationAhoy.Core.EventHandler.event_configChange;
import dmfmm.StarvationAhoy.Core.HUD.OverlaySaturationBar;
import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodEdit.EventHandler.FoodEatenResult;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.ModuleLoad;
import dmfmm.StarvationAhoy.proxy.CommonProxy;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUIFactory)
public class StarvationAhoy {
	
	@Instance(value = "StarvationAhoy")
	public static StarvationAhoy instance;
	
	public static ArmorMaterial StatusArmor = EnumHelper.addArmorMaterial("statusarmor", 16, new int[]{2,5,2,1}, 21);;
	
	@SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SALog.error("We have Launced");
		ConfigHandler.init(new File(event.getModConfigurationDirectory() + "/StarvationAhoy", ModInfo.MOD_ID + ".cfg"));
		FMLCommonHandler.instance().bus().register(new event_configChange());
		
		ItemLoad.initItems();
		
		MinecraftForge.EVENT_BUS.register(new FoodEatenResult());
		MinecraftForge.EVENT_BUS.register(new OverlaySaturationBar(Minecraft.getMinecraft()));
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){
		ItemLoad.registerItems();
		ModuleLoad.loadModules();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
