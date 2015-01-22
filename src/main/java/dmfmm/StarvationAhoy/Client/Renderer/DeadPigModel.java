package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DeadPigModel extends ModelBase
{
	  //fields
	    ModelRenderer Head;
	    ModelRenderer Body;
	    ModelRenderer Leg1;
	    ModelRenderer Leg3;
	    ModelRenderer Leg2;
	    ModelRenderer Leg4;
	  
	  public DeadPigModel()
	  {
	    textureWidth = 64;
	    textureHeight = 64;
	    
	      Head = new ModelRenderer(this, 0, 0);
	      Head.addBox(0F, 0F, 0F, 4, 4, 4);
	      Head.setRotationPoint(-2F, 17F, -8F);
	      Head.setTextureSize(64, 64);
	      Head.mirror = true;
	      setRotation(Head, 0F, 0F, 0F);
	      Body = new ModelRenderer(this, 16, 0);
	      Body.addBox(0F, -1F, 0F, 6, 4, 7);
	      Body.setRotationPoint(-3F, 20F, -4F);
	      Body.setTextureSize(64, 64);
	      Body.mirror = true;
	      setRotation(Body, 0F, 0F, 0F);
	      Leg1 = new ModelRenderer(this, 0, 8);
	      Leg1.addBox(0F, 0F, 0F, 2, 2, 3);
	      Leg1.setRotationPoint(1F, 22F, 3F);
	      Leg1.setTextureSize(64, 64);
	      Leg1.mirror = true;
	      setRotation(Leg1, 0F, 0F, 0F);
	      Leg3 = new ModelRenderer(this, 0, 8);
	      Leg3.addBox(0F, 0F, 0F, 2, 2, 3);
	      Leg3.setRotationPoint(-3F, 22F, -7F);
	      Leg3.setTextureSize(64, 64);
	      Leg3.mirror = true;
	      setRotation(Leg3, 0F, 0F, 0F);
	      Leg2 = new ModelRenderer(this, 0, 8);
	      Leg2.addBox(0F, 0F, 0F, 2, 2, 3);
	      Leg2.setRotationPoint(-3F, 22F, 3F);
	      Leg2.setTextureSize(64, 64);
	      Leg2.mirror = true;
	      setRotation(Leg2, 0F, 0F, 0F);
	      Leg4 = new ModelRenderer(this, 0, 8);
	      Leg4.addBox(0F, 0F, 0F, 2, 2, 3);
	      Leg4.setRotationPoint(1F, 22F, -7F);
	      Leg4.setTextureSize(64, 64);
	      Leg4.mirror = true;
	      setRotation(Leg4, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    Head.render(f5);
	    Body.render(f5);
	    Leg1.render(f5);
	    Leg3.render(f5);
	    Leg2.render(f5);
	    Leg4.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  }

	}