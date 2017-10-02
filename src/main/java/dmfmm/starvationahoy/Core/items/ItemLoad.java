package dmfmm.starvationahoy.Core.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.starvationahoy.Core.lib.CoreLib;
import dmfmm.starvationahoy.StarvationAhoy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ItemLoad {
	
	public static Item stat_helm;
	public static Item stat_chest;
	public static Item HungerPotion;
	public static Item infoBook;
	
	public static void initItems(){
		stat_helm = new SaturationArmorTracker(StarvationAhoy.StatusArmor, EntityEquipmentSlot.HEAD, CoreLib.Helmet);
		stat_chest = new SaturationArmorTracker(StarvationAhoy.StatusArmor, EntityEquipmentSlot.CHEST, CoreLib.Chestplate);
		HungerPotion = ((ItemFood) new HungerPotion().setUnlocalizedName(CoreLib.potion)).setAlwaysEdible();
		infoBook = new InfoBook().setUnlocalizedName(CoreLib.book);
	}
	
	public static void registerItems(){
		// This is just some fancy reflection to register any static item in this class with name being watever it is declared is (public static
		for (Field item : ItemLoad.class.getFields()){
			if (item.getType() == Item.class){
				if (Modifier.isStatic(item.getModifiers())){
					Object toRegister;
					try {
						 toRegister = item.get(null);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
					if (toRegister instanceof Item){
						if (toRegister != null){
							registerItem((Item) toRegister, item.getName());
						}
					}
				}
				
			}
		}
	}
	private static void registerItem(Item item, String name){
		item.setRegistryName(name);
		GameRegistry.register(item);
	}
	
}
