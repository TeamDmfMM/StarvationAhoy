package dmfmm.StarvationAhoy.Meat.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MItemLoader {
private static boolean IRegister=false;  
	
	public static Item deadCow, deadPig, deadChicken;
	public static Item skinlessCow, skinlessPig, skinlessChicken;
	public static Item ButcherKnife, filetKnife;
    public static Item pigleg;

	public static Map<String, Item> modMeatItems = new HashMap<>();
	
	public static void initiateItems() {
		deadCow = new DeadEntity(MeatLib.iCowDead);
		deadPig = new DeadEntity(MeatLib.iPigDead);
		deadChicken = new DeadEntity(MeatLib.iChickenDead);
		skinlessCow = new SkinnedEntity(MeatLib.iCowSkinned);
		skinlessPig = new SkinnedEntity(MeatLib.iPigSkinned);
		skinlessChicken = new SkinnedEntity(MeatLib.iChickenSkinned);
		ButcherKnife = new ButcherKnife().setUnlocalizedName(MeatLib.iButcherKnife);
		filetKnife = new FiletKnife().setUnlocalizedName(MeatLib.ifiletKnife);
        pigleg = new StanFood(5, 6.3F).setUnlocalizedName(MeatLib.iPigLeg);


		
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
