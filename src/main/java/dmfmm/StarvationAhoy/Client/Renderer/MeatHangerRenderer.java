package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;



public class MeatHangerRenderer extends TileEntitySpecialRenderer {
    
    //The model of your block
    private final MeatHangerModel model;
    
    public MeatHangerRenderer() {
            this.model = new MeatHangerModel();
    }
    
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
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
            default:
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
            	String resource = getTexture(meatType, meatState);
            	ResourceLocation cowT = new ResourceLocation(resource);
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
            	String resource2 = getTexture(meatType, meatState);
            	ResourceLocation pigT = new ResourceLocation(resource2);
            	Minecraft.getMinecraft().renderEngine.bindTexture(pigT);
            	pig.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            	break;
            case 3:
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
            	String resource3 = getTexture(meatType, meatState);
            	ResourceLocation chickT = new ResourceLocation(resource3);
            	Minecraft.getMinecraft().renderEngine.bindTexture(chickT);
            	chick.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            	break;
            //case 4:
            	//Sheep
            
            }
    //Tell it to stop rendering for both the PushMatrix's
            GL11.glPopMatrix();
            GL11.glPopMatrix();
    }

    //Set the lighting stuff, so it changes it's brightness properly.       
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        float brightness = block.getMixedBrightnessForBlock(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
}
    
    private String getTexture(int Animal, int state){
    	switch(Animal){
    	case 1:
    		if(state == 0){
    			return "minecraft:textures/entity/cow/cow.png";
    		} else if(state == 1){
    			return "starvationahoy:textures/entity/skinnedCow.png";
    		} else if(state == 2){
    			return "starvationahoy:textures/entity/rottenCow.png";
    		}
    	case 2:
    		if(state == 0){
    			return "minecraft:textures/entity/pig/pig.png";
    		} else if(state == 1){
    			return "starvationahoy:textures/entity/skinnedPig.png";
    		} else if(state == 2){
    			return "starvationahoy:textures/entity/rottenPig.png";
    		}
    	case 3:
    		if(state == 0){
    			return "minecraft:textures/entity/chicken.png";
    		} else if(state == 1){
    			return "starvationahoy:textures/entity/skinnedChicken.png";
    		} else if(state == 2){
    			return "starvationahoy:textures/entity/rottenChicken.png";
    		}
    	default:
    		return "ERRORED";
    	}
    }
}