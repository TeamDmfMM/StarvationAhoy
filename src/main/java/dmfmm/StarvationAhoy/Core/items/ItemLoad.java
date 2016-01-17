package dmfmm.StarvationAhoy.Core.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.CoreLib;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ItemLoad {
	
	public static Item stat_helm;
	public static Item stat_chest;
	public static Item HungerPotion;
	
	public static void initItems(){
		stat_helm = new SaturationArmorTracker(StarvationAhoy.StatusArmor, 0, CoreLib.Helmet);
		stat_chest = new SaturationArmorTracker(StarvationAhoy.StatusArmor, 1, CoreLib.Chestplate);
		HungerPotion = ((ItemFood) new HungerPotion().setUnlocalizedName(CoreLib.potion)).setAlwaysEdible();
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
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
					if (toRegister instanceof Item){
						if (toRegister != null){
							GameRegistry.registerItem((Item) toRegister, item.getName());
						}
					}
				}
				
			}
		}
	}
	
}
