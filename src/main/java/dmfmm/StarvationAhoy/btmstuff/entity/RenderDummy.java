package dmfmm.StarvationAhoy.btmstuff.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDummy extends RenderLiving<EntityDummy> {


    public static final Factory FACTORY = new Factory();

    public RenderDummy(RenderManager redManager) {
        super(redManager, new ModelPlayer(0F, false), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDummy entity) {
        return new ResourceLocation("Carls", "omdpa");
    }



    public void doRender(EntityDummy entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        if (!this.renderOutlines)
        {
            this.renderLeash(entity, x, y, z, entityYaw, partialTicks);
        }
    }





    public static class Factory implements IRenderFactory<EntityDummy> {

        @Override
        public Render<? super EntityDummy> createRenderFor(RenderManager manager) {
            return new RenderDummy(manager);
        }
    }
}
