package dmfmm.StarvationAhoy.Client.Gui;


import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.util.DualObjectLink;
import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Map;

public class InfoBookGUI extends GuiScreen{

    int bookHeight = 180;
    int bookWidth = 146;
    private BookPageRegistry registry = new BookPageRegistry();

    @Override
    public void initGui()
    {


    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawBookBackground();
        this.interpretBookPages();
        for (int i = 0; i < this.buttonList.size(); ++i)
        {
            ((GuiButton)this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY);
        }

    }

    private void interpretBookPages() {
        Map<String, DualObjectLink<ItemStack, Boolean>> pages = registry.getBookTabs();
        int left = width / 2 - bookWidth / 2;
        int top = (height / 2 - bookHeight / 2) + 2;
        int topItems = (height / 2 - bookHeight / 2) + 50;

        for(int i=0; i<pages.size(); i++){
            String page = (String) pages.keySet().toArray()[i];
            DualObjectLink<ItemStack, Boolean> obj = pages.get(page);
            boolean displayPage = obj.getB();
            ItemStack stack = obj.getA();

            this.fontRendererObj.setUnicodeFlag(true);
            if(displayPage) {
                if (stack != null) {
                    GlStateManager.pushMatrix();
                    RenderHelper.enableStandardItemLighting();
                    GlStateManager.translate(left+255, topItems + 25, 0);
                    GlStateManager.scale(0.5f, 0.5f, 0.5f);
                    GlStateManager.translate(-(left+255), -(topItems + 25), 0);
                    //ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                    //int guiScale = scaledresolution.getScaleFactor();
                    // GlStateManager.translate((292 - width), (541 - height), 0);
                    this.itemRender.renderItemIntoGUI(stack, left - 225, topItems - 110);

                    topItems += 9;
                    GlStateManager.popMatrix();
                    this.buttonList.clear();
                    this.buttonList.add(new SelectionButton(i, left+12, top +5, page));
                    //this.fontRendererObj.drawString(StatCollector.translateToLocal("infobook.title." + page), left + 25, top + 6, 000000);
                    top += 9;
                } else {
                    int size = this.fontRendererObj.getStringWidth(StatCollector.translateToLocal("infobook.header." + page));
                    int lefts = width / 2 - size / 2;
                    this.fontRendererObj.drawString("§n§l"+ StatCollector.translateToLocal("infobook.header." + page), lefts -6, top + 6, 000000);
                    top += 11;
                    topItems += 11;
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

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        if (mouseButton == 0)
        {
            for (int i = 0; i < this.buttonList.size(); ++i)
            {
                GuiButton guibutton = (GuiButton)this.buttonList.get(i);

                if (guibutton.mousePressed(this.mc, mouseX, mouseY))
                {
                    //guibutton.playPressSound(this.mc.getSoundHandler());
                    this.actionPerformed(guibutton);
                }
            }
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button instanceof SelectionButton){
            SelectionButton b = (SelectionButton)button;
            SALog.error(b.getPageName());
        }
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
