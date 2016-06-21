package dmfmm.StarvationAhoy.FoodStats.DebugKeyBind;

import dmfmm.StarvationAhoy.FoodStats.PlayerDiet.Diet;
import dmfmm.StarvationAhoy.FoodStats.PlayerInstanceHolder;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */


public class OverlayHungerDebug {

    @SubscribeEvent
    public void renderExpBar(RenderGameOverlayEvent event)
    {
        if(event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE || !StarvationAhoy.proxy.debugKey.isKeyDown())
        {
            return;
        }

        Diet d = PlayerInstanceHolder.instance.dietMap.get(Minecraft.getMinecraft().thePlayer.getGameProfile().getName());

        FontRenderer f = Minecraft.getMinecraft().fontRendererObj;

        f.drawString(" ==== Diet Info ==== ", 100, 20, 16777215);
        f.drawString(" = Nutrient (n1, n2, nut) (" + d.nutrient1 + "," + d.nutrient2 + "," + d.nutrient + ")", 70, 60, 16777215);
        f.drawString(" = Fat " + d.fat, 100, 100, 16777215);
        f.drawString(" = Calories " + d.calories, 100, 140, 16777215);
        f.drawString(" = Weight " + d.weight, 100, 180, 16777215);
        f.drawString(" =================== ", 100, 220, 16777215);

    }
}
