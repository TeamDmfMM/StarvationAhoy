package dmfmm.StarvationAhoy.Client.Gui.book_gui;

import dmfmm.StarvationAhoy.Core.Init.SASoundEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class Buttons {

    static int ARROW_BUTTON_WIDTH = 18;
    static int ARROW_BUTTON_HEIGHT = 12;

    static int ARROW_START_X = 0;
    static int ARROW_START_Y = 166;

    static int ARROW_LEFT_POS_X = 21;
    static int ARROW_LEFT_POS_Y = 150;

    static int ARROW_NEXT_POS_X = 221;
    static int ARROW_NEXT_POS_Y = 160;

    static String ARROW_LOCATION = "starvationahoy:/textures/gui/infobook_background.png";

    public static class NextPage extends GuiButton {

        boolean isForward = false;

        public NextPage(int par1, int par2, int par3, boolean par4){
            super(par1, par2, par3, ARROW_BUTTON_WIDTH, ARROW_BUTTON_HEIGHT, "");
            isForward = par4;
        }

        public void drawButton(Minecraft mc, int par2, int par3){
            if (this.visible){
                boolean flag = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_BLEND);

                mc.getTextureManager().bindTexture(new ResourceLocation(ARROW_LOCATION));

                int k = ARROW_START_X;
                int l = ARROW_START_Y;

                if (flag){
                    k += ARROW_BUTTON_WIDTH + 5;
                }

                if (!isForward){
                    l += ARROW_BUTTON_HEIGHT;
                }

                this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, ARROW_BUTTON_WIDTH, ARROW_BUTTON_HEIGHT);
                GL11.glDisable(GL11.GL_BLEND);
            }
        }
        public void playPressSound(SoundHandler soundHandlerIn)
        {
            soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SASoundEvent.pageFlip, 1.0F));
        }
    }

}
