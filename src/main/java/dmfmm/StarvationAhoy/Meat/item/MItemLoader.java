package dmfmm.StarvationAhoy.Meat.item;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;

public class MItemLoader {
private static boolean IRegister=false;  
	
	public static Item deadCow, deadPig, deadChicken;
	public static Item skinlessCow, skinlessPig, skinlessChicken;
	public static Item ButcherKnife, filetKnife;

	public static Map<String, Item> modMeatItems = new HashMap<>();
	
	public static void initiateItems() {
		deadCow = new DeadEntity(MeatLib.iCowDead, "starvationahoy:TEXTURENAME");
		deadPig = new DeadEntity(MeatLib.iPigDead, "starvationahoy:TEXTURENAME");
		deadChicken = new DeadEntity(MeatLib.iChickenDead, "starvationahoy:TEXTURENAME");
		skinlessCow = new SkinnedEntity(MeatLib.iCowSkinned, "starvationahoy:TEXTURENAME");
		skinlessPig = new SkinnedEntity(MeatLib.iPigSkinned, "starvationahoy:TEXTURENAME");
		skinlessChicken = new SkinnedEntity(MeatLib.iChickenSkinned, "starvationahoy:TEXTURENAME");
		ButcherKnife = new ButcherKnife().setUnlocalizedName(MeatLib.iButcherKnife);
		filetKnife = new FiletKnife().setUnlocalizedName(MeatLib.ifiletKnife);


		
		registerItems();
	}


	private static void registerItems() {
		if(!IRegister){
			for (Field item : MItemLoader.class.getFields()){
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

			for (String meat : modMeatItems.keySet()) {
				GameRegistry.registerItem(modMeatItems.get(meat), meat);
			}

			
			
			
			
		}
		IRegister=true;
	}
}
