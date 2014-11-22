package dmfmm.StarvationAhoy.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SaturationArmorThingy extends ItemArmor{

	public SaturationArmorThingy(ArmorMaterial material, int type,
			String name) {
		super(material, 0, type);
		this.setTextureName("StarvationAhoy:" + "stats");
		this.setUnlocalizedName(name);
	
		
		// TODO Auto-generated constructor stub
	}
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "StarvationAhoy:armor/stats_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	

}
