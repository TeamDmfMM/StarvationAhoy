package dmfmm.StarvationAhoy.Client.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * Created by dmf444 on 3/5/2016. Code originally written
 * for StarvationAhoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class SelectionButton extends GuiButton{

    private static String text;


    public SelectionButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, 118, 9, "");
        this.text = buttonText;
    }

    public static String getPageName(){
        return text;
    }
    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        FontRenderer fontrenderer = mc.fontRendererObj;

        fontrenderer.setUnicodeFlag(true);
        int color=000000;
        if(mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height){
            color = 16777215;
        }

        fontrenderer.drawString(StatCollector.translateToLocal("infobook.title." + text), (int) xPosition+15, yPosition, color);
        fontrenderer.setUnicodeFlag(false);
    }
}
