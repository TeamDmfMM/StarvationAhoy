package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * WashBarrelModel - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class WashBarrelModel extends ModelBase {
    public ModelRenderer southbasePiece1;
    public ModelRenderer southbasePiece2;
    public ModelRenderer leftbasePiece;
    public ModelRenderer counterbasePiece;
    public ModelRenderer basePiece;
    public ModelRenderer leftbasePiece2;
    public ModelRenderer leftbasePiece1;
    public ModelRenderer rightbasePiece1;
    public ModelRenderer rightbasePiece2;
    public ModelRenderer Bottom_Square;
    public ModelRenderer rightbasePiece;
    public ModelRenderer northbasePiece2;
    public ModelRenderer WaterRender;
    public ModelRenderer northbasePiece1;

    public WashBarrelModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.counterbasePiece = new ModelRenderer(this, 0, 0);
        this.counterbasePiece.setRotationPoint(0.0F, 13.0F, 6.5F);
        this.counterbasePiece.addBox(-1.5F, 0.0F, -0.5F, 3, 11, 1, 0.0F);
        this.setRotateAngle(counterbasePiece, 0.0F, 3.1415927410125732F, 0.0F);
        this.leftbasePiece1 = new ModelRenderer(this, 0, 0);
        this.leftbasePiece1.setRotationPoint(-5.5F, 12.399999618530273F, 2.9000000953674316F);
        this.leftbasePiece1.addBox(-1.5F, 0.0F, -0.5F, 3, 12, 1, 0.0F);
        this.setRotateAngle(leftbasePiece1, 0.0F, 2.2310543060302734F, 0.0F);
        this.rightbasePiece2 = new ModelRenderer(this, 0, 15);
        this.rightbasePiece2.setRotationPoint(2.799999952316284F, 12.699999809265137F, -5.5F);
        this.rightbasePiece2.addBox(-1.5F, 0.0F, -0.5F, 4, 12, 1, 0.0F);
        this.setRotateAngle(rightbasePiece2, 0.0F, -0.5462880730628967F, 0.0F);
        //this.WaterRender = new ModelRenderer(this, -4, 16);
        //this.WaterRender.setRotationPoint(-8.0F, 23.0F, -8.0F);
        //this.WaterRender.addBox(0.0F, 0.0F, 0.0F, 16, 0, 16, 0.0F);
        this.leftbasePiece = new ModelRenderer(this, 0, 0);
        this.leftbasePiece.setRotationPoint(-6.5F, 13.0F, 0.0F);
        this.leftbasePiece.addBox(-1.5F, 0.0F, -0.5F, 3, 11, 1, 0.0F);
        this.setRotateAngle(leftbasePiece, 0.0F, 1.5707963705062866F, 0.0F);
        this.northbasePiece1 = new ModelRenderer(this, 0, 0);
        this.northbasePiece1.setRotationPoint(-5.5F, 12.399999618530273F, -2.9000000953674316F);
        this.northbasePiece1.addBox(-1.5F, 0.0F, -0.5F, 3, 12, 1, 0.0F);
        this.setRotateAngle(northbasePiece1, 0.0F, 1.001644492149353F, 0.0F);
        this.southbasePiece1 = new ModelRenderer(this, 0, 0);
        this.southbasePiece1.setRotationPoint(5.599999904632568F, 12.399999618530273F, 3.0F);
        this.southbasePiece1.addBox(-1.5F, 0.0F, -0.5F, 3, 12, 1, 0.0F);
        this.setRotateAngle(southbasePiece1, 0.0F, -2.1399481296539307F, 0.0F);
        this.leftbasePiece2 = new ModelRenderer(this, 0, 15);
        this.leftbasePiece2.setRotationPoint(-3.0F, 12.699999809265137F, 5.599999904632568F);
        this.leftbasePiece2.addBox(-1.5F, 0.0F, -0.5F, 4, 12, 1, 0.0F);
        this.setRotateAngle(leftbasePiece2, 0.0F, 2.5953044891357426F, 0.0F);
        this.Bottom_Square = new ModelRenderer(this, -4, 0);
        this.Bottom_Square.setRotationPoint(-8.0F, 23.700000762939453F, -8.0F);
        this.Bottom_Square.addBox(0.0F, 0.0F, 0.0F, 16, 0, 16, 0.0F);
        this.southbasePiece2 = new ModelRenderer(this, 0, 15);
        this.southbasePiece2.setRotationPoint(2.9000000953674316F, 12.699999809265137F, 5.300000190734863F);
        this.southbasePiece2.addBox(-2.0F, 0.0F, -0.5F, 4, 12, 1, 0.0F);
        this.setRotateAngle(southbasePiece2, 0.0F, -2.5953044891357426F, 0.0F);
        this.basePiece = new ModelRenderer(this, 0, 0);
        this.basePiece.setRotationPoint(0.0F, 13.0F, -6.5F);
        this.basePiece.addBox(-1.5F, 0.0F, -0.5F, 3, 11, 1, 0.0F);
        this.rightbasePiece = new ModelRenderer(this, 0, 0);
        this.rightbasePiece.setRotationPoint(6.5F, 13.0F, 0.0F);
        this.rightbasePiece.addBox(-1.5F, 0.0F, -0.5F, 3, 11, 1, 0.0F);
        this.setRotateAngle(rightbasePiece, 0.0F, -1.5707963705062866F, 0.0F);
        this.northbasePiece2 = new ModelRenderer(this, 0, 15);
        this.northbasePiece2.setRotationPoint(-3.799999952316284F, 12.699999809265137F, -5.099999904632568F);
        this.northbasePiece2.addBox(-1.5F, 0.0F, -0.5F, 4, 12, 1, 0.0F);
        this.setRotateAngle(northbasePiece2, 0.0F, 0.5462880730628967F, 0.0F);
        this.rightbasePiece1 = new ModelRenderer(this, 0, 0);
        this.rightbasePiece1.setRotationPoint(5.5F, 12.399999618530273F, -2.9000000953674316F);
        this.rightbasePiece1.addBox(-1.5F, 0.0F, -0.5F, 3, 12, 1, 0.0F);
        this.setRotateAngle(rightbasePiece1, 0.0F, -1.001644492149353F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.counterbasePiece.render(f5);
        this.leftbasePiece1.render(f5);
        this.rightbasePiece2.render(f5);
        //this.WaterRender.render(f5);
        this.leftbasePiece.render(f5);
        this.northbasePiece1.render(f5);
        this.southbasePiece1.render(f5);
        this.leftbasePiece2.render(f5);
        this.Bottom_Square.render(f5);
        this.southbasePiece2.render(f5);
        this.basePiece.render(f5);
        this.rightbasePiece.render(f5);
        this.northbasePiece2.render(f5);
        this.rightbasePiece1.render(f5);
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
