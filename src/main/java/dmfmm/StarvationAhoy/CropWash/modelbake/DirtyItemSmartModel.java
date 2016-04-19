package dmfmm.StarvationAhoy.CropWash.modelbake;


import com.google.common.base.Function;
import com.google.common.base.Joiner;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.*;
import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DirtyItemSmartModel implements IModel, IModelCustomData, IRetexturableModel {

    private final IBakedModel model;
    String drawing;

    private static final String BASEIMGLOC = "starvationahoy:items/dirty_overlay";

    static DirtyItemSmartModel MODEL = new DirtyItemSmartModel();

    public DirtyItemSmartModel() {
        this("minecraft:carrot");
    }

    public DirtyItemSmartModel(String dr) {
        this.drawing = dr;
        model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(Item.itemRegistry.getObject(new ResourceLocation(dr)), 1));
    }

    @Override
    public IModel process(ImmutableMap<String, String> customData) {

        String strings = "";

        if (customData.containsKey("data")) {
            strings = customData.get("data");
        }
        return new DirtyItemSmartModel(strings);
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.of();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        ImmutableList.Builder<ResourceLocation> builder = ImmutableList.builder();
        builder.add(new ResourceLocation(BASEIMGLOC));
        return builder.build();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transformMap = IPerspectiveAwareModel.MapWrapper.getTransforms(state);
        TRSRTransformation transform = state.apply(Optional.<IModelPart>absent()).or(TRSRTransformation.identity());

        ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();

        ImmutableList.Builder<ResourceLocation> objectBuilder = ImmutableList.builder();

            try {
                ResourceLocation r = new ResourceLocation(BASEIMGLOC);
                objectBuilder.add(r);
            }
            catch (NullPointerException ignored) {

            }
        IBakedModel model = new ItemLayerModel(objectBuilder.build()).bake(state, format, bakedTextureGetter);
        builder.addAll(this.model.getQuads(null, null, 0));
        builder.addAll(model.getQuads(null, null, 0));

        return new BakedDirtyItemModel(this, builder.build(), this.model.getParticleTexture(), format, transformMap);
    }

    @Override
    public IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }

    @Override
    public IModel retexture(ImmutableMap<String, String> textures) {
        return this;
    }

    private static final class BakedNBTFoodOverrideHandler extends ItemOverrideList {

        public static final BakedNBTFoodOverrideHandler INSTANCE = new BakedNBTFoodOverrideHandler();

        public BakedNBTFoodOverrideHandler() {
            super (ImmutableList.<ItemOverride>of());
        }

        @Override
        public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {

            BakedDirtyItemModel model = (BakedDirtyItemModel) originalModel;

            String dtat = Item.itemRegistry.getNameForObject(ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem()).toString();

            if (!model.cache.containsKey(dtat)) {
                Joiner joiner = Joiner.on(";");
                IModel model2 = model.parent.process(ImmutableMap.of("data", dtat));
                Function<ResourceLocation, TextureAtlasSprite> textureGetter;
                textureGetter = new Function<ResourceLocation, TextureAtlasSprite>()
                {
                    public TextureAtlasSprite apply(ResourceLocation location)
                    {
                        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                    }
                };

                IBakedModel bakedModel = model2.bake(new SimpleModelState(model.transforms), model.format, textureGetter);
                model.cache.put(dtat, bakedModel);
                return bakedModel;
            }

            return model.cache.get(dtat);

        }
    }

    public static class BakedDirtyItemModel implements IPerspectiveAwareModel {

        private final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;
        private final DirtyItemSmartModel parent;
        private final Map<String, IBakedModel> cache;
        private final ImmutableList<BakedQuad> quads;
        private final TextureAtlasSprite particle;
        private final VertexFormat format;

        public BakedDirtyItemModel(DirtyItemSmartModel parent, ImmutableList<BakedQuad> quads, TextureAtlasSprite particle, VertexFormat fmt, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms) {
            this.quads = quads;
            this.particle = particle;
            this.format = fmt;
            this.parent = parent;
            this.transforms = transforms;
            this.cache = Maps.newHashMap();
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
            return MapWrapper.handlePerspective(this, transforms, cameraTransformType);
        }

        @Override
        public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
        {
            if(side == null) return quads;
            return ImmutableList.of();
        }

        public boolean isAmbientOcclusion() { return true;  }
        public boolean isGui3d() { return false; }
        public boolean isBuiltInRenderer() { return false; }
        public TextureAtlasSprite getParticleTexture() { return particle; }
        public ItemCameraTransforms getItemCameraTransforms() { return ItemCameraTransforms.DEFAULT; }

        @Override
        public ItemOverrideList getOverrides() {
            return BakedNBTFoodOverrideHandler.INSTANCE;
        }
    }
    public enum ModelLodaer implements ICustomModelLoader
    {
        instance;

        @Override
        public boolean accepts(ResourceLocation modelLocation)
        {
            return modelLocation.getResourceDomain().equals("starvationahoy") && modelLocation.getResourcePath().contains("dirtymodel");
        }

        @Override
        public IModel loadModel(ResourceLocation modelLocation) throws IOException
        {
            return MODEL;
        }

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager)
        {
            // no need to clear cache since we create a new model instance
        }
    }
}
