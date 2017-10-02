package dmfmm.starvationahoy.Client.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMeatRoaster - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelMeatRoaster extends ModelBase {
    public ModelRenderer DownHandle;
    public ModelRenderer LSupport1;
    public ModelRenderer BackSupport1;
    public ModelRenderer LStick;
    public ModelRenderer BottomStick1;
    public ModelRenderer BackSupport;
    public ModelRenderer RSupport1;
    public ModelRenderer RStick;
    public ModelRenderer shape10;
    public ModelRenderer LSupport;
    public ModelRenderer RSupport;
    public ModelRenderer BottomStick;
    public ModelRenderer Bar;
    public ModelRenderer RStick1;
    public ModelRenderer FrontSupport;
    public ModelRenderer LStick1;
    public ModelRenderer FrontSupport1;

    public ModelMeatRoaster() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.FrontSupport = new ModelRenderer(this, 0, 0);
        this.FrontSupport.setRotationPoint(0.0F, 16.700000762939453F, 47.20000076293945F);
        this.FrontSupport.addBox(0.0F, -0.8999999761581421F, -0.20000000298023224F, 1, 9, 1, 1.7881393432617188E-7F);
        this.setRotateAngle(FrontSupport, -0.5918411612510681F, 0.0F, 0.0F);
        this.RStick = new ModelRenderer(this, 6, 0);
        this.RStick.setRotationPoint(1.0F, 2.700000047683716F, 48.20000076293945F);
        this.RStick.addBox(0.0F, 0.10000000149011612F, -0.20000000298023224F, 1, 4, 1, -4.842877388000488E-8F);
        this.BackSupport1 = new ModelRenderer(this, 0, 0);
        this.BackSupport1.setRotationPoint(0.0F, 17.0F, 1.0F);
        this.BackSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(BackSupport1, 0.4553563892841339F, 0.0F, 0.0F);
        this.LSupport = new ModelRenderer(this, 0, 0);
        this.LSupport.setRotationPoint(-1.0F, 16.700000762939453F, 48.20000076293945F);
        this.LSupport.addBox(0.0F, -0.8999999761581421F, -0.20000000298023224F, 1, 9, 1, 1.7881393432617188E-7F);
        this.setRotateAngle(LSupport, 0.0F, 0.0F, 0.5462880730628967F);
        this.Bar = new ModelRenderer(this, 0, 0);
        this.Bar.setRotationPoint(0.4F, 4.0F, -1.0F);
        this.Bar.addBox(-0.4F, 0.0F, 0.0F, 1, 1, 50, 0.0F);
        this.BackSupport = new ModelRenderer(this, 0, 0);
        this.BackSupport.setRotationPoint(0.0F, 16.700000762939453F, 49.20000076293945F);
        this.BackSupport.addBox(0.0F, -0.8999999761581421F, -0.20000000298023224F, 1, 9, 1, 1.7881393432617188E-7F);
        this.setRotateAngle(BackSupport, 0.4553563892841339F, 0.0F, 0.0F);
        this.LStick = new ModelRenderer(this, 6, 0);
        this.LStick.setRotationPoint(-1.0F, 2.700000047683716F, 48.20000076293945F);
        this.LStick.addBox(0.0F, 0.10000000149011612F, -0.20000000298023224F, 1, 4, 1, -4.842877388000488E-8F);
        this.RSupport = new ModelRenderer(this, 0, 0);
        this.RSupport.setRotationPoint(1.0F, 17.700000762939453F, 48.20000076293945F);
        this.RSupport.addBox(0.0F, -0.8999999761581421F, -0.20000000298023224F, 1, 9, 1, 1.7881393432617188E-7F);
        this.setRotateAngle(RSupport, 0.0F, 0.0F, -0.6373942494392395F);
        this.shape10 = new ModelRenderer(this, 17, 0);
        this.shape10.setRotationPoint(0.4F, 4.0F, -1.0F);
        this.shape10.addBox(-0.4F, 6.0F, -3.9F, 1, 1, 3, 0.0F);
        this.LSupport1 = new ModelRenderer(this, 0, 0);
        this.LSupport1.setRotationPoint(-1.0F, 17.0F, 0.0F);
        this.LSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(LSupport1, 0.0F, 0.0F, 0.5462880730628967F);
        this.BottomStick1 = new ModelRenderer(this, 11, 0);
        this.BottomStick1.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.BottomStick1.addBox(0.0F, 0.0F, 0.0F, 1, 19, 1, 0.0F);
        this.RSupport1 = new ModelRenderer(this, 0, 0);
        this.RSupport1.setRotationPoint(1.0F, 18.0F, 0.0F);
        this.RSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(RSupport1, 0.0F, 0.0F, -0.6373942494392395F);
        this.LStick1 = new ModelRenderer(this, 6, 0);
        this.LStick1.setRotationPoint(-1.0F, 3.0F, 0.0F);
        this.LStick1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.RStick1 = new ModelRenderer(this, 6, 0);
        this.RStick1.setRotationPoint(1.0F, 3.0F, 0.0F);
        this.RStick1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.DownHandle = new ModelRenderer(this, 27, 0);
        this.DownHandle.setRotationPoint(0.4F, 4.0F, -1.0F);
        this.DownHandle.addBox(-0.4F, 0.0F, -0.2F, 1, 7, 1, 0.0F);
        this.setRotateAngle(DownHandle, -0.22759093446006054F, 0.0F, 0.0F);
        this.FrontSupport1 = new ModelRenderer(this, 0, 0);
        this.FrontSupport1.setRotationPoint(0.0F, 17.0F, -1.0F);
        this.FrontSupport1.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(FrontSupport1, -0.5918411612510681F, 0.0F, 0.0F);
        this.BottomStick = new ModelRenderer(this, 11, 0);
        this.BottomStick.setRotationPoint(0.0F, 4.699999809265137F, 48.20000076293945F);
        this.BottomStick.addBox(0.0F, 0.10000000149011612F, -0.20000000298023224F, 1, 19, 1, 1.8998980522155762E-7F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.FrontSupport.render(f5);
        this.RStick.render(f5);
        this.BackSupport1.render(f5);
        this.LSupport.render(f5);
        this.Bar.render(f5);
        this.BackSupport.render(f5);
        this.LStick.render(f5);
        this.RSupport.render(f5);
        this.shape10.render(f5);
        this.LSupport1.render(f5);
        this.BottomStick1.render(f5);
        this.RSupport1.render(f5);
        this.LStick1.render(f5);
        this.RStick1.render(f5);
        this.DownHandle.render(f5);
        this.FrontSupport1.render(f5);
        this.BottomStick.render(f5);
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
