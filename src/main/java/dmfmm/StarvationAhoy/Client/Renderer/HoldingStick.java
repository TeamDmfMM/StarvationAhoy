package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * HoldingStick - dmf444
 * Created using Tabula 4.1.1
 */
public class HoldingStick extends ModelBase {
    public ModelRenderer BottomStick;
    public ModelRenderer LStick;
    public ModelRenderer RStick;
    public ModelRenderer BackSupport;
    public ModelRenderer LSupport;
    public ModelRenderer RSupport;
    public ModelRenderer FrontSupport;

    public HoldingStick() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LStick = new ModelRenderer(this, 6, 0);
        this.LStick.setRotationPoint(-1.0F, 3.0F, 0.0F);
        this.LStick.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.BottomStick = new ModelRenderer(this, 11, 0);
        this.BottomStick.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.BottomStick.addBox(0.0F, 0.0F, 0.0F, 1, 19, 1, 0.0F);
        this.BackSupport = new ModelRenderer(this, 0, 0);
        this.BackSupport.setRotationPoint(0.0F, 17.0F, 1.0F);
        this.BackSupport.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(BackSupport, 0.4553564018453205F, 0.0F, 0.0F);
        this.FrontSupport = new ModelRenderer(this, 0, 0);
        this.FrontSupport.setRotationPoint(0.0F, 17.0F, -1.0F);
        this.FrontSupport.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(FrontSupport, -0.5918411493512771F, 0.0F, 0.0F);
        this.RSupport = new ModelRenderer(this, 0, 0);
        this.RSupport.setRotationPoint(1.0F, 18.0F, 0.0F);
        this.RSupport.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(RSupport, 0.0F, 0.0F, -0.6373942428283291F);
        this.LSupport = new ModelRenderer(this, 0, 0);
        this.LSupport.setRotationPoint(-1.0F, 17.0F, 0.0F);
        this.LSupport.addBox(0.0F, -1.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(LSupport, 0.0F, 0.0F, 0.5462880558742251F);
        this.RStick = new ModelRenderer(this, 6, 0);
        this.RStick.setRotationPoint(1.0F, 3.0F, 0.0F);
        this.RStick.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LStick.render(f5);
        this.BottomStick.render(f5);
        this.BackSupport.render(f5);
        this.FrontSupport.render(f5);
        this.RSupport.render(f5);
        this.LSupport.render(f5);
        this.RStick.render(f5);
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
