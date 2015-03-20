package dmfmm.StarvationAhoy.Client.Renderer;

import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;

public class HoldingStickRenderer extends TileEntitySpecialRenderer{
    
    //The model of your block
    private final HoldingStick model;
    private final ModelMeatRoaster modelMulti;
    
    public HoldingStickRenderer() {
            this.model = new HoldingStick();
        modelMulti = new ModelMeatRoaster();
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
        if (((TileEntityMultiBlock) te).multiBlockStructure != null){
            if (((TileEntityMultiBlock) te).multiBlockStructure.bPos == 3){
                return;
            }
        }
            GL11.glPushMatrix();
    //This is setting the initial location.
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            int i = te.getBlockMetadata();
        float short1 = 90;
        if (((TileEntityMultiBlock) te).multiBlockStructure != null){
            if (((TileEntityMultiBlock) te).multiBlockStructure.bPos == 0 && ((TileEntityMultiBlock) te).multiBlockStructure.orient == 1){
               short1 = 0;
            }
        }

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
   //Use in 1.6.2  this
        ResourceLocation textures;
        if (((TileEntityMultiBlock) te).multiBlockStructure == null) {
            textures = (new ResourceLocation("starvationahoy:textures/blocks/HoldingStick.png"));
        }
        else {
           textures = (new ResourceLocation("starvationahoy:textures/blocks/HoldingStick-texture.png"));
        }

    //the ':' is very important
    //binding the textures
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);

    //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    //A reference to your Model file. Again, very important.
        if (((TileEntityMultiBlock) te).multiBlockStructure == null) {
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }
        else {
            this.modelMulti.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }
    //Tell it to stop rendering for both the PushMatrix's
            GL11.glPopMatrix();
            GL11.glPopMatrix();
    }
}