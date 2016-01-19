package dmfmm.StarvationAhoy.Client.Renderer;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRabbitSA extends ModelBase {
    public ModelRenderer rabbitLeftEar;
    public ModelRenderer rabbitNose;
    public ModelRenderer rabbitHead;
    public ModelRenderer rabbitTail;
    public ModelRenderer rabbitLeftThigh;
    public ModelRenderer rabbitLeftFoot;
    public ModelRenderer rabbitRightThigh;
    public ModelRenderer rabbitBody;
    public ModelRenderer rabbitRightArm;
    public ModelRenderer rabbitRightFoot;
    public ModelRenderer rabbitLeftArm;
    public ModelRenderer rabbitRightEar;

    public ModelRabbitSA() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rabbitLeftFoot = new ModelRenderer(this, 26, 24);
        this.rabbitLeftFoot.setRotationPoint(3.0F, 15.8F, 3.7F);
        this.rabbitLeftFoot.addBox(0.0F, 5.6F, 4.9F, 2, 1, 7, 0.0F);
        this.setRotateAngle(rabbitLeftFoot, -0.3490658503988659F, -0.3490658503988659F, 0.0F);
        this.rabbitHead = new ModelRenderer(this, 32, 0);
        this.rabbitHead.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.rabbitHead.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5, 0.0F);
        this.setRotateAngle(rabbitHead, -0.3490658503988659F, 0.0F, 0.0F);
        this.rabbitRightFoot = new ModelRenderer(this, 8, 24);
        this.rabbitRightFoot.mirror = true;
        this.rabbitRightFoot.setRotationPoint(-3.0F, 15.8F, 3.7F);
        this.rabbitRightFoot.addBox(-2.0F, 5.6F, 4.9F, 2, 1, 7, 0.0F);
        this.setRotateAngle(rabbitRightFoot, -0.3490658503988659F, 0.3490658503988659F, 0.0F);
        this.rabbitBody = new ModelRenderer(this, 0, 0);
        this.rabbitBody.setRotationPoint(0.0F, 19.0F, 8.0F);
        this.rabbitBody.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10, 0.0F);
        this.setRotateAngle(rabbitBody, -0.34906584024429316F, 0.0F, 0.0F);
        this.rabbitRightEar = new ModelRenderer(this, 52, 0);
        this.rabbitRightEar.setRotationPoint(0.0F, 12.3F, 3.5F);
        this.rabbitRightEar.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1, 0.0F);
        this.setRotateAngle(rabbitRightEar, 1.2304571226560024F, 0.0F, 0.0F);
        this.rabbitLeftArm = new ModelRenderer(this, 8, 15);
        this.rabbitLeftArm.setRotationPoint(3.0F, 17.0F, -1.0F);
        this.rabbitLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(rabbitLeftArm, -1.9547687622336491F, 0.0F, 0.0F);
        this.rabbitLeftThigh = new ModelRenderer(this, 30, 15);
        this.rabbitLeftThigh.setRotationPoint(3.0F, 17.5F, 3.700000047683716F);
        this.rabbitLeftThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(rabbitLeftThigh, -0.36651915311813354F, 0.0F, 0.0F);
        this.rabbitRightThigh = new ModelRenderer(this, 16, 15);
        this.rabbitRightThigh.setRotationPoint(-3.0F, 17.5F, 3.700000047683716F);
        this.rabbitRightThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(rabbitRightThigh, -0.36651915311813354F, 0.0F, 0.0F);
        this.rabbitRightArm = new ModelRenderer(this, 0, 15);
        this.rabbitRightArm.setRotationPoint(-3.0F, 17.0F, -1.0F);
        this.rabbitRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(rabbitRightArm, -1.955990492710045F, 0.0F, 0.0F);
        this.rabbitLeftEar = new ModelRenderer(this, 58, 0);
        this.rabbitLeftEar.setRotationPoint(0.0F, 12.3F, 3.5F);
        this.rabbitLeftEar.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1, 0.0F);
        this.setRotateAngle(rabbitLeftEar, 1.2269664641520137F, 0.0F, 0.0F);
        this.rabbitTail = new ModelRenderer(this, 52, 6);
        this.rabbitTail.setRotationPoint(0.0F, 20.0F, 7.0F);
        this.rabbitTail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(rabbitTail, -0.349065899848938F, 0.0F, 0.0F);
        this.rabbitNose = new ModelRenderer(this, 32, 9);
        this.rabbitNose.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.rabbitNose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(rabbitNose, -0.3490658503988659F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.rabbitLeftFoot.render(f5);
        this.rabbitHead.render(f5);
        this.rabbitRightFoot.render(f5);
        this.rabbitBody.render(f5);
        this.rabbitRightEar.render(f5);
        this.rabbitLeftArm.render(f5);
        this.rabbitLeftThigh.render(f5);
        this.rabbitRightThigh.render(f5);
        this.rabbitRightArm.render(f5);
        this.rabbitLeftEar.render(f5);
        this.rabbitTail.render(f5);
        this.rabbitNose.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
