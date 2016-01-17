package dmfmm.StarvationAhoy.Client.Renderer;

import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import dmfmm.StarvationAhoy.Meat.MeatRegistry;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.Meat.ISpitRoastRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;

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
            textures = (new ResourceLocation("starvationahoy:textures/blocks/HoldingStick.png"));
        }
        else {
           textures = (new ResourceLocation("starvationahoy:textures/blocks/HoldingStick-texture.png"));
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
                ItemStack is = ItemStack.loadItemStackFromNBT(((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getCompoundTag("RoastingItem"));
                MeatRegistry.MeatReturn r = ModuleMeat.registry.isSkinnedItem(is);
                MeatRegistry.MeatReturn r2 = ModuleMeat.registry.isCookedItem(is);
                MeatRegistry.MeatReturn r3 = ModuleMeat.registry.isCookedItem(ItemStack.loadItemStackFromNBT(((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getCompoundTag("CookedItem")));
                if (r.value || r2.value || is.getItem() == Items.bone) {
                    //SALog.error("Spam 1");

                    int meatState = 1;
                    int meatType;
                    if (r.value) meatType = r.meatID;
                    else meatType = r2.meatID;

                    if (r3.value == true && is.getItem() == Items.bone) {
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
                    //Color of the displayed model stuff
                    if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.hasKey("CookBurn")){
                        if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getInteger("CookBurn") == 1){
                            //SALog.error("BL\nBL\n");
                            GL11.glColor3f(0.09f,0.09f,0.09f);
                        }
                        else if (r2.value){
                            if (r2.meatID == 1 || r2.meatID == 2){
                                GL11.glColor3f(0.4f,0.3f,0.3f);
                            }
                            else {
                                //SALog.error("Chicky whites");
                                GL11.glColor3f(1.0f,0.6f,0.6f);
                            }
                        }


                    }
                    else if (r2.value){
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
                        case 1:
                            // ================================= COW Start ================================
                            ModelCowSA cow = new ModelCowSA();
                            cow.isChild = false;

                            zoffset = 3.42f;
                            yoffset = 1.871f;
                            // ================================= Rotate Start ================================
                           // GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(0F, 1, 0, 0);
                            GL11.glTranslatef(xoffset + 0, yoffset + -0.95F, zoffset + -1.9F);

                            ResourceLocation cowT = getTexture(meatType, meatState);
                            Minecraft.getMinecraft().renderEngine.bindTexture(cowT);
                            float rangle = desync;
                            float radians = (float) Math.toRadians(rangle);
                            GL11.glTranslatef(0, -0.66f,0);

                            float ztrans = (float) (Math.sin(radians));
                            float ytrans = (float) (Math.cos(radians));
                            GL11.glTranslatef(ztrans, -ytrans, 0);
                            GL11.glRotatef(rangle, 0, 0, 1);
                            GL11.glTranslatef(0, 0.66f,0);

                            //GL11.glTranslatef(0, 0, 0);
                            cow.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            // ================================ End ============================================
                            break;
                        case 2:
                            // ================================= Pig Start ================================
                            ModelPigSA pig = new ModelPigSA();
                            pig.isChild = false;
                            // ================================= Rotate Start ================================
                            zoffset = 3.42f;
                            yoffset = 1.42f;
                            GL11.glTranslatef(xoffset+0, yoffset-1F, zoffset+-1.6F);
                            rangle = desync;
                            radians = (float) Math.toRadians(rangle);
                            GL11.glTranslatef(0, -0.2f,0);

                            ztrans = (float) (Math.sin(radians));
                            ytrans = (float) (Math.cos(radians));
                            GL11.glTranslatef(ztrans, -ytrans, 0);
                            GL11.glRotatef(rangle, 0, 0, 1);
                            GL11.glTranslatef(0, 0.2f,0);
                            GL11.glRotatef(0F, 1, 0, 0);

                            getTexture(meatType, meatState);
                            ResourceLocation pigT = getTexture(meatType, meatState);
                            Minecraft.getMinecraft().renderEngine.bindTexture(pigT);
                            pig.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            // ================================ End ============================================
                            break;
                        case 3:
                            // ================================= Chicken Start ================================
                            ModelChickenSA chick = new ModelChickenSA();
                            chick.isChild = false;

                            // ================================= Rotate Start ================================
                            zoffset = 0.62f;
                            yoffset = 3.50f;
                            GL11.glTranslatef(xoffset+0, yoffset-3.00F, zoffset+0.9F);
                            GL11.glDisable(GL11.GL_CULL_FACE);
                            rangle = desync    ;
                            radians = (float) Math.toRadians(rangle);
                            GL11.glTranslatef(0, -0.2f,0);

                            ztrans = (float) (Math.sin(radians));
                            ytrans = (float) (Math.cos(radians));
                            GL11.glTranslatef(ztrans, -ytrans, 0);
                            GL11.glRotatef(rangle, 0, 0, 1);
                            GL11.glTranslatef(0, 0.22f,0);

                            ResourceLocation chickT = getTexture(meatType, meatState);
                            Minecraft.getMinecraft().renderEngine.bindTexture(chickT);
                            chick.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            // ================================ End ============================================
                            break;
                        //case 4:
                        //Sheep
                        default:
                            // ================================= Other Start ================================
                            if (meatType > 0) {
                                ModelBase toDraw = ModuleMeat.registry.getModel(meatType);
                                GL11.glRotatef(180F, 1, 0, 0);
                                GL11.glRotatef(180F, 0, 1, 0);
                                GL11.glTranslatef(0, -2.3F, 0.2F);
                                GL11.glDisable(GL11.GL_CULL_FACE);


                                boolean hasSpecialRender = false;

                                for (Class c : ModuleMeat.registry.getMeatTypeForId(meatType).entity.getInterfaces()){

                                    if (c == ISpitRoastRender.class){
                                        hasSpecialRender = true;
                                    }

                                }

                                if (hasSpecialRender){

                                    Class t = ModuleMeat.registry.getMeatTypeForId(meatType).entity;
                                    try {
                                        Method changeModel = t.getMethod("updateExistingModel", ModelBase.class);
                                        Method translation = t.getMethod("getTranslations");




                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    }

                                }



                                GL11.glTranslatef(xoffset+0, yoffset-1F, zoffset+-1.6F);
                                rangle = desync;
                                radians = (float) Math.toRadians(rangle);
                                GL11.glTranslatef(0, -0.2f,0);

                                ztrans = (float) (Math.sin(radians));
                                ytrans = (float) (Math.cos(radians));
                                GL11.glTranslatef(ztrans, -ytrans, 0);
                                GL11.glRotatef(rangle, 0, 0, 1);
                                GL11.glTranslatef(0, 0.2f,0);
                                GL11.glRotatef(0F, 1, 0, 0);


                                toDraw.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            }
                            // ================================ End ============================================
                            break;

                    }
                    GL11.glPopMatrix();

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
