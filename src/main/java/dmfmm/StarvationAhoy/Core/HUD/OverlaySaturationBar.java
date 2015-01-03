package dmfmm.StarvationAhoy.Core.HUD;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.Core.util.SALog;

public class OverlaySaturationBar extends Gui {
	
	Minecraft mc;
	
	public OverlaySaturationBar(Minecraft mc){
		this.mc = mc;
	}
	
	private static final int SATURATION_BAR_X = CRef.getOSX();
	private static final int SATURATION_BAR_Y = CRef.getOSY();
	private static final String SATURATION_TEXT_UNLOCALIZED = "hud.statarmor.saturation";
	
	private static final int EXHAUSTION_BAR_X = CRef.getOSX();
	private static final int EXHAUSTION_BAR_Y = CRef.getOSY() + 7;
	private static final String EXHAUSTION_TEXT_UNLOCALIZED = "hud.statarmor.exhaustion";
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	  public void onRenderExperienceBar(RenderGameOverlayEvent event)
	  {
		
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
	    {      
	      return;
	    }
		int count = 0;
		for (int i : new int[] {3,2}){
			if (this.mc.thePlayer.inventory.armorInventory[i] == null){
				continue;
			}
			if (i == 3 && this.mc.thePlayer.inventory.armorInventory[3].getItem() == ItemLoad.stat_helm){
				count += 1;
				continue;
			}
			if (i == 2 && this.mc.thePlayer.inventory.armorInventory[2].getItem() == ItemLoad.stat_chest){
				count += 1;
				continue;
			}
		}
		if (count == 1){
			this.mc.renderEngine.bindTexture(new ResourceLocation("starvationahoy", "textures/gui/SaturationBar.png"));
			this.drawTexturedModalRect(EXHAUSTION_BAR_X, EXHAUSTION_BAR_Y, 0, 0, 62, 9);
			float Sat = this.mc.thePlayer.getFoodStats().getSaturationLevel();
			if(Sat >= 20){
				this.drawTexturedModalRect(EXHAUSTION_BAR_X + 50, EXHAUSTION_BAR_Y + 2, 0, 9, 1, 5);
			} else if (Sat < 20){
				float i = (float) (Sat * 2.5); //2.5 * 20 = 50 (# of pixels in bar)
				this.drawTexturedModalRect((int) (EXHAUSTION_BAR_X + i), EXHAUSTION_BAR_Y + 2, 0, 9, 1, 5);
			}
			this.mc.fontRenderer.setUnicodeFlag(true);
			this.mc.fontRenderer.drawString(StatCollector.translateToLocal(SATURATION_TEXT_UNLOCALIZED), SATURATION_BAR_X + 7, SATURATION_BAR_Y, 16430373);
			this.mc.fontRenderer.setUnicodeFlag(false);
		}
		if (count == 2){
			this.mc.fontRenderer.drawString(StatCollector.translateToLocal(SATURATION_TEXT_UNLOCALIZED)+ ": " + this.mc.thePlayer.getFoodStats().getSaturationLevel(), SATURATION_BAR_X, SATURATION_BAR_Y, 16430373);
			this.mc.fontRenderer.drawString(StatCollector.translateToLocal(EXHAUSTION_TEXT_UNLOCALIZED)+ ": " + getExhuast(this.mc.thePlayer.getFoodStats()), EXHAUSTION_BAR_X, EXHAUSTION_BAR_Y, 2823662);
		}
	  }
	
	public float getExhuast(FoodStats food){
		// just for demo
		Float EXlevel = 0.0F;
		return EXlevel;
	}
	
}
