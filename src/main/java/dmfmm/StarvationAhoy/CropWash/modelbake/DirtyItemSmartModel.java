package dmfmm.StarvationAhoy.CropWash.modelbake;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.*;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Matrix4f;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DirtyItemSmartModel implements IModel, IModelCustomData, IRetexturableModel {

    ResourceLocation orig_tex = new ResourceLocation("starvationahoy", "items/dirty_overlay");

    IBakedModel mimicky;

    static DirtyItemSmartModel MODEL = new DirtyItemSmartModel();

    public DirtyItemSmartModel() {
        this(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(Items.apple))); // # should be a missingtex.
    }

    public DirtyItemSmartModel(IBakedModel mimic) {
        this.mimicky = mimic;
    }

    /**
     * Allows the model to process custom data from the variant definition.
     * If unknown data is encountered it should be skipped.
     *
     * @param customData
     * @return a new model, with data applied.
     */
    @Override
    public IModel process(ImmutableMap<String, String> customData) {
        if (customData.get("id_name") != null) {
            ItemStack assumed_itemstack = new ItemStack(Item.itemRegistry.getObject(new ResourceLocation(customData.get("id_name"))));
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(assumed_itemstack);
            return new DirtyItemSmartModel(model);
        }
        else {
            ItemStack assumed_itemstack = new ItemStack(Items.apple);
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(assumed_itemstack);
            return new DirtyItemSmartModel(model);
        }
    }

    /**
     * Applies new textures to the model.
     * The returned model should be independent of the accessed one,
     * as a model should be able to be retextured multiple times producing
     * a separate model each time.
     * <p>
     * The input map MAY map to an empty string "" which should be used
     * to indicate the texture was removed. Handling of that is up to
     * the model itself. Such as using default, missing texture, or
     * removing vertices.
     * <p>
     * The input should be considered a DIFF of the old textures, not a
     * replacement as it may not contain everything.
     *
     * @param textures New
     * @return Model with textures applied.
     */
    @Override
    public IModel retexture(ImmutableMap<String, String> textures) {
        return this;
    }

    @Override
    public Collection<ResourceLocation> getDependencies()
    {
        return ImmutableList.of();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        ImmutableList.Builder<ResourceLocation> r = ImmutableList.builder();
        r.add(orig_tex);
        return r.build();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transformMap = IPerspectiveAwareModel.MapWrapper.getTransforms(BakedDirtyModel.getItemCameraTransforms2());
        TextureAtlasSprite baseSprite;
        try {
            baseSprite = this.mimicky.getParticleTexture();
        }
        catch (NullPointerException e) {
            baseSprite = bakedTextureGetter.apply(new ResourceLocation("minecraft:carrot"));
        }
        TextureAtlasSprite overlaySprite = bakedTextureGetter.apply(this.orig_tex);
        ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();

        builder.addAll(ItemLayerModel.getQuadsForSprite(0, baseSprite, format, Optional.<TRSRTransformation>absent()));
        builder.addAll(ItemLayerModel.getQuadsForSprite(1, overlaySprite, format, Optional.<TRSRTransformation>absent()));

        return new BakedDirtyModel(this, builder.build(), baseSprite, format, transformMap);

    }

    @Override
    public IModelState getDefaultState()
    {
        return TRSRTransformation.identity();
    }

    public static class BakedDirtyModel implements IPerspectiveAwareModel {

        private final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;
        private final Map<Item, IBakedModel> cache;
        private final ImmutableList<BakedQuad> quads;
        private final TextureAtlasSprite particle;
        private final VertexFormat format;
        private final DirtyItemSmartModel parent;

        public BakedDirtyModel(DirtyItemSmartModel parent, ImmutableList<BakedQuad> quadrilaterals, TextureAtlasSprite particle, VertexFormat fmt, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms) {
            this.quads = quadrilaterals;
            this.particle = particle;
            this.format = fmt;
            this.parent = parent;
            this.transforms = transforms;
            this.cache = Maps.newHashMap();
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
            return IPerspectiveAwareModel.MapWrapper.handlePerspective(this, transforms, cameraTransformType);
        }

        @Override
        public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
            if(side == null) return quads;
            return ImmutableList.of();

        }

        public boolean isAmbientOcclusion() { return true;  }
        public boolean isGui3d() { return false; }
        public boolean isBuiltInRenderer() { return false; }
        public TextureAtlasSprite getParticleTexture() { return particle; }
        public ItemCameraTransforms getItemCameraTransforms() {

            ItemCameraTransforms cameraTransforms = new ItemCameraTransforms(
                    new ItemTransformVec3f(new Vector3f(-90.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.05F, -0.2F), new Vector3f(0.55F, 0.55F, 0.55F)),//tp
                    new ItemTransformVec3f(new Vector3f(-90.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.05F, -0.2F), new Vector3f(0.55F, 0.55F, 0.55F)),//tp_l
                    new ItemTransformVec3f(new Vector3f(0F, -135F, 25.0F), new Vector3f(0F, 0.3F, 0.1F), new Vector3f(.17F, .17F, .17F)),//fp
                    new ItemTransformVec3f(new Vector3f(0F, -135F, 25.0F), new Vector3f(0F, 0.3F, 0.1F), new Vector3f(.17F, .17F, .17F)),//fp_l
                    new ItemTransformVec3f(new Vector3f(0F, 0F, 0.0F), new Vector3f(), new Vector3f(1.2F, 1.2F, 1.2F)),//head
                    new ItemTransformVec3f(new Vector3f(0F, 0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.F), new Vector3f(1.0F, 1.0F, 1.0F)),//gui
                    new ItemTransformVec3f(new Vector3f(0F, -190F, 0.0F), new Vector3f(0.0F, -0.05F, 0.F), new Vector3f(0.0005F, 0.0005F, 0.0005F)),//ground
                    new ItemTransformVec3f(new Vector3f(0F, -190F, 0.0F), new Vector3f(0.0F, -0.05F, 0.F), new Vector3f(1.2F, 1.2F, 1.2F))//fixed
            );
            return cameraTransforms;
        }
        public static ItemCameraTransforms getItemCameraTransforms2() {

            ItemCameraTransforms cameraTransforms = new ItemCameraTransforms(
                    new ItemTransformVec3f(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.20F, 0.05F), new Vector3f(0.55F, 0.55F, 0.55F)),//tp
                    new ItemTransformVec3f(new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.20F, 0.05F), new Vector3f(0.55F, 0.55F, 0.55F)),//tp_l
                    new ItemTransformVec3f(new Vector3f(0F, -950F, 20.0F), new Vector3f(0F, 0.25F, 0.1F), new Vector3f(.57F, .57F, .57F)),//fp
                    new ItemTransformVec3f(new Vector3f(0F, -95F, 20.0F), new Vector3f(0F, 0.25F, 0.1F), new Vector3f(.57F, .57F, .57F)),//fp_l
                    new ItemTransformVec3f(new Vector3f(0F, 0F, 0.0F), new Vector3f(), new Vector3f(1.2F, 1.2F, 1.2F)),//head
                    new ItemTransformVec3f(new Vector3f(0F, 0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.F), new Vector3f(1.0F, 1.0F, 1.0F)),//gui
                    new ItemTransformVec3f(new Vector3f(0F, -190F, 0.0F), new Vector3f(0.0F, 0.25F, 0.F), new Vector3f(0.5F, 0.5F, 0.5F)),//ground
                    new ItemTransformVec3f(new Vector3f(0F, -190F, 0.0F), new Vector3f(0.0F, -0.05F, 0.F), new Vector3f(1.2F, 1.2F, 1.2F))//fixed
            );
            return cameraTransforms;
        }

        @Override
        public ItemOverrideList getOverrides() {
            return DirtyOverrides.INSTANCE;
        }
    }

    public static class DirtyOverrides extends ItemOverrideList {

        public static final DirtyOverrides INSTANCE = new DirtyOverrides();

        public DirtyOverrides() {
            super (ImmutableList.<ItemOverride>of());
        }

        @Override
        public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {

            BakedDirtyModel model = (BakedDirtyModel) originalModel;

            Item itemy = stack.getItem();

            if (!model.cache.containsKey(itemy)) {
                IModel new_model = model.parent.process(ImmutableMap.of("id_name", Item.itemRegistry.getNameForObject(itemy).toString()));
                Function<ResourceLocation, TextureAtlasSprite> textureGetter;
                textureGetter = new Function<ResourceLocation, TextureAtlasSprite>()
                {
                    public TextureAtlasSprite apply(ResourceLocation location)
                    {
                        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                    }
                };
                IBakedModel bakedModel = new_model.bake(new SimpleModelState(model.transforms), model.format, textureGetter);
                model.cache.put(itemy, bakedModel);
                return bakedModel;
            }
            if(stack.hasTagCompound()) {
                NBTTagCompound tag = stack.getTagCompound().getCompoundTag("Original");
                String namze = tag.getString("id");
                Function<ResourceLocation, TextureAtlasSprite> textureGetter;
                IBakedModel InnerModel = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(namze, "inventory"));
                textureGetter = new Function<ResourceLocation, TextureAtlasSprite>()
                {
                    public TextureAtlasSprite apply(ResourceLocation location)
                    {
                        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                    }
                };
                return new DirtyItemSmartModel(InnerModel).bake(new SimpleModelState(model.transforms), model.format, textureGetter);
            }

            return model.cache.get(itemy);

        }
    }

    public enum Loader implements ICustomModelLoader {
        instance;

        @Override
        public boolean accepts(ResourceLocation modelLocation) {
            return modelLocation.getResourceDomain().equals("starvationahoy") && (modelLocation.getResourcePath().contains("dirtymodel") || modelLocation.getResourcePath().contains("dirty_item"));
        }

        @Override
        public IModel loadModel(ResourceLocation modelLocation) throws Exception {
            return MODEL;
        }

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager) {

        }
    }
}
