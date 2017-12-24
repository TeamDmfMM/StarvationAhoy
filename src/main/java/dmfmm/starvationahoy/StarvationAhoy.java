package dmfmm.starvationahoy;

import dmfmm.starvationahoy.api.StarvationAhoyRegistry;
import dmfmm.starvationahoy.client.gui.book_gui.FurnaceHelper;
import dmfmm.starvationahoy.core.CoreRecipies;
import dmfmm.starvationahoy.core.GuiHandler;
import dmfmm.starvationahoy.core.IMCRerouter;
import dmfmm.starvationahoy.core.StarvationAhoyProvider;
import dmfmm.starvationahoy.core.event.ConfigChangeEvent;
import dmfmm.starvationahoy.core.init.CoreItemRegistry;
import dmfmm.starvationahoy.core.lib.ModInfo;
import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.core.util.SALog;
import dmfmm.starvationahoy.crops.ModuleCropWash;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.meat.block.multiblock.net.PacketMultiBlock;
import dmfmm.starvationahoy.proxy.CommonProxy;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUI_FACTORY)
public class StarvationAhoy {
	
	@Instance(value = ModInfo.MOD_ID)
	public static StarvationAhoy instance;
	
	public static ArmorMaterial StatusArmor = EnumHelper.addArmorMaterial("statusarmor", "", 16, new int[]{2,5,2,1}, 21, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2);
	//public static TileEntityBanner.EnumBannerPattern pattern;
	public static String DIR;

	public static Side side;
	public static SimpleNetworkWrapper MultiBlockChannel;
	@SidedProxy(clientSide= ModInfo.CLIENT_PROXY, serverSide= ModInfo.SERVER_PROXY)
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
		MinecraftForge.EVENT_BUS.register(new ConfigChangeEvent());


		//Module Initiation
		CoreItemRegistry.registerItems();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		//pattern = addBannerIcon("starvationAhoy", "sta", new ItemStack(CoreItemRegistry.HUNGER_POTION));

		//Packet Initiation
		MultiBlockChannel = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
		MultiBlockChannel.registerMessage(PacketMultiBlock.Handler.class, PacketMultiBlock.class, 0, Side.CLIENT);

        //MultiBlockChannel.registerMessage(ClientGetExhaustPacket.Handler.class, ClientGetExhaustPacket.class, 1, Side.CLIENT);
        //MultiBlockChannel.registerMessage(ServerGetExhaustPacket.Handler.class, ServerGetExhaustPacket.class, 2, Side.SERVER);

		//Secondary events
		proxy.preInit();
		proxy.initSounds();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){

		//Module Loading
		CoreRecipies.registerRecipies();
		ModuleCropWash.init();
		ModuleMeat.init();
		//ModuleFoodStats.init();


		//client Rendering
		proxy.registerRenderers();
		//proxy.registerKeyBindings();

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
