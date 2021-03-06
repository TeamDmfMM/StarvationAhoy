package dmfmm.StarvationAhoy.Core.HUD;

import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
	private static final String FOODSAT_TEXT_UNLOCALIZED = "hud.statarmor.foodSat";
    private static final String FOODHUNGER_TEXT_UNLOCALIZED = "hud.statarmor.hunger";
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	  public void onRenderExperienceBar(RenderGameOverlayEvent event)
	  {
		if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
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
			drawSaturationBar();
		}
		if (count == 2){
			drawSaturationBar();
            getCurrentFoodStat(this.mc.thePlayer);
		}
	  }

    private void drawSaturationBar(){
        this.mc.renderEngine.bindTexture(new ResourceLocation("starvationahoy", "textures/gui/SaturationBar.png"));
        this.drawTexturedModalRect(EXHAUSTION_BAR_X, EXHAUSTION_BAR_Y, 0, 0, 62, 9);
        float Sat = this.mc.thePlayer.getFoodStats().getSaturationLevel();
        if(Sat >= 20){
            this.drawTexturedModalRect(EXHAUSTION_BAR_X + 50, EXHAUSTION_BAR_Y + 2, 0, 9, 1, 5);
        } else if (Sat < 20){
            float i = (float) (Sat * 2.5); //2.5 * 20 = 50 (# of pixels in bar)
            this.drawTexturedModalRect((int) (EXHAUSTION_BAR_X + i), EXHAUSTION_BAR_Y + 2, 0, 9, 1, 5);
        }
        this.mc.fontRendererObj.setUnicodeFlag(true);
        this.mc.fontRendererObj.drawString(I18n.translateToLocal(SATURATION_TEXT_UNLOCALIZED), SATURATION_BAR_X + 7, SATURATION_BAR_Y, 16430373);
        this.mc.fontRendererObj.setUnicodeFlag(false);
    }

	public void getCurrentFoodStat(EntityPlayerSP player){
        Item unknown = null;
        if(player.getHeldItemMainhand() != null) {
            unknown = player.getHeldItemMainhand().getItem();
            if (unknown instanceof ItemFood) {
                ItemFood food = (ItemFood) unknown;
                int HealAmt = KnownFoods.getFoodHunger(new ItemStack(food));
                float Saturation = KnownFoods.getFoodSaturation(new ItemStack(food));
                this.mc.fontRendererObj.setUnicodeFlag(true);
                this.mc.fontRendererObj.drawString(I18n.translateToLocal(FOODHUNGER_TEXT_UNLOCALIZED), SATURATION_BAR_X , SATURATION_BAR_Y + 15, 16430373);
                drawFoodHunks((float) HealAmt / 2);
                this.mc.fontRendererObj.drawString(String.format(I18n.translateToLocal(FOODSAT_TEXT_UNLOCALIZED), Saturation), SATURATION_BAR_X, SATURATION_BAR_Y + 22, 16430373);
                this.mc.fontRendererObj.setUnicodeFlag(false);
            }
        }

	}
    private void drawFoodHunks(Float hunks){
        String[] split = (hunks.toString()).split("\\.");
        //SALog.fatal(split[0]);
        if(split.length > 0) {
            int First = Integer.parseInt(split[0]);
            int Second = Integer.parseInt(split[1]);
            int i = 0;
            int Loc = 0;
            ResourceLocation res = new ResourceLocation("minecraft:textures/gui/icons.png");

            this.mc.renderEngine.bindTexture(res);
            while (i < First) {
                this.drawTexturedModalRect(SATURATION_BAR_X + 57+ Loc, SATURATION_BAR_Y + 17 , 53, 28, 7, 7);
                Loc += 7;
                i++;
            }
            if (Second != 0) {
                this.drawTexturedModalRect(SATURATION_BAR_X + 57+ Loc, SATURATION_BAR_Y + 17, 62, 28, 7, 7);
            }

        }
    }
	
}
