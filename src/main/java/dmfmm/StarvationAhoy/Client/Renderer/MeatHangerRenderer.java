package dmfmm.StarvationAhoy.Client.Renderer;

import dmfmm.StarvationAhoy.Meat.MeatRegistry;
import dmfmm.StarvationAhoy.api.Meat.ISAModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;


public class MeatHangerRenderer extends TileEntitySpecialRenderer {
    
    //The model of your block
    private final MeatHangerModel model;
    
    public MeatHangerRenderer() {
            this.model = new MeatHangerModel();
    }

    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int ticks) {
    //The PushMatrix tells the renderer to "start" doing something.
            GL11.glPushMatrix();
    //This is setting the initial location.
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            int i = te.getBlockMetadata();
    		
    		short short1 = 0;
    		
    		if (i == 2)
            {
                short1 = 360;
            }

            if (i == 3)
            {
                short1 = 180;
            }

            if (i == 4)
            {
                short1 = 90; //-90
            }

            if (i == 5)
            {
                short1 = -90; //90
            }
            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
   //Use in 1.6.2  this
            ResourceLocation textures = (new ResourceLocation("starvationahoy:textures/blocks/MeatHook1.png")); 
    //the ':' is very important
    //binding the textures
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);

    //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    //A reference to your Model file. Again, very important.
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            int meatType = ((MeatHangerTileEntity)te).getMeatType();
            int meatState = ((MeatHangerTileEntity)te).getMeatState();
            switch(meatType){
            case 0:
           break;
            case 1:
            	//cow
            	ModelCowSA cow = new ModelCowSA();
            	cow.isChild = false;
            	cow.leg1.rotateAngleX = 77;
            	cow.leg2.rotateAngleX = 77;
            	cow.leg1.rotateAngleY = 0.2F;
            	cow.leg2.rotateAngleY = -0.2F;
            	cow.leg3.rotateAngleX = -77F;
            	cow.leg4.rotateAngleX = -77F;
            	GL11.glRotatef(90F, 1, 0, 0);
            	GL11.glTranslatef(0, -0.65F, -1.9F);

            	ResourceLocation cowT =  getTexture(meatType, meatState);
            	Minecraft.getMinecraft().renderEngine.bindTexture(cowT);
            	cow.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            	break;
            case 2:
            	//pig
            	ModelPigSA pig = new ModelPigSA();
            	pig.isChild = false;
            	pig.leg1.rotateAngleX = 77;
            	pig.leg2.rotateAngleX = 77;
            	pig.leg1.rotateAngleY = 0.2F;
            	pig.leg2.rotateAngleY = -0.2F;
            	pig.leg3.rotateAngleX = -77F;
            	pig.leg4.rotateAngleX = -77F;
            	GL11.glRotatef(90F, 1, 0, 0);
            	GL11.glTranslatef(0, -1F, -1.6F);
            	getTexture(meatType, meatState);
            	ResourceLocation pigT = getTexture(meatType, meatState);
            	Minecraft.getMinecraft().renderEngine.bindTexture(pigT);
            	pig.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            	break;
           /* case 3:
            	//chicken
            	ModelChickenSA chick = new ModelChickenSA();
            	chick.isChild = false;
            	chick.rightWing.offsetX = 0.1F;
            	chick.rightWing.rotateAngleZ = 361.2F;
            	chick.leftWing.offsetX = -0.1F;
            	chick.leftWing.rotateAngleZ = 361.2F;
            	GL11.glRotatef(180F, 1, 0, 0);
            	GL11.glRotatef(180F, 0, 1, 0);
            	GL11.glTranslatef(0, -2.3F, 0.2F);
            	GL11.glDisable(GL11.GL_CULL_FACE);

            	ResourceLocation chickT =getTexture(meatType, meatState);
            	Minecraft.getMinecraft().renderEngine.bindTexture(chickT);
            	chick.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            	break;*/
            case 4:
            	//Sheep
				if(meatState == 0){
					ModelSheepSA sheepy = new ModelSheepSA();
					sheepy.isChild = false;
					sheepy.leg1s.rotateAngleX = 77;
					sheepy.leg2s.rotateAngleX = 77;
					sheepy.leg1s.rotateAngleY = 0.2F;
					sheepy.leg2s.rotateAngleY = -0.2F;
					sheepy.leg3s.rotateAngleX = -77F;
					sheepy.leg4s.rotateAngleX = -77F;
					sheepy.leg1.rotateAngleX = 77;
					sheepy.leg2.rotateAngleX = 77;
					sheepy.leg1.rotateAngleY = 0.2F;
					sheepy.leg2.rotateAngleY = -0.2F;
					sheepy.leg3.rotateAngleX = -77F;
					sheepy.leg4.rotateAngleX = -77F;

					//Rotation Block
					GL11.glRotatef(90F, 1, 0, 0);
					GL11.glTranslated(0, -0.7, -1.9);


					//Draw & Finish
					Minecraft.getMinecraft().renderEngine.bindTexture(getTexture(ModuleMeat.MEATTYPE_SHEEP, meatState));
					sheepy.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
					break;
				}else{
					ModelSheepSA.ModelSheepSA2 sheepyz = new ModelSheepSA.ModelSheepSA2();
					sheepyz.isChild = false;
					sheepyz.leg1.rotateAngleX = 77;
					sheepyz.leg2.rotateAngleX = 77;
					sheepyz.leg1.rotateAngleY = 0.2F;
					sheepyz.leg2.rotateAngleY = -0.2F;
					sheepyz.leg3.rotateAngleX = -77F;
					sheepyz.leg4.rotateAngleX = -77F;

					//Rotation Block
					GL11.glRotatef(90F, 1, 0, 0);
					GL11.glTranslated(0, -0.7, -1.9);

					Minecraft.getMinecraft().renderEngine.bindTexture(getTexture(ModuleMeat.MEATTYPE_SHEEP, meatState));
					sheepyz.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
					break;
				}
			case 5:
				//Rabbit
				ModelRabbitSA rabbit = (ModelRabbitSA) ModuleMeat.registry.getModel(ModuleMeat.MEATTYPE_RABBIT);
				rabbit.isChild = false;

				GL11.glRotatef(110F, 1, 0, 0);
				GL11.glTranslated(0, -1.7, -1.6);

				ResourceLocation rabbitT = getTexture(meatType, meatState);
				Minecraft.getMinecraft().renderEngine.bindTexture(rabbitT);
				rabbit.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
				break;
			default:
					if (meatType > 0){
						ModelBase toDraw = ModuleMeat.registry.getModel(meatType);
						if(toDraw instanceof ISAModel){
							ISAModel moddelo = (ISAModel)toDraw;
							moddelo.modelTransformations();
							moddelo.glTransformations();
						}else {
							GL11.glRotatef(180F, 1, 0, 0);
							GL11.glRotatef(180F, 0, 1, 0);
							GL11.glTranslatef(0, -2.3F, 0.2F);
							GL11.glDisable(GL11.GL_CULL_FACE);
						}
						ResourceLocation animalTexture =getTexture(meatType, meatState);
						Minecraft.getMinecraft().renderEngine.bindTexture(animalTexture);
						toDraw.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
					}
					break;
            
            }
    //Tell it to stop rendering for both the PushMatrix's
            GL11.glPopMatrix();
            GL11.glPopMatrix();
    }


	private ResourceLocation getTexture(int Animal, int state) {
		switch (state) {
			case 0:

				return ModuleMeat.registry.getMeatTypeForId(Animal).textures.dead;

			case 1:

				return ModuleMeat.registry.getMeatTypeForId(Animal).textures.skinned;

			case 2:

				return ModuleMeat.registry.getMeatTypeForId(Animal).textures.rotten;
		}
		return null;
	}
}