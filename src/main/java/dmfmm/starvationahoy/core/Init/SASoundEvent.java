package dmfmm.starvationahoy.core.Init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 * Created by TeamDMFMM on 4/17/2016. Code originally written
 * for starvationahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therefore
 * credit us, just don't steal large portions of this.
 */
public class SASoundEvent {


    public static SoundEvent pageFlip;

    private static int size = 0;

    public static void init() {
        size = SoundEvent.REGISTRY.getKeys().size();

        pageFlip = register("pageFlip");
    }

    public static SoundEvent register(String name) {
        ResourceLocation loc = new ResourceLocation("starvationahoy:" + name);
        SoundEvent e = new SoundEvent(loc);

        SoundEvent.REGISTRY.register(size, loc, e);
        size++;

        return e;
    }
}
