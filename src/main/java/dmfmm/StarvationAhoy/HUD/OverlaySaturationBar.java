package dmfmm.StarvationAhoy.HUD;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.items.ItemLoad;
public class OverlaySaturationBar extends Gui {
	
	Minecraft mc;
	
	public OverlaySaturationBar(Minecraft mc){
		this.mc = mc;
	}
	
	private static final int SATURATION_BAR_X = 3;
	private static final int SATURATION_BAR_Y = 3;
	private static final String SATURATION_TEXT_UNLOCALIZED = "hud.statarmor.saturation";
	
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
		}
		if (count == 1){
			this.mc.fontRenderer.drawString(StatCollector.translateToLocal(SATURATION_TEXT_UNLOCALIZED)+ ": " + this.mc.thePlayer.getFoodStats().getSaturationLevel(), SATURATION_BAR_X, SATURATION_BAR_Y, 16430373);
		}
	  }
	
}
