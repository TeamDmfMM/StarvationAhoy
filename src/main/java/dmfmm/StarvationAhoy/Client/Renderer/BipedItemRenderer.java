package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class BipedItemRenderer implements IItemRenderer
{
    protected ModelBase model;
    protected String texturelocation;

    public BipedItemRenderer(ModelBase model, String texture){
        this.model = model;
        this.texturelocation = texture;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
            case ENTITY:
            case EQUIPPED:
            case INVENTORY:
            case EQUIPPED_FIRST_PERSON:
                return true;
            default:
                return false;
        }
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack var2, Object ... data)
    {
        GL11.glPushMatrix();
        switch(type){
            case ENTITY://DONE
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0F, -1F, 0F);
                break;
            case EQUIPPED://DONE
                GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -1.3F, -0.4F);
                GL11.glScalef(1.2F, 1.2F, 1.2F);
                break;
            case EQUIPPED_FIRST_PERSON://DONE
                GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -1.1F, -0.4F);
                GL11.glScalef(1F, 1F, 1F);
                break;
            case INVENTORY://DONE
                //GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                GL11.glRotatef(-90F, -30.0F, -90.0F, 95.0F);
                GL11.glScalef(12F, 12F, 12F);
                GL11.glTranslatef(0.1F, 0.1F, -0.3F);
                GL11.glEnable(GL11.GL_BLEND);
                break;
            default://DONE
                break;
        }

        ResourceLocation textures = (new ResourceLocation(this.texturelocation));
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

        this.model.isChild = false;
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}

