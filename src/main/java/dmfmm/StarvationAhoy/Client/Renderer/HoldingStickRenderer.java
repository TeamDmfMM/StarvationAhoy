package dmfmm.StarvationAhoy.Client.Renderer;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import dmfmm.StarvationAhoy.Meat.MeatRegistry;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
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
    
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
    //The PushMatrix tells the renderer to "start" doing something.
        desync++;
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
            this.modelMulti.Bar.rotateAngleZ = -desync;
            this.modelMulti.DownHandle.rotateAngleZ = -desync;
            this.modelMulti.shape10.rotateAngleZ = -desync;
            this.modelMulti.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.hasKey("RoastingItem")) {
                ItemStack is = ItemStack.loadItemStackFromNBT(((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getCompoundTag("RoastingItem"));
                MeatRegistry.MeatReturn r = ModuleMeat.registry.isSkinnedItem(is);
                MeatRegistry.MeatReturn r2 = ModuleMeat.registry.isCookedItem(is);
                if (r.value || r2.value) {
                    SALog.error("Spam 1");

                    int meatState = 1;
                    int meatType;
                    if (r.value) meatType = r.meatID;
                    else meatType = r2.meatID;
                    float xoffset = 0.0255f;
                    float zoffset = 0;
                    float yoffset = 0;
                    if (short1 == 0){
                        short1 = 90;
                    }
                    else {
                        short1 = 0;
                    }
                    if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.hasKey("CookBurn")){
                        if (((TileEntityMultiBlock) te).multiBlockStructure.sharedData.getInteger("CookBurn") == 1){
                            SALog.error("BL\nBL\n");
                            GL11.glColor3f(1.0f,1.0f,1.0f);
                        }
                        else if (r2.value){
                            if (r2.meatID == 1 || r2.meatID == 2){
                                GL11.glColor3f(0.4f,0.3f,0.3f);
                            }
                            else {
                                SALog.error("Chicky whites");
                                GL11.glColor3f(1.0f,0.6f,0.6f);
                            }
                        }


                    }
                    else if (r2.value){
                        if (r2.meatID == 1 || r2.meatID == 2){
                            GL11.glColor3f(0.4f,0.3f,0.3f);
                        }
                        else {
                            SALog.error("Chicky whites");
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

                    switch (meatType) {
                        case 0:
                            break;
                        case 1:
                            //cow
                            ModelCowSA cow = new ModelCowSA();
                            cow.isChild = false;

                            zoffset = 3.42f;
                            yoffset = 1.871f;

                           // GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(0F, 1, 0, 0);
                            GL11.glTranslatef(xoffset + 0, yoffset + -0.95F, zoffset + -1.9F);

                            ResourceLocation cowT = getTexture(meatType, meatState);
                            Minecraft.getMinecraft().renderEngine.bindTexture(cowT);
                            /*for (Object modelBox : cow.boxList) {
                                ModelRenderer box = (ModelRenderer) modelBox;

                                box.rotateAngleZ = 7f;
                                float cur = box.rotationPointZ;
                                float cury = box.rotationPointY;
                                float ofset = (float) Math.cos(box.rotateAngleZ);
                                float ofsety = (float) Math.sin(box.rotateAngleZ);
                                box.rotationPointY = (ofsety * cury);
                                box.rotationPointZ = (cur * ofset);
                            }*/
                            float rangle = desync    ;
                            float radians = (float) Math.toRadians(rangle);
                            GL11.glTranslatef(0, -0.66f,0);

                            float ztrans = (float) (Math.sin(radians));
                            float ytrans = (float) (Math.cos(radians));
                            GL11.glTranslatef(ztrans, -ytrans, 0);
                            GL11.glRotatef(rangle, 0, 0, 1);
                            GL11.glTranslatef(0, 0.66f,0);




                            //GL11.glTranslatef(0, 0, 0);
                            cow.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            break;
                        case 2:
                            //pig
                            ModelPigSA pig = new ModelPigSA();
                            pig.isChild = false;
                            zoffset = 3.42f;
                            yoffset = 1.42f;
                            GL11.glTranslatef(xoffset+0, yoffset-1F, zoffset+-1.6F);
                            rangle = desync    ;
                            radians = (float) Math.toRadians(rangle);
                            GL11.glTranslatef(0, -0.2f,0);

                            ztrans = (float) (Math.sin(radians));
                            ytrans = (float) (Math.cos(radians));
                            GL11.glTranslatef(ztrans, -ytrans, 0);
                            GL11.glRotatef(rangle, 0, 0, 1);
                            GL11.glTranslatef(0, 0.2f,0);

                          //  GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(0F, 1, 0, 0);

                            getTexture(meatType, meatState);
                            ResourceLocation pigT = getTexture(meatType, meatState);
                            Minecraft.getMinecraft().renderEngine.bindTexture(pigT);
                            pig.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            break;
                        case 3:
                            //chicken
                            SALog.error("Spam 3");
                            ModelChickenSA chick = new ModelChickenSA();
                            chick.isChild = false;

                            //GL11.glRotatef(180F, 1, 0, 0);
                            //GL11.glRotatef(180F, 0, 1, 0);
                            zoffset = 0.62f;
                            yoffset = 3.29f;
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
                            break;
                        //case 4:
                        //Sheep
                        default:
                            if (meatType > 0) {
                                ModelBase toDraw = ModuleMeat.registry.getModel(meatType);
                                GL11.glRotatef(180F, 1, 0, 0);
                                GL11.glRotatef(180F, 0, 1, 0);
                                GL11.glTranslatef(0, -2.3F, 0.2F);
                                GL11.glDisable(GL11.GL_CULL_FACE);
                                toDraw.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                            }
                            break;

                    }

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
