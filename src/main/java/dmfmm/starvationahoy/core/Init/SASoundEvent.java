package dmfmm.starvationahoy.core.Init;

import dmfmm.starvationahoy.core.lib.ModInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Created by TeamDMFMM on 4/17/2016. Code originally written
 * for starvationahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therefore
 * credit us, just don't steal large portions of this.
 */
public class SASoundEvent {


    public static SoundEvent pageFlip;

    public static void init() {

        pageFlip = register("pageFlip");
    }

    public static SoundEvent register(String name) {
        ResourceLocation loc = new ResourceLocation("starvationahoy:" + name);
        SoundEvent e = new SoundEvent(loc);
        e.setRegistryName(ModInfo.MOD_ID + ":" + name);

        ForgeRegistries.SOUND_EVENTS.register(e);

        return e;
    }
}
