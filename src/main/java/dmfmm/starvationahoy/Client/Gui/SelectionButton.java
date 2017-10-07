package dmfmm.starvationahoy.Client.Gui;

import dmfmm.starvationahoy.Core.Init.SASoundEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;


/**
 * Created by dmf444 on 3/5/2016. Code originally written
 * for starvationahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class SelectionButton extends GuiButton{

    private String text;


    public SelectionButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, 118, 9, "");
        this.text = buttonText;
    }

    public String getPageName(){
        return text;
    }
    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        FontRenderer fontrenderer = mc.fontRendererObj;

        fontrenderer.setUnicodeFlag(true);
        int color=000000;
        if(mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height){
            color = 10066431;
            GlStateManager.pushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("starvationahoy", "textures/gui/infobookmain.png"));
            GlStateManager.color(1f, 1f, 0.8f);
            this.drawTexturedModalRect(xPosition, yPosition, 0, 180, 118, 9);
            GlStateManager.popMatrix();
        }

        fontrenderer.drawString(I18n.format("infobook.title." + text), (int) xPosition+15, yPosition, color);
        fontrenderer.setUnicodeFlag(false);
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SASoundEvent.pageFlip, 1.0F));
    }
}
