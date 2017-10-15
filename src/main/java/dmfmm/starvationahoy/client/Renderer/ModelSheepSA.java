package dmfmm.starvationahoy.client.Renderer;


import dmfmm.starvationahoy.api.Meat.ISAModel;
import dmfmm.starvationahoy.api.Meat.ISpitRoastRender;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

/**
 * ModelSheep2 - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */
public class ModelSheepSA extends ModelBase implements ISAModel{
    public ModelRenderer bodys;
    public ModelRenderer leg4s;
    public ModelRenderer leg3s;
    public ModelRenderer leg2s;
    public ModelRenderer leg1s;
    public ModelRenderer heads;
    public ModelRenderer body;
    public ModelRenderer leg4;
    public ModelRenderer leg3;
    public ModelRenderer leg2;
    public ModelRenderer leg1;
    public ModelRenderer head;

    public ModelSheepSA() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leg3 = new ModelRenderer(this, 0, 48);
        this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg4s = new ModelRenderer(this, 0, 16);
        this.leg4s.setRotationPoint(3.0F, 12.0F, -5.0F);
        this.leg4s.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 48);
        this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg2 = new ModelRenderer(this, 0, 48);
        this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg2s = new ModelRenderer(this, 0, 16);
        this.leg2s.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.leg2s.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bodys = new ModelRenderer(this, 28, 8);
        this.bodys.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.bodys.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.setRotateAngle(bodys, 1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 32);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.leg1s = new ModelRenderer(this, 0, 16);
        this.leg1s.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.leg1s.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leg3s = new ModelRenderer(this, 0, 16);
        this.leg3s.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.leg3s.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.body = new ModelRenderer(this, 28, 40);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.heads = new ModelRenderer(this, 0, 0);
        this.heads.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.heads.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 48);
        this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.leg3.render(f5);
        this.leg4s.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg2s.render(f5);
        this.bodys.render(f5);
        this.head.render(f5);
        this.leg1s.render(f5);
        this.leg3s.render(f5);
        this.body.render(f5);
        this.heads.render(f5);
        this.leg4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void glTransformations() {
        GL11.glRotatef(90F, 1, 0, 0);
        GL11.glTranslated(0, -0.7, -1.9);
    }

    @Override
    public void modelTransformations() {
        this.isChild = false;
        this.leg1s.rotateAngleX = 77;
        this.leg2s.rotateAngleX = 77;
        this.leg1s.rotateAngleY = 0.2F;
        this.leg2s.rotateAngleY = -0.2F;
        this.leg3s.rotateAngleX = -77F;
        this.leg4s.rotateAngleX = -77F;
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


    public static class  ModelSheepSA2 extends ModelSheep2  implements ISAModel, ISpitRoastRender{
        public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn)
        {
            this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
            this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
            this.body.rotateAngleX = ((float)Math.PI / 2F);
        }

        @Override
        public void glTransformations() {
            GL11.glRotatef(90F, 1, 0, 0);
            GL11.glTranslated(0, -0.7, -1.9);
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
            float[] carl = {0, -1.2F, 0.3F, 1.2f, 2f, -0.57f};
            return carl;
        }
    }
}




