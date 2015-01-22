package dmfmm.StarvationAhoy.Client.Renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

//import cpw.mods.fml.client.TextureHelper;

public class PigItemRenderer implements IItemRenderer
{
        protected DeadPigModel model = new DeadPigModel();

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
					GL11.glTranslatef(0F, -1.5F, 0F);					
					break;
				case EQUIPPED://DONE
					GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(0.0F, -2.1F, -0.4F);
					GL11.glScalef(1.5F, 1.5F, 1.5F);
					break;
				case EQUIPPED_FIRST_PERSON://DONE
					GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(0.6F, -1.1F, -0.2F);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					break;
				case INVENTORY://DONE
					GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
					GL11.glRotatef(-90F, -30.0F, -90.0F, 90.0F);
					GL11.glScalef(19F, 19F, 19F);
					GL11.glTranslatef(0.0F, -0.7F, -0.1F);
					
					break;
				default://DONE
					break;
                }
                	
                ResourceLocation textures = (new ResourceLocation("starvationahoy:textures/entity/PigItem.png")); 
                Minecraft.getMinecraft().renderEngine.bindTexture(textures);


                this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
        }
}

