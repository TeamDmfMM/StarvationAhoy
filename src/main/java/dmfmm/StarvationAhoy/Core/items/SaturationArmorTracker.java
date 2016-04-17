package dmfmm.StarvationAhoy.Core.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.client.model.ModelBiped;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class SaturationArmorTracker extends ItemArmor{


	public SaturationArmorTracker(ArmorMaterial material, EntityEquipmentSlot type, String name) {
		super(material, 0, type);
        this.setCreativeTab(SATabs.INSTANCE);
		///this.setTextureName("StarvationAhoy:stats_" + type);
		this.setUnlocalizedName(name);
	}


	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if(stack.getItem().equals(ItemLoad.stat_helm)){
			return "starvationahoy:textures/armor/stats_1.png";
		}else{
			return "starvationahoy:textures/armor/body.png";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped model) {
		GL11.glEnable(GL11.GL_BLEND);
		ModelBiped armorModel = null;
		if(stack != null){
			if(stack.getItem() instanceof SaturationArmorTracker){
				EntityEquipmentSlot type = ((ItemArmor)stack.getItem()).armorType;
				if(type == EntityEquipmentSlot.CHEST || type == EntityEquipmentSlot.HEAD){
					armorModel = StarvationAhoy.proxy.getArmorModel(1);
				}else{
					armorModel = null;
				}
			}
			if(armorModel != null){
				/** Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots */
				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST || armorSlot == EntityEquipmentSlot.LEGS;
				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
				armorModel.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.leftArmPose = ModelBiped.ArmPose.EMPTY;
				armorModel.rightArmPose = ModelBiped.ArmPose.EMPTY;
				if(entityLiving instanceof EntityPlayer) {
					pleaseWork(entityLiving.getHeldItem(EnumHand.MAIN_HAND), entityLiving.getHeldItem(EnumHand.OFF_HAND), (EntityPlayer) entityLiving, armorModel);
				}
					GL11.glDisable(GL11.GL_BLEND);
				return armorModel;
			}
		}
		return null;
	}

	public static void pleaseWork(ItemStack itemstack, ItemStack itemstack1, EntityPlayer clientPlayer, ModelBiped modelplayer){
		ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
		ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
	if (itemstack != null)
	{
		modelbiped$armpose = ModelBiped.ArmPose.ITEM;

		if (clientPlayer.getItemInUseCount() > 0)
		{
			EnumAction enumaction = itemstack.getItemUseAction();

			if (enumaction == EnumAction.BLOCK)
			{
				modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
			}
			else if (enumaction == EnumAction.BOW)
			{
				modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
			}
		}
	}

	if (itemstack1 != null)
	{
		modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;

		if (clientPlayer.getItemInUseCount() > 0)
		{
			EnumAction enumaction1 = itemstack1.getItemUseAction();

			if (enumaction1 == EnumAction.BLOCK)
			{
				modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
			}
		}
	}

	if (clientPlayer.getPrimaryHand() == EnumHandSide.RIGHT)
	{
		modelplayer.rightArmPose = modelbiped$armpose;
		modelplayer.leftArmPose = modelbiped$armpose1;
	}
	else
	{
		modelplayer.rightArmPose = modelbiped$armpose1;
		modelplayer.leftArmPose = modelbiped$armpose;
	}
}


}
