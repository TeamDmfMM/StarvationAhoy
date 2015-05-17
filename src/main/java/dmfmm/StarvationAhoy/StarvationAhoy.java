package dmfmm.StarvationAhoy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Core.EventHandler.event_configChange;
import dmfmm.StarvationAhoy.Core.FoodModifyCommand;
import dmfmm.StarvationAhoy.Core.HUD.OverlaySaturationBar;
import dmfmm.StarvationAhoy.Core.StarvationAhoyProvider;
import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodEdit.EventHandler.FoodEatenResult;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.ModuleLoad;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.net.PacketMultiBlock;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.StarvationAhoyRegistry;
import dmfmm.StarvationAhoy.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import java.io.File;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUIFactory)
public class StarvationAhoy {
	
	@Instance(value = "StarvationAhoy")
	public static StarvationAhoy instance;
	
	public static ArmorMaterial StatusArmor = EnumHelper.addArmorMaterial("statusarmor", 16, new int[]{2,5,2,1}, 21);
	public static String DIR;

	public static Side side;
	public static SimpleNetworkWrapper MultiBlockChannel;
	@SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SALog.error("We have Launced");
		side = event.getSide();
		DIR = event.getModConfigurationDirectory() + "/StarvationAhoy";
		StarvationAhoyRegistry.init(new StarvationAhoyProvider());
		ConfigHandler.init(new File(DIR, ModInfo.MOD_ID + ".cfg"));
		FMLCommonHandler.instance().bus().register(new event_configChange());
		
		ItemLoad.initItems();
		ModuleMeat.preinit(event.getSide());
		MultiBlockChannel = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
		MultiBlockChannel.registerMessage(PacketMultiBlock.Handler.class, PacketMultiBlock.class, 0, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ClientGetExhaustPacket.Handler.class, ClientGetExhaustPacket.class, 1, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ServerGetExhaustPacket.Handler.class, ServerGetExhaustPacket.class, 2, Side.SERVER);

		MinecraftForge.EVENT_BUS.register(new FoodEatenResult());
		if(event.getSide() == Side.CLIENT){
			MinecraftForge.EVENT_BUS.register(new OverlaySaturationBar(Minecraft.getMinecraft()));
		}
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){
		ItemLoad.registerItems();
		ModuleMeat.init();
		ModuleLoad.loadModules();
		proxy.registerRenderers();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
	  event.registerServerCommand(new FoodModifyCommand());
	}
}
