package dmfmm.starvationahoy.core.init;

import dmfmm.starvationahoy.StarvationAhoy;
import dmfmm.starvationahoy.base.ItemRegister;
import dmfmm.starvationahoy.core.items.HungerPotion;
import dmfmm.starvationahoy.core.items.InfoBook;
import dmfmm.starvationahoy.core.items.SaturationArmorTracker;
import dmfmm.starvationahoy.core.lib.CoreLib;
import jline.internal.Log;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

import java.lang.reflect.Field;

public class CoreItemRegistry {
	
	public static Item STAT_HELM = new SaturationArmorTracker(StarvationAhoy.StatusArmor, EntityEquipmentSlot.HEAD, CoreLib.STAT_HELM);;
	public static Item STAT_CHEST = new SaturationArmorTracker(StarvationAhoy.StatusArmor, EntityEquipmentSlot.CHEST, CoreLib.STAT_CHEST);
	public static Item HUNGER_POTION = new HungerPotion().setAlwaysEdible();
	public static Item INFO_BOOK = new InfoBook();


	public static void registerItems() {
		for (Field field : CoreItemRegistry.class.getFields()) {
			if (field.getType() == Item.class) {
				try {
					Item item = (Item) field.get(null);
					ItemRegister.addItem(item, item.getRegistryName().getResourcePath());
				} catch (IllegalAccessException e) {
					Log.warn("Somehow failed to get a block from its registrator. WAT?");
				}
			}
		}
	}

	
}
