package dmfmm.StarvationAhoy.Client.Gui;


import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class InfoBookGUI extends GuiScreen{

    private static int PaneWidth = 256;
    private static int PaneHeight = 202;
    int bookHeight = 90;
    int bookWidth = 73;
    int guiWidth = 365;
    int guiHeight = 450;




    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawBookBackground();
    }

    private void drawBookBackground() {
        int left = width / 2 - guiWidth / 2;
        int top = height / 2 - guiHeight / 2;


        GlStateManager.pushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glScaled(2.5, 2.5, 2.5);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("starvationahoy", "textures/gui/Infobook.png"));
        this.drawTexturedModalRect(left, top, 409, 0, bookWidth, bookHeight);
        GlStateManager.popMatrix();
    }

}
