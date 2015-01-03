package dmfmm.StarvationAhoy.Meat.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import net.minecraft.item.Item;

public class MItemLoader {
	private static boolean IRegister=false;  
	
	public static Item deadCow, deadPig, deadChicken;
	public static Item skinlessCow, skinlessPig, skinlessChicken;
	public static Item ButcherKnife, filetKnife;
	
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
			GameRegistry.registerItem(deadCow, MeatLib.iCowDead);
			GameRegistry.registerItem(deadPig, MeatLib.iPigDead);
			GameRegistry.registerItem(deadChicken, MeatLib.iChickenDead);
			GameRegistry.registerItem(skinlessCow, MeatLib.iCowSkinned);
			GameRegistry.registerItem(skinlessPig, MeatLib.iPigSkinned);
			GameRegistry.registerItem(skinlessChicken, MeatLib.iChickenSkinned);
			GameRegistry.registerItem(ButcherKnife, MeatLib.iButcherKnife);
			GameRegistry.registerItem(filetKnife, MeatLib.ifiletKnife);
			
			
			
			
		}
		IRegister=true;
	}

}
