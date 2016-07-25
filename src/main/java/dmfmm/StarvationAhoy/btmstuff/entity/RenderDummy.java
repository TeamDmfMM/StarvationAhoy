package dmfmm.StarvationAhoy.btmstuff.entity;


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDummy extends RenderLivingBase<EntityDummy> {


    public static final Factory FACTORY = new Factory();

    public RenderDummy(RenderManager redManager) {
        super(redManager, new ModelPlayer(0F, false), 0.5F);
        this.addLayer(new HeldItemLayer(this));
    }

    public ModelPlayer getMainModel()
    {
        return (ModelPlayer) super.getMainModel();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDummy entity) {
        return new ResourceLocation("textures/entity/steve.png");
    }

    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0F, 0F, 0.0F);
    }

    public void doRender(EntityDummy entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        //super.doRender(entity, x, y, z, entityYaw, partialTicks);

        getMainModel().setInvisible(true);
        ItemStack itemstack = entity.getHeldItemMainhand();
        ItemStack itemstack1 = entity.getHeldItemOffhand();
        getMainModel().bipedHeadwear.showModel = true;
        getMainModel().bipedBodyWear.showModel = true;
        getMainModel().bipedLeftLegwear.showModel = true;
        getMainModel().bipedRightLegwear.showModel = true;
        getMainModel().bipedLeftArmwear.showModel = true;
        getMainModel().bipedRightArmwear.showModel = true;
        ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
        ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
        if (itemstack != null) {
            modelbiped$armpose = ModelBiped.ArmPose.ITEM;
        }
        if (itemstack1 != null) {
            modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;
        }
        if (entity.getPrimaryHand() == EnumHandSide.RIGHT)
        {
            getMainModel().rightArmPose = modelbiped$armpose;
            getMainModel().leftArmPose = modelbiped$armpose1;
        }
        else
        {
            getMainModel().rightArmPose = modelbiped$armpose1;
            getMainModel().leftArmPose = modelbiped$armpose;
        }

        GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
    }





    public static class Factory implements IRenderFactory<EntityDummy> {

        @Override
        public Render<? super EntityDummy> createRenderFor(RenderManager manager) {
            return new RenderDummy(manager);
        }
    }
}
