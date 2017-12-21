package dmfmm.starvationahoy.client.Renderer;

import dmfmm.starvationahoy.crops.block.tilentity.TileEntityCropWasher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;

/**
* Created by DMF444 for Starvation Ahoy. All rights
* reserved. Code may be copied if credit is given to
* source. Any code derived code is under their respective
* licences.
*/
public class WashBarrelRenderer extends TileEntitySpecialRenderer {

    //The model of your block
    private final WashBarrelModel model;

    public WashBarrelRenderer() {
        this.model = new WashBarrelModel();
    }

    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
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
        GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
        //GL11.glEnable(GL11.GL_BLEND);
        //Use in 1.6.2  this

        GL11.glPushMatrix();







        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        ResourceLocation textures = (new ResourceLocation("starvationahoy:textures/blocks/wash_barrel.png"));
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            //TileEntityMonitorStorageFluid TE = (TileEntityMonitorStorageFluid) tileentity;
        TileEntityCropWasher tet = (TileEntityCropWasher) te;
            if (tet.getFluidAmount() > 0)
            {
                Float UPDOWN = Float.valueOf(Float.valueOf(tet.getFluidAmount()) / 1000) * 0.65f;

                //SALog.error(UPDOWN);
                //SALog.error(tet.getFluidAmount());

                TextureAtlasSprite water = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(FluidRegistry.WATER.getStill().toString());
                GL11.glRotatef(180F, 0.0F, 0.0F, -1.0F);
                GL11.glTranslated(-0.3F, -1.55 + UPDOWN, -0.3F );
                GL11.glRotated(90, 1, 0, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

                Tessellator cake = Tessellator.getInstance();
               // cake.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
                //cake.setBrightness(1000);

                //Main SQ
                cake.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                this.draw(cake, 0, 0.6, 0, water.getMinU(), water.getMaxV());
                this.draw(cake, 0.625, 0.6, 0, water.getMaxU(), water.getMaxV());
                this.draw(cake, 0.625, 0, 0, water.getMaxU(), water.getMinV());
                this.draw(cake, 0, 0, 0, water.getMinU(), water.getMinV());
                cake.draw();
                GL11.glTranslated(0.3F, 1.0, 0);

                GL11.glTranslated(-0.4F, -0.86, 0);
                //Sides 1
                cake.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                this.draw(cake, 0, 0.32, 0, water.getMinU(), water.getMaxV());
                this.draw(cake, 0.125, 0.37, 0, water.getMaxU(), water.getMaxV());
                this.draw(cake, 0.125, -0.1, 0, water.getMaxU(), water.getMinV());
                this.draw(cake, 0, 0, 0, water.getMinU(), water.getMinV());
                cake.draw();
                GL11.glTranslated(0.4F, 0.86, 0);

                GL11.glTranslated(0.31F, -0.86, 0);
                //Sides 2
                cake.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                this.draw(cake, 0, 0.31, 0, water.getMinU(), water.getMaxV());
                this.draw(cake, 0.124, 0.21, 0, water.getMaxU(), water.getMaxV());
                this.draw(cake, 0.124, 0.1, 0, water.getMaxU(), water.getMinV());
                this.draw(cake, 0, 0, 0, water.getMinU(), water.getMinV());
                cake.draw();
                GL11.glTranslated(-0.31F, -0.86, 0.0F);

                GL11.glTranslated(-0.09F, 0.599F, 0F); //0.599
                //Sides 3
                cake.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                this.draw(cake, -0.1, 0.126, 0, water.getMinU(), water.getMaxV());
                this.draw(cake, 0.30, 0.126, 0, water.getMaxU(), water.getMaxV());
                this.draw(cake, 0.24, 0, 0, water.getMaxU(), water.getMinV());
                this.draw(cake, 0, 0, 0, water.getMinU(), water.getMinV());
                cake.draw();
                GL11.glTranslated(0.31F, 0.86, 0.0F);

                GL11.glTranslated(-0.39F, -0.15F, 0F);
                //Sides 4
                cake.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                this.draw(cake, 0.17, 0.126, 0, water.getMinU(), water.getMaxV());
                this.draw(cake, 0.26, 0.126, 0, water.getMaxU(), water.getMaxV());
                this.draw(cake, 0.38, 0, 0, water.getMaxU(), water.getMinV());
                this.draw(cake, 0, 0, 0, water.getMinU(), water.getMinV());
                cake.draw();

                //cake.getBuffer().finishDrawing();
            }
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GL11.glPopMatrix();
        GL11.glPopAttrib();

        //


        //Tell it to stop rendering for both the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private static void draw(Tessellator tess, double x, double y, double z, float U, float V){
        tess.getBuffer().pos(x, y ,z);
        tess.getBuffer().tex(U, V);
        tess.getBuffer().color(1.0f, 1.0f, 1.0f, 1.0f);
        tess.getBuffer().endVertex();
    }

}