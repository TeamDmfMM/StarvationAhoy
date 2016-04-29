package dmfmm.StarvationAhoy.Client.Renderer;

import dmfmm.StarvationAhoy.api.Meat.ISAModel;
import dmfmm.StarvationAhoy.api.Meat.ISpitRoastRender;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelChickenSA extends ModelChicken implements ISAModel, ISpitRoastRender{
	 public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
	    {
	        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
	        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
	        this.bill.rotateAngleX = this.head.rotateAngleX;
	        this.bill.rotateAngleY = this.head.rotateAngleY;
	        this.chin.rotateAngleX = this.head.rotateAngleX;
	        this.chin.rotateAngleY = this.head.rotateAngleY;
	        this.body.rotateAngleX = ((float)Math.PI / 2F);
	        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
	        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
	        //this.rightWing.rotateAngleZ = p_78087_3_;
	       // this.leftWing.rotateAngleZ = -p_78087_3_;
	    }

	@Override
	public void glTransformations() {
		GL11.glRotatef(180F, 1, 0, 0);
		GL11.glRotatef(180F, 0, 1, 0);
		GL11.glTranslatef(0, -2.3F, 0.2F);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	@Override
	public void modelTransformations() {
		this.isChild = false;
		this.rightWing.offsetX = 0.1F;
		this.rightWing.rotateAngleZ = 361.2F;
		this.leftWing.offsetX = -0.1F;
		this.leftWing.rotateAngleZ = 361.2F;
	}

	@Override
	public AxisAlignedBB getMeatAABB(double x, double minX, double maxX, double y, double minY, double maxY, double z, double minZ, double maxZ) {
		return new AxisAlignedBB(
				x + minX,
				y + minY - 0.3F,
				z + minZ,
				x + maxX,
				y +maxY,
				z + maxZ
		);
	}

	@Override
	public ModelBase updateExistingModel(ModelBase change) {
		return change;
	}

	@Override
	public float[] getTranslations() {
		float[] carl = {0, -1.40F, 01.5F, 0.62f, 3.50f};
		return carl;
	}
}
