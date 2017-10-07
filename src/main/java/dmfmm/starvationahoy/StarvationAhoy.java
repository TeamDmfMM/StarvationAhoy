package dmfmm.starvationahoy;

import dmfmm.starvationahoy.Client.Gui.book_gui.FurnaceHelper;
import dmfmm.starvationahoy.Core.*;
import dmfmm.starvationahoy.Core.EventHandler.event_configChange;
import dmfmm.starvationahoy.Core.Init.CoreTextureRegistry;
import dmfmm.starvationahoy.Core.Init.CropwashTextureRegistry;
import dmfmm.starvationahoy.Core.Init.MeatTextureRegistry;
import dmfmm.starvationahoy.Core.items.ItemLoad;
import dmfmm.starvationahoy.Core.lib.ModInfo;
import dmfmm.starvationahoy.Core.util.CRef;
import dmfmm.starvationahoy.Core.util.ConfigHandler;
import dmfmm.starvationahoy.Core.util.SALog;
import dmfmm.starvationahoy.CropWash.ModuleCropWash;
import dmfmm.starvationahoy.Meat.Block.multiblock.net.PacketMultiBlock;
import dmfmm.starvationahoy.Meat.ModuleMeat;
import dmfmm.starvationahoy.api.StarvationAhoyRegistry;
import dmfmm.starvationahoy.proxy.CommonProxy;
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

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUIFactory)
public class StarvationAhoy {
	
	@Instance(value = ModInfo.MOD_ID)
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
		DIR = event.getModConfigurationDirectory() + File.separator+ "starvationahoy";
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

        //MultiBlockChannel.registerMessage(ClientGetExhaustPacket.Handler.class, ClientGetExhaustPacket.class, 1, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ServerGetExhaustPacket.Handler.class, ServerGetExhaustPacket.class, 2, Side.SERVER);

		//Secondary Events
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
		//ModuleFoodStats.init();

		//Client Rendering
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
			FurnaceHelper.iterate();
			FurnaceHelper.findFuels();
		}
	}


}
