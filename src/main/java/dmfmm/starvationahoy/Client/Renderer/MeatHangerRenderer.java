package dmfmm.starvationahoy.Client.Renderer;

import dmfmm.starvationahoy.Meat.block.tileentity.MeatHangerData;
import dmfmm.starvationahoy.Meat.block.tileentity.MeatHangerTileEntity;
import dmfmm.starvationahoy.Meat.ModuleMeat;
import dmfmm.starvationahoy.api.Meat.ISAModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


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
    		
    		if (i == 2) {
                short1 = 360;
            }

            if (i == 3) {
                short1 = 180;
            }

            if (i == 4) {
                short1 = 90; //-90
            }

            if (i == 5) {
                short1 = -90; //90
            }
            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
   //Use in 1.6.2  this
            ResourceLocation textures = (new ResourceLocation("starvationahoy:textures/blocks/meathook1.png"));
    //the ':' is very important
    //binding the textures
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);

    //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    //A reference to your Model file. Again, very important.
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            int meatType = ((MeatHangerTileEntity)te).getMeatType();
            MeatHangerData.MeatStates meatState = ((MeatHangerTileEntity)te).getMeatState();
            switch(meatType){
            case 4:
            	//Sheep
				if(meatState == MeatHangerData.MeatStates.NORMAL){
					ModelSheepSA sheepy = new ModelSheepSA();
					sheepy.modelTransformations();
					sheepy.glTransformations();
					Minecraft.getMinecraft().renderEngine.bindTexture(getTexture(MeatHangerData.SHEEP.getMeatID(), meatState));
					sheepy.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
					break;
				}else{
					ModelSheepSA.ModelSheepSA2 sheepyz = new ModelSheepSA.ModelSheepSA2();
					sheepyz.modelTransformations();
					sheepyz.glTransformations();
					Minecraft.getMinecraft().renderEngine.bindTexture(getTexture(MeatHangerData.SHEEP.getMeatID(), meatState));
					sheepyz.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
					break;
				}
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
						ResourceLocation animalTexture = getTexture(meatType, meatState);
						Minecraft.getMinecraft().renderEngine.bindTexture(animalTexture);
						toDraw.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
					}
					break;
            
            }
    //Tell it to stop rendering for both the PushMatrix's
            GL11.glPopMatrix();
            GL11.glPopMatrix();
    }


	private ResourceLocation getTexture(int Animal, MeatHangerData.MeatStates state) {
		switch (state) {
			case NORMAL:

				return ModuleMeat.registry.getMeatTypeForId(Animal).textures.dead;

			case SKINNED:

				return ModuleMeat.registry.getMeatTypeForId(Animal).textures.skinned;

			case ROTTEN:

				return ModuleMeat.registry.getMeatTypeForId(Animal).textures.rotten;
			default:
				return TextureMap.LOCATION_MISSING_TEXTURE;
		}
	}
}