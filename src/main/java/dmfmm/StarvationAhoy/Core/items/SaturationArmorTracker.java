package dmfmm.StarvationAhoy.Core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SaturationArmorTracker extends ItemArmor{

	public SaturationArmorTracker(ArmorMaterial material, int type, String name) {
		super(material, 0, type);
        this.setCreativeTab(SATabs.INSTANCE);
		this.setTextureName("StarvationAhoy:stats_" + type);
		this.setUnlocalizedName(name);
	}
	
	/*public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "StarvationAhoy:textures/armor/stats_1" + ".png";
	}*/

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(stack.getItem().equals(ItemLoad.stat_helm)){
			return "starvationahoy:textures/armor/helm";
		}else{
			return "starvationahoy:textures/armor/body.png";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		ModelBiped armorModel = null;
		if(stack != null){
			if(stack.getItem() instanceof SaturationArmorTracker){
				int type = ((ItemArmor)stack.getItem()).armorType;
				if(type == 1 || type == 3){
					armorModel = StarvationAhoy.proxy.getArmorModel(1);
				}else{
					armorModel = StarvationAhoy.proxy.getArmorModel(0); }
			}
			if(armorModel != null){
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 :0;
				if(entityLiving instanceof EntityPlayer){
					armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}
				return armorModel;
			}
		}
		return null;
	}

}
