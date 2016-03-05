package dmfmm.StarvationAhoy.Client.Gui;


import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.Map;

public class InfoBookGUI extends GuiScreen{

    int bookHeight = 180;
    int bookWidth = 146;
    private BookPageRegistry registry = new BookPageRegistry();





    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawBookBackground();
        this.interpretBookPages();
    }

    private void interpretBookPages() {
        Map<String, ItemStack> pages = registry.getBookTabs();
        int left = width / 2 - bookWidth / 2;
        int top = height / 2 - bookHeight / 2;

        for(int i=0; i<pages.size(); i++){
            String page = (String) pages.keySet().toArray()[i];
            ItemStack stack = pages.get(page);

            if(stack != null){
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5f, 0.5f,0.5f);
                this.itemRender.renderItemIntoGUI(stack, left, top+3);
                GlStateManager.popMatrix();
                this.fontRendererObj.drawString(StatCollector.translateToLocal("infobook.title."+page), left + 20, top+6, 000000);
                top += 14;
            }
        }




    }

    private void drawBookBackground() {
        int left = width / 2 - bookWidth / 2;
        int top = height / 2 - bookHeight / 2;


        GlStateManager.pushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("starvationahoy", "textures/gui/InfobookMain.png"));
        this.drawTexturedModalRect(left, top, 0, 0, bookWidth, bookHeight);
        GlStateManager.popMatrix();
    }

}
