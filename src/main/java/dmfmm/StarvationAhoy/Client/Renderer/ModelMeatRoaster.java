package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * HoldingStick - dmf444
 * Created using Tabula 4.1.1
 */
public class ModelMeatRoaster extends ModelBase {
    public ModelRenderer Bar;
    public ModelRenderer DownHandle;
    public ModelRenderer shape10;
    public ModelRenderer BottomStick;
    public ModelRenderer LStick;
    public ModelRenderer RStick;
    public ModelRenderer BackSupport;
    public ModelRenderer LSupport;
    public ModelRenderer RSupport;
    public ModelRenderer FrontSupport;
    public ModelRenderer BottomStick1;
    public ModelRenderer LStick1;
    public ModelRenderer RStick1;
    public ModelRenderer BackSupport1;
    public ModelRenderer LSupport1;
    public ModelRenderer RSupport1;
    public ModelRenderer FrontSupport1;

    public ModelMeatRoaster() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.BackSupport1 = new ModelRenderer(this, 0, 0);
        this.BackSupport1.setRotationPoint(0.0F, 17.0F, 1.0F);
        this.BackSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(BackSupport1, 0.4553564018453205F, 0.0F, 0.0F);
        this.shape10 = new ModelRenderer(this, 17, 0);
        this.shape10.setRotationPoint(0.0F, 4.0F, -1.0F);
        this.shape10.addBox(0.0F, 6.0F, -3.9F, 1, 1, 3, 0.0F);
        this.RStick1 = new ModelRenderer(this, 6, 0);
        this.RStick1.setRotationPoint(1.0F, 3.0F, 0.0F);
        this.RStick1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.FrontSupport = new ModelRenderer(this, 0, 0);
        this.FrontSupport.setRotationPoint(0.0F, 16.699999999999996F, 47.20000000000011F);
        this.FrontSupport.addBox(0.0F, -0.9F, -0.19999999999999998F, 1, 9, 1, 0.0F);
        this.setRotateAngle(FrontSupport, -0.5918411493512771F, 0.0F, 0.0F);
        this.DownHandle = new ModelRenderer(this, 27, 0);
        this.DownHandle.setRotationPoint(0.0F, 4.0F, -1.0F);
        this.DownHandle.addBox(0.0F, 0.0F, -0.2F, 1, 7, 1, 0.0F);
        this.setRotateAngle(DownHandle, -0.22759093446006054F, 0.0F, 0.0F);
        this.LStick = new ModelRenderer(this, 6, 0);
        this.LStick.setRotationPoint(-1.0F, 2.6999999999999997F, 48.20000000000011F);
        this.LStick.addBox(0.0F, 0.10000000000000003F, -0.19999999999999998F, 1, 4, 1, 0.0F);
        this.BackSupport = new ModelRenderer(this, 0, 0);
        this.BackSupport.setRotationPoint(0.0F, 16.699999999999996F, 49.20000000000011F);
        this.BackSupport.addBox(0.0F, -0.9F, -0.19999999999999998F, 1, 9, 1, 0.0F);
        this.setRotateAngle(BackSupport, 0.4553564018453205F, 0.0F, 0.0F);
        this.BottomStick1 = new ModelRenderer(this, 11, 0);
        this.BottomStick1.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.BottomStick1.addBox(0.0F, 0.0F, 0.0F, 1, 19, 1, 0.0F);
        this.BottomStick = new ModelRenderer(this, 11, 0);
        this.BottomStick.setRotationPoint(0.0F, 4.700000000000001F, 48.20000000000011F);
        this.BottomStick.addBox(0.0F, 0.10000000000000003F, -0.19999999999999998F, 1, 19, 1, 0.0F);
        this.LSupport = new ModelRenderer(this, 0, 0);
        this.LSupport.setRotationPoint(-1.0F, 16.699999999999996F, 48.20000000000011F);
        this.LSupport.addBox(0.0F, -0.9F, -0.19999999999999998F, 1, 9, 1, 0.0F);
        this.setRotateAngle(LSupport, 0.0F, 0.0F, 0.5462880558742251F);
        this.LSupport1 = new ModelRenderer(this, 0, 0);
        this.LSupport1.setRotationPoint(-1.0F, 17.0F, 0.0F);
        this.LSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(LSupport1, 0.0F, 0.0F, 0.5462880558742251F);
        this.RStick = new ModelRenderer(this, 6, 0);
        this.RStick.setRotationPoint(1.0F, 2.6999999999999997F, 48.20000000000011F);
        this.RStick.addBox(0.0F, 0.10000000000000003F, -0.19999999999999998F, 1, 4, 1, 0.0F);
        this.LStick1 = new ModelRenderer(this, 6, 0);
        this.LStick1.setRotationPoint(-1.0F, 3.0F, 0.0F);
        this.LStick1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.FrontSupport1 = new ModelRenderer(this, 0, 0);
        this.FrontSupport1.setRotationPoint(0.0F, 17.0F, -1.0F);
        this.FrontSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(FrontSupport1, -0.5918411493512771F, 0.0F, 0.0F);
        this.RSupport = new ModelRenderer(this, 0, 0);
        this.RSupport.setRotationPoint(1.0F, 17.699999999999996F, 48.20000000000011F);
        this.RSupport.addBox(0.0F, -0.9F, -0.19999999999999998F, 1, 9, 1, 0.0F);
        this.setRotateAngle(RSupport, 0.0F, 0.0F, -0.6373942428283291F);
        this.RSupport1 = new ModelRenderer(this, 0, 0);
        this.RSupport1.setRotationPoint(1.0F, 18.0F, 0.0F);
        this.RSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(RSupport1, 0.0F, 0.0F, -0.6373942428283291F);
        this.Bar = new ModelRenderer(this, 0, 0);
        this.Bar.setRotationPoint(0.0F, 4.0F, -1.0F);
        this.Bar.addBox(0.0F, 0.0F, 0.0F, 1, 1, 50, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.BackSupport1.render(f5);
        this.shape10.render(f5);
        this.RStick1.render(f5);
        this.FrontSupport.render(f5);
        this.DownHandle.render(f5);
        this.LStick.render(f5);
        this.BackSupport.render(f5);
        this.BottomStick1.render(f5);
        this.BottomStick.render(f5);
        this.LSupport.render(f5);
        this.LSupport1.render(f5);
        this.RStick.render(f5);
        this.LStick1.render(f5);
        this.FrontSupport1.render(f5);
        this.RSupport.render(f5);
        this.RSupport1.render(f5);
        this.Bar.render(f5);
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
