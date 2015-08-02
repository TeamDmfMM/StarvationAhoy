package dmfmm.StarvationAhoy.Core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

public class SaturationArmorTracker extends ItemArmor{


	public SaturationArmorTracker(ArmorMaterial material, int type, String name) {
		super(material, 0, type);
        this.setCreativeTab(SATabs.INSTANCE);
		this.setTextureName("StarvationAhoy:stats_" + type);
		this.setUnlocalizedName(name);
	}


	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(stack.getItem().equals(ItemLoad.stat_helm)){
			return "starvationahoy:textures/armor/stats_1.png";
		}else{
			return "starvationahoy:textures/armor/body.png";
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return false;
	}
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_)
	{
		return this.getIconFromDamage(p_77618_1_);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		GL11.glEnable(GL11.GL_BLEND);
		ModelBiped armorModel = null;
		if(stack != null){
			if(stack.getItem() instanceof SaturationArmorTracker){
				int type = ((ItemArmor)stack.getItem()).armorType;
				if(type == 1 || type == 3){
					armorModel = StarvationAhoy.proxy.getArmorModel(1);
				}else{
					armorModel = null;
				}
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
				GL11.glDisable(GL11.GL_BLEND);
				return armorModel;
			}
		}
		return null;
	}

}
