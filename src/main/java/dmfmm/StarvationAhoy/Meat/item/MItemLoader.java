package dmfmm.StarvationAhoy.Meat.item;

import net.minecraftforge.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MItemLoader {
private static boolean IRegister=false;  
	
	public static Item deadCow, deadPig, deadChicken, deadSheep, deadRabbit;
	public static Item skinlessCow, skinlessPig, skinlessChicken, skinlessSheep, skinlessRabbit;
	public static Item ButcherKnife, filetKnife;
    public static Item pigleg, cookedpigleg;

	public static Map<String, Item> modMeatItems = new HashMap<>();
	
	public static void initiateItems() {
		deadCow = new DeadEntity(MeatLib.iCowDead);
		deadPig = new DeadEntity(MeatLib.iPigDead);
		deadChicken = new DeadEntity(MeatLib.iChickenDead);
		deadRabbit = new DeadEntity(MeatLib.iRabbitDead);
		deadSheep = new DeadEntity(MeatLib.iSheepDead);
		skinlessCow = new SkinnedEntity(MeatLib.iCowSkinned);
		skinlessPig = new SkinnedEntity(MeatLib.iPigSkinned);
		skinlessChicken = new SkinnedEntity(MeatLib.iChickenSkinned);
		skinlessRabbit = new SkinnedEntity(MeatLib.iRabbitSkinned);
		skinlessSheep = new SkinnedEntity(MeatLib.iSheepSkinned);
		ButcherKnife = new ButcherKnife().setUnlocalizedName(MeatLib.iButcherKnife);
		filetKnife = new FiletKnife().setUnlocalizedName(MeatLib.ifiletKnife);
        pigleg = new StanFood(5, 6.3F).setUnlocalizedName(MeatLib.iPigLeg);
		cookedpigleg = new StanFood(8, 5.7F).setUnlocalizedName(MeatLib.iCookedPigLeg);


		
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

			for (String meat : modMeatItems.keySet()) {
				registerItem(modMeatItems.get(meat), meat);
			}

			
			
			
			
		}
		IRegister=true;
	}

	private static void registerItem(Item item, String name){
		item.setRegistryName(name);
		GameRegistry.register(item);
	}
}
