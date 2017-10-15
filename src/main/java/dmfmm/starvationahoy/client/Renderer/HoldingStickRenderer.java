package dmfmm.starvationahoy.client.Renderer;

import dmfmm.starvationahoy.meat.block.multiblock.TileEntityMultiBlock;
import dmfmm.starvationahoy.meat.MeatRegistry;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.api.Meat.ISpitRoastRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class HoldingStickRenderer extends TileEntitySpecialRenderer{
    
    //The model of your block
    private final HoldingStick model;
    private final ModelMeatRoaster modelMulti;

    int desync = 0;
    
    public HoldingStickRenderer() {
        this.model = new HoldingStick();
        modelMulti = new ModelMeatRoaster();
    }

    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int ticks) {



        if (((TileEntityMultiBlock) te).multiBlockStructure != null){
            //Desync is the Var for the spinning of the Food
            desync = ((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getInteger("CookTime");

            if (((TileEntityMultiBlock) te).multiBlockStructure.bPos == 3){
                return;
            }
        }
        //GlPush to start the Draw
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        //Directional computation
        float short1 = 90;
        if (((TileEntityMultiBlock) te).multiBlockStructure != null){
            if (((TileEntityMultiBlock) te).multiBlockStructure.bPos == 0 && ((TileEntityMultiBlock) te).multiBlockStructure.orient == 1){
               short1 = 0;
            }
        }

        GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);

        //Wooden Texture & Bind
        ResourceLocation textures;
        if (((TileEntityMultiBlock) te).multiBlockStructure == null) {
            textures = (new ResourceLocation("starvationahoy:textures/blocks/holding_stick.png"));
        }
        else {
           textures = (new ResourceLocation("starvationahoy:textures/blocks/holdingstick_texture.png"));
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

    //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


        if(((TileEntityMultiBlock) te).multiBlockStructure != null){
        this.modelMulti.Bar.rotateAngleZ =(float) Math.toRadians(desync);
        this.modelMulti.DownHandle.rotateAngleZ = (float) Math.toRadians(desync);
        this.modelMulti.shape10.rotateAngleZ = (float) Math.toRadians(desync);
        this.modelMulti.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }else{
            int i = te.getBlockMetadata();

            short short1z = 0;

            if (i == 2)
            {
                short1z = -90;
            }

            if (i == 3)
            {
                short1z = 90;
            }

            if (i == 4)
            {
                short1z = 180; //-90
            }

            if (i == 5)
            {
                short1z = 360; //90
            }
            GL11.glRotatef((float)short1z, 0.0F, 1.0F, 0.0F);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }

        //Basically the meat render code....
        if (((TileEntityMultiBlock) te).multiBlockStructure != null) {
            if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.hasKey("RoastingItem")) {
                ItemStack is = new ItemStack(((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getCompoundTag("RoastingItem"));
                MeatRegistry.MeatReturn r = ModuleMeat.registry.isSkinnedItem(is);
                MeatRegistry.MeatReturn r2 = ModuleMeat.registry.isCookedItem(is);
                MeatRegistry.MeatReturn r3 = ModuleMeat.registry.isCookedItem(new ItemStack(((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getCompoundTag("CookedItem")));
                if (r.value || r2.value || is.getItem() == Items.BONE) {
                    //SALog.error("Spam 1");

                    int meatState = 1;
                    int meatType;
                    if (r.value) meatType = r.meatID;
                    else meatType = r2.meatID;

                    if (r3.value == true && is.getItem() == Items.BONE) {
                        meatType = r3.meatID;
                    }
                    float xoffset = 0.0255f;
                    float zoffset = 0;
                    float yoffset = 0;
                    if (short1 == 0){
                        short1 = 90;
                    }
                    else {
                        short1 = 0;
                    }
                    GlStateManager.pushMatrix();
                    //Color of the displayed model stuff
                    if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.hasKey("CookBurn")){
                        if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getInteger("CookBurn") == 1){
                            //SALog.error("BL\nBL\n");
                            GlStateManager.color(0.09f,0.09f,0.09f);
                            //GL11.glColor3f();
                        } else if (r2.value){
                            if (r2.meatID == 1 || r2.meatID == 2){
                                GlStateManager.color(0.4f,0.3f,0.3f);
                                //GL11.glColor3f();
                            }
                            else {
                                //SALog.error("Chicky whites");
                                GL11.glColor3f(1.0f,0.6f,0.6f);
                            }
                        }
                    } else if (r2.value){
                        if (r2.meatID == 1 || r2.meatID == 2){
                            GL11.glColor3f(0.4f,0.3f,0.3f);
                        }
                        else {
                            //SALog.error("Chicky whites");
                            GL11.glColor3f(1.0f,0.6f,0.6f);
                        }
                    }
                    if (((TileEntityMultiBlock) te).multiBlockStructure.orient == 0){
                        //xoffset = 1.3f;
                        zoffset = 0f;
                        yoffset = 1.67f;
                    }
                    else {
                        yoffset = 1.67f;
                        zoffset = 0f;
                    }
                    //Actual Animal Renders
                    GL11.glPushMatrix();
                    switch (meatType) {
                        case 0:
                            break;
                        case 4://Rabbit Type
                            ModelBase rabbit = ModuleMeat.registry.getModel(meatType);
                            rabbit.isChild =false;


                            GlStateManager.translate(0, 0.5, 1);

                            float radian = (float) Math.toRadians(desync);
                            GlStateManager.translate(0, -0.2f, 0);
                            GlStateManager.translate((float) (Math.sin(radian)), ((float) -(Math.cos(radian))), 0);
                            GlStateManager.rotate(desync, 0, 0, 1);
                            GlStateManager.translate(0, 0.1f, 0);
                            GlStateManager.rotate(22F, 1, 0, 0);

                            this.bindTexture(getTexture(meatType, meatState));
                            rabbit.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            break;
                        default:
                            // ================================= Other Start ================================
                            if (meatType > 0) {
                                float rotation = -0.2f;
                                GL11.glDisable(GL11.GL_CULL_FACE);
                                ModelBase toDraw = ModuleMeat.registry.getModel(meatType);
                                toDraw.isChild =false;

                                if (toDraw instanceof ISpitRoastRender){

                                    ISpitRoastRender render = (ISpitRoastRender) toDraw;
                                    if(render.getTranslations().length == 6) {
                                        zoffset = render.getTranslations()[3];
                                        yoffset = render.getTranslations()[4];
                                        rotation = render.getTranslations()[5];
                                    }
                                    xoffset += render.getTranslations()[0];
                                    yoffset += render.getTranslations()[1];
                                    zoffset += render.getTranslations()[2];
                                    toDraw = render.updateExistingModel(toDraw);
                                    GL11.glTranslatef(xoffset, yoffset, zoffset);
                                }else{
                                    GL11.glRotatef(180F, 1, 0, 0);
                                    GL11.glRotatef(180F, 0, 1, 0);
                                    GL11.glTranslatef(0, -2.3F, 0.2F);
                                    GL11.glTranslatef(xoffset + 0, yoffset - 1F, zoffset + -1.6F);
                                }


                                float rangle = desync;
                                float radians = (float) Math.toRadians(rangle);
                                GL11.glTranslatef(0, rotation,0);

                                float ztrans = (float) (Math.sin(radians));
                                float ytrans = (float) (Math.cos(radians));
                                GL11.glTranslatef(ztrans, -ytrans, 0);
                                GL11.glRotatef(rangle, 0, 0, 1);
                                GL11.glTranslatef(0, Math.abs(rotation),0);
                                GL11.glRotatef(0F, 1, 0, 0);

                                this.bindTexture(getTexture(meatType, meatState));
                                toDraw.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            }
                            // ================================ End ============================================
                            break;

                    }
                    GL11.glPopMatrix();
                    GlStateManager.popMatrix();

                }
            }
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
