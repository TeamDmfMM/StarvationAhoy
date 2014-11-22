package dmfmm.StarvationAhoy.items;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import dmfmm.StarvationAhoy.StarvationAhoy;


public class ItemLoad {
	
	public static Item stat_helm;
	
	public static void initItems(){
		stat_helm = new SaturationArmorThingy(StarvationAhoy.StatusArmor, 0, "stat_helm");
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
