package dmfmm.StarvationAhoy.Core.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class SaturationArmorTracker extends ItemArmor{

	public SaturationArmorTracker(ArmorMaterial material, int type, String name) {
		super(material, 0, type);
		this.setTextureName("StarvationAhoy:stats_" + type);
		this.setUnlocalizedName(name);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "StarvationAhoy:textures/armor/stats_1" + ".png";
	}
	

}
