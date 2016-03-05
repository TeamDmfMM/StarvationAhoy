package dmfmm.StarvationAhoy.Client.Gui;


import dmfmm.StarvationAhoy.Core.util.DualObjectLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
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
        Map<String, DualObjectLink<ItemStack, Boolean>> pages = registry.getBookTabs();
        int left = width / 2 - bookWidth / 2;
        int top = (height / 2 - bookHeight / 2) + 2;

        for(int i=0; i<pages.size(); i++){
            String page = (String) pages.keySet().toArray()[i];
            DualObjectLink<ItemStack, Boolean> obj = pages.get(page);
            boolean displayPage = obj.getB();
            ItemStack stack = obj.getA();

            this.fontRendererObj.setUnicodeFlag(true);
            if(displayPage) {
                if (stack != null) {
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(0.5f, 0.5f, 0.5f);
                    this.itemRender.renderItemIntoGUI(stack, left, top + 3);
                    GlStateManager.popMatrix();
                    this.fontRendererObj.drawString(StatCollector.translateToLocal("infobook.title." + page), left + 25, top + 6, 000000);
                    top += 9;
                } else {
                    int size = this.fontRendererObj.getStringWidth(StatCollector.translateToLocal("infobook.header." + page));
                    int lefts = width / 2 - size / 2;
                    this.fontRendererObj.drawString("§n§l"+ StatCollector.translateToLocal("infobook.header." + page), lefts -6, top + 6, 000000);
                    top += 11;
                }
            }
            this.fontRendererObj.setUnicodeFlag(false);
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
