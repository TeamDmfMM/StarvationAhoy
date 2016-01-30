package dmfmm.StarvationAhoy.Client.Renderer;

import dmfmm.StarvationAhoy.api.Meat.ISAModel;
import dmfmm.StarvationAhoy.api.Meat.ISpitRoastRender;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class ModelCowSA extends ModelCow implements ISAModel, ISpitRoastRender{
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

    @Override
    public void glTransformations() {
        GL11.glRotatef(90F, 1, 0, 0);
        GL11.glTranslatef(0, -0.65F, -1.9F);
    }

    @Override
    public void modelTransformations() {
        this.isChild = false;
        this.leg1.rotateAngleX = 77;
        this.leg2.rotateAngleX = 77;
        this.leg1.rotateAngleY = 0.2F;
        this.leg2.rotateAngleY = -0.2F;
        this.leg3.rotateAngleX = -77F;
        this.leg4.rotateAngleX = -77F;
    }

    @Override
    public AxisAlignedBB getMeatAABB(double x, double minX, double maxX, double y, double minY, double maxY, double z, double minZ, double maxZ) {
        return new AxisAlignedBB((double) x + minX, (double) y + minY - 1.6F, (double) z + minZ, (double) x + maxX, (double) y + maxY, (double) z + maxZ);
    }

    @Override
    public ModelBase updateExistingModel(ModelBase change) {
        return change;
    }

    @Override
    public float[] getTranslations() {
        float[] carl = {0, -0.95F, -1.9F, 3.42f, 1.871f, -0.66f};
        return carl;
    }
}
