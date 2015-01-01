package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelChickenSA extends ModelChicken{
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
}
