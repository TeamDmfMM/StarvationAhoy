package dmfmm.StarvationAhoy;

import dmfmm.StarvationAhoy.Client.Gui.book_gui.FurnaceHelper;
import dmfmm.StarvationAhoy.Core.*;
import dmfmm.StarvationAhoy.Core.EventHandler.event_configChange;
import dmfmm.StarvationAhoy.Core.Init.CoreTextureRegistry;
import dmfmm.StarvationAhoy.Core.Init.CropwashTextureRegistry;
import dmfmm.StarvationAhoy.Core.Init.MeatTextureRegistry;
import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import dmfmm.StarvationAhoy.FoodEdit.EventHandler.FoodEatenResult;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.ModuleLoad;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketFoodUpdate;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketRequestNewFoods;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketResponseNewFoods;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.net.PacketMultiBlock;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.StarvationAhoyRegistry;
import dmfmm.StarvationAhoy.btmstuff.*;
import dmfmm.StarvationAhoy.proxy.CommonProxy;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;

//import net.minecraft.client.Minecraft;
//import net.minecraftforge.client.model.obj.OBJLoader;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUIFactory)
public class StarvationAhoy {
	
	@Instance(value = "StarvationAhoy")
	public static StarvationAhoy instance;
	
	public static ArmorMaterial StatusArmor = EnumHelper.addArmorMaterial("statusarmor", "", 16, new int[]{2,5,2,1}, 21, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2);
	//public static TileEntityBanner.EnumBannerPattern pattern;
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
		DIR = event.getModConfigurationDirectory() + File.separator+ "StarvationAhoy";
		StarvationAhoyRegistry.init(new StarvationAhoyProvider());
		ConfigHandler.init(new File(DIR, ModInfo.MOD_ID + ".cfg"));
		MinecraftForge.EVENT_BUS.register(new event_configChange());


		//Module Initiation
		ModuleCropWash.preinit(side);
		ItemLoad.initItems();
		ModuleMeat.preinit(event.getSide());

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		//pattern = addBannerIcon("starvationAhoy", "sta", new ItemStack(ItemLoad.HungerPotion));

		//Packet Initiation
		MultiBlockChannel = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
		MultiBlockChannel.registerMessage(PacketMultiBlock.Handler.class, PacketMultiBlock.class, 0, Side.CLIENT);
		MultiBlockChannel.registerMessage(PacketResponseNewFoods.Handler.class, PacketResponseNewFoods.class, 1, Side.CLIENT);
		MultiBlockChannel.registerMessage(PacketRequestNewFoods.Handler.class, PacketRequestNewFoods.class, 2, Side.SERVER);
		MultiBlockChannel.registerMessage(PacketFoodUpdate.Handler.class, PacketFoodUpdate.class, 4, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ClientGetExhaustPacket.Handler.class, ClientGetExhaustPacket.class, 1, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ServerGetExhaustPacket.Handler.class, ServerGetExhaustPacket.class, 2, Side.SERVER);

		//Secondary Events
		MinecraftForge.EVENT_BUS.register(new FoodEatenResult());

		ModuleBTMStuff.instance.preinit();


		proxy.preInit();
		proxy.initSounds();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){

		//Module Loading
		ItemLoad.registerItems();
		CoreRecipies.registerRecipies();
		ModuleCropWash.init(event.getSide());
		ModuleMeat.init();
		ModuleBTMStuff.instance.init();
		//ModuleFoodStats.init();

		//Client Rendering and Food Overrides
		ModuleLoad.loadModules();
		proxy.registerRenderers();
		//proxy.registerKeyBindings();

		if(event.getSide() == Side.CLIENT){
			CoreTextureRegistry.initTextures();
			if(CRef.useCropwash()) {
				CropwashTextureRegistry.initTextures();
			}
			if(CRef.useMeatOverride()){
				MeatTextureRegistry.initTextures();
			}


		}


    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		ModuleCropWash.postInit();
		if (event.getSide() == Side.CLIENT) {
			KnownFoods.leaveServer();
			FurnaceHelper.iterate();
			FurnaceHelper.findFuels();
		}
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

}
