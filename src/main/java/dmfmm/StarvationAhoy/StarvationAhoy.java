package dmfmm.StarvationAhoy;

import dmfmm.StarvationAhoy.Core.Init.CoreTextureRegistry;
import dmfmm.StarvationAhoy.Core.Init.CropwashTextureRegistry;
import dmfmm.StarvationAhoy.Core.Init.MeatTextureRegistry;
import dmfmm.StarvationAhoy.api.CropWash.CropWash;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import dmfmm.StarvationAhoy.Core.CoreRecipies;
import dmfmm.StarvationAhoy.Core.EventHandler.event_configChange;
import dmfmm.StarvationAhoy.Core.FoodModifyCommand;
import dmfmm.StarvationAhoy.Core.HUD.OverlaySaturationBar;
import dmfmm.StarvationAhoy.Core.IMCRerouter;
import dmfmm.StarvationAhoy.Core.StarvationAhoyProvider;
import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
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
	
	public static ArmorMaterial StatusArmor = EnumHelper.addArmorMaterial("statusarmor", "", 16, new int[]{2,5,2,1}, 21);
	public static TileEntityBanner.EnumBannerPattern pattern;
	public static String DIR;

	public static Side side;
	public static SimpleNetworkWrapper MultiBlockChannel;
	@SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
	public static CommonProxy proxy;


	public static IMCRerouter router = new IMCRerouter();

	@EventHandler
	public void processIMCMessages(FMLInterModComms.IMCEvent event) {
		for (FMLInterModComms.IMCMessage message : event.getMessages()){
			router.onImcMessage(message);
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SALog.error("We have Launched");

		//Config and API
		side = event.getSide();
		DIR = event.getModConfigurationDirectory() + "/StarvationAhoy";
		StarvationAhoyRegistry.init(new StarvationAhoyProvider());
		ConfigHandler.init(new File(DIR, ModInfo.MOD_ID + ".cfg"));
		FMLCommonHandler.instance().bus().register(new event_configChange());

		//Module Initiation
		ModuleCropWash.preinit();
		ItemLoad.initItems();
		ModuleMeat.preinit(event.getSide());

		pattern = addBannerIcon("starvationAhoy", "sta", new ItemStack(ItemLoad.HungerPotion));

		//Packet Initiation
		MultiBlockChannel = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
		MultiBlockChannel.registerMessage(PacketMultiBlock.Handler.class, PacketMultiBlock.class, 0, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ClientGetExhaustPacket.Handler.class, ClientGetExhaustPacket.class, 1, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ServerGetExhaustPacket.Handler.class, ServerGetExhaustPacket.class, 2, Side.SERVER);

		//Secondary Events
		MinecraftForge.EVENT_BUS.register(new FoodEatenResult());
		if(event.getSide() == Side.CLIENT){
			MinecraftForge.EVENT_BUS.register(new OverlaySaturationBar(Minecraft.getMinecraft()));
		}
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){

		//Module Loading
		ItemLoad.registerItems();
		CoreRecipies.registerRecipies();
		ModuleCropWash.init(event.getSide());
		ModuleMeat.init();
		//ModuleFoodStats.init();

		//Client Rendering and Food Overrides
		ModuleLoad.loadModules();
		proxy.registerRenderers();
		//proxy.registerKeyBindings();

		if(event.getSide() == Side.CLIENT){

			CoreTextureRegistry.initTextures();
			CropwashTextureRegistry.initTextures();
			MeatTextureRegistry.initTextures();


		}


    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
	  event.registerServerCommand(new FoodModifyCommand());
		//ModuleFoodStats.serverStart();
	}

	@EventHandler
	public void serverStop(FMLServerStoppingEvent event){
		//ModuleFoodStats.serverStop();
	}

	public static TileEntityBanner.EnumBannerPattern addBannerIcon(String name, String id, ItemStack craftingItem)
	{
		return EnumHelper.addEnum(TileEntityBanner.EnumBannerPattern.class, name, id, craftingItem);
	}
}
