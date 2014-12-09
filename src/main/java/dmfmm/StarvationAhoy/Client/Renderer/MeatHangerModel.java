package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MeatHangerModel extends ModelBase
{
	  //fields
	    ModelRenderer CrossBar;
	    ModelRenderer WallPlateR;
	    ModelRenderer WallPlateL;
	    ModelRenderer TopHook;
	    ModelRenderer MiddleHook;
	    ModelRenderer BottomHook;
	    ModelRenderer Dot;
	    ModelRenderer LongDot;
	  
	  public MeatHangerModel()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      CrossBar = new ModelRenderer(this, 0, 0);
	      CrossBar.addBox(-8F, 0F, 0F, 14, 1, 1);
	      CrossBar.setRotationPoint(1F, 10F, 4F);
	      CrossBar.setTextureSize(64, 32);
	      CrossBar.mirror = true;
	      setRotation(CrossBar, 0F, 0F, 0F);
	      WallPlateR = new ModelRenderer(this, 34, 0);
	      WallPlateR.addBox(0F, 0F, 0F, 1, 3, 5);
	      WallPlateR.setRotationPoint(7F, 9F, 3F);
	      WallPlateR.setTextureSize(64, 32);
	      WallPlateR.mirror = true;
	      setRotation(WallPlateR, 0F, 0F, 0F);
	      WallPlateL = new ModelRenderer(this, 34, 0);
	      WallPlateL.addBox(0F, 0F, 0F, 1, 3, 5);
	      WallPlateL.setRotationPoint(-8F, 9F, 3F);
	      WallPlateL.setTextureSize(64, 32);
	      WallPlateL.mirror = true;
	      setRotation(WallPlateL, 0F, 0F, 0F);
	      TopHook = new ModelRenderer(this, 0, 11);
	      TopHook.addBox(0F, 0F, 0F, 1, 1, 3);
	      TopHook.setRotationPoint(0F, 9F, 3F);
	      TopHook.setTextureSize(64, 32);
	      TopHook.mirror = true;
	      setRotation(TopHook, 0F, 0F, 0F);
	      MiddleHook = new ModelRenderer(this, 0, 15);
	      MiddleHook.addBox(0F, 0F, 0F, 1, 4, 1);
	      MiddleHook.setRotationPoint(0F, 10F, 3F);
	      MiddleHook.setTextureSize(64, 32);
	      MiddleHook.mirror = true;
	      setRotation(MiddleHook, 0F, 0F, 0F);
	      BottomHook = new ModelRenderer(this, 0, 20);
	      BottomHook.addBox(0F, 0F, 0F, 1, 1, 3);
	      BottomHook.setRotationPoint(0F, 14F, 1F);
	      BottomHook.setTextureSize(64, 32);
	      BottomHook.mirror = true;
	      setRotation(BottomHook, 0F, 0F, 0F);
	      Dot = new ModelRenderer(this, 0, 24);
	      Dot.addBox(0F, 0F, 0F, 1, 1, 1);
	      Dot.setRotationPoint(0F, 13F, 1F);
	      Dot.setTextureSize(64, 32);
	      Dot.mirror = true;
	      setRotation(Dot, 0F, 0F, 0F);
	      LongDot = new ModelRenderer(this, 0, 8);
	      LongDot.addBox(0F, 0F, 0F, 1, 2, 1);
	      LongDot.setRotationPoint(0F, 10F, 5F);
	      LongDot.setTextureSize(64, 32);
	      LongDot.mirror = true;
	      setRotation(LongDot, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    CrossBar.render(f5);
	    WallPlateR.render(f5);
	    WallPlateL.render(f5);
	    TopHook.render(f5);
	    MiddleHook.render(f5);
	    BottomHook.render(f5);
	    Dot.render(f5);
	    LongDot.render(f5);
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
