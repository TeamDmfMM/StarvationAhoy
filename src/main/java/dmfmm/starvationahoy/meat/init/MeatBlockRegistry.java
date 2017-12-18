package dmfmm.starvationahoy.meat.init;

import dmfmm.starvationahoy.core.lib.MeatLib;
import dmfmm.starvationahoy.meat.block.Cooker;
import dmfmm.starvationahoy.meat.block.HoldingStick;
import dmfmm.starvationahoy.meat.block.MeatHanger;
import dmfmm.starvationahoy.meat.block.multiblock.CookerTileEntity;
import dmfmm.starvationahoy.meat.block.tileentity.HoldingStickTileEntity;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerTileEntity;
import jline.internal.Log;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class MeatBlockRegistry {

	public static Block	MeatHanger = new MeatHanger();
	public static Block	HoldingStick = new HoldingStick();
	public static Block	Cooker = new Cooker();


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		//if(CRef.useMeatOverride()) {
			IForgeRegistry<Block> registry = event.getRegistry();

			for (Field field : MeatBlockRegistry.class.getDeclaredFields()) {
				if (field.getType() == Block.class) {
					try {
						registry.register((Block) field.get(null));
					} catch (IllegalAccessException e) {
						Log.warn("Somehow failed to get a block from its registrator. WAT?");
					}
				}
			}

			initTileEntity();
		//}
	}

	public static void initTileEntity() {
		GameRegistry.registerTileEntity(MeatHangerTileEntity.class, MeatLib.MEAT_HANGER);
		GameRegistry.registerTileEntity(HoldingStickTileEntity.class, MeatLib.HOLDING_STICK);
		GameRegistry.registerTileEntity(CookerTileEntity.class, "DEV__TILE__cooker");

	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		//if(CRef.useMeatOverride()) {
			IForgeRegistry<Item> registry = event.getRegistry();
			for (Field field : MeatBlockRegistry.class.getDeclaredFields()) {
				if (field.getType() == Block.class) {
					try {
						Block theBlock = (Block) field.get(null);
						registry.register(new ItemBlock(theBlock).setRegistryName(theBlock.getRegistryName()));
					} catch (IllegalAccessException e) {
						Log.warn("Somehow failed to get a block from its registrator. WAT?");
					}
				}
			}
		//}
	}
}
