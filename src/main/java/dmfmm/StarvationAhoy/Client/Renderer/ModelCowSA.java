package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelCow;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCowSA extends ModelCow{
	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        float f6 = (180F / (float)Math.PI);
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        //this.leg1.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        //this.leg2.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        //this.leg3.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        //this.leg4.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
    }

}
