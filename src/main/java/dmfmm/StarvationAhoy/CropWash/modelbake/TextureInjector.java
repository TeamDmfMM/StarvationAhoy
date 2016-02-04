package dmfmm.StarvationAhoy.CropWash.modelbake;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
@SideOnly(Side.CLIENT)
public class TextureInjector {


    @SubscribeEvent
    public void sticherEventPre(TextureStitchEvent.Pre event) {
        event.map.registerSprite(new ResourceLocation("starvationahoy:items/dirty_overlay"));

        event.map.registerSprite(new ResourceLocation("starvationahoy:items/dirty_overlay_back"));
    }


}
