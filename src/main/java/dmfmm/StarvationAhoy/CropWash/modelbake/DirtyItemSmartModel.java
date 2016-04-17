package dmfmm.StarvationAhoy.CropWash.modelbake;

import com.google.common.primitives.Ints;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DirtyItemSmartModel implements ISmartItemModel {
    String dirtyOverlay = "starvationahoy:items/dirty_overlay", dirtyOverlayBack ="starvationahoy:items/dirty_overlay_back";
    Item current = null;

    IBakedModel exist;



    public IBakedModel handleItemState(ItemStack stack) {
        current =  ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")).getItem();
        exist = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Original")));

        return this;
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) {
        return getQuadsForT();
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return exist.getGeneralQuads();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getMinecraft().getTextureMapBlocks()
                .getAtlasSprite("minecraft:blocks/diamond_block");
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        ItemCameraTransforms cameraTransforms = new ItemCameraTransforms(
                new ItemTransformVec3f(new Vector3f(-90.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.05F, -0.2F), new Vector3f(0.55F, 0.55F, 0.55F)),//tp
                new ItemTransformVec3f(new Vector3f(0F, -135F, 25.0F), new Vector3f(0F, 0.3F, 0.1F), new Vector3f(1.7F, 1.7F, 1.7F)),//fp
                new ItemTransformVec3f(new Vector3f(0F, 0F, 0.0F), new Vector3f(), new Vector3f(1.2F, 1.2F, 1.2F)),//head
                new ItemTransformVec3f(new Vector3f(0F, 0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.F), new Vector3f(1.0F, 1.0F, 1.0F)),//gui
                new ItemTransformVec3f(new Vector3f(0F, -190F, 0.0F), new Vector3f(0.0F, -0.05F, 0.F), new Vector3f(1.2F, 1.2F, 1.2F)),//ground
                new ItemTransformVec3f(new Vector3f(0F, -190F, 0.0F), new Vector3f(0.0F, -0.05F, 0.F), new Vector3f(1.2F, 1.2F, 1.2F))//fixed
        );
        return cameraTransforms;
    }

    public List<BakedQuad> getQuadsForT() {


        final float center1 = 0.5f;
        final float center2 = 0.5f;
        final float size = 1.0f;

        final float BUILTIN_GEN_ITEM_THICKNESS = 1/16.0F;
        final float BUILTIN_GEN_ITEM_Z_CENTRE = 0.5F;
        final float BUILTIN_GEN_ITEM_Z_MAX = BUILTIN_GEN_ITEM_Z_CENTRE + BUILTIN_GEN_ITEM_THICKNESS / 2.0F;
        final float BUILTIN_GEN_ITEM_Z_MIN = BUILTIN_GEN_ITEM_Z_CENTRE - BUILTIN_GEN_ITEM_THICKNESS / 2.0F;
        final float SOUTH_FACE_POSITION = 1.0F;  // the south face of the cube is at z = 1.0F
        final float NORTH_FACE_POSITION = 0.0F;  // the north face of the cube is at z = 0.0F

        final float DISTANCE_BEHIND_SOUTH_FACE = SOUTH_FACE_POSITION - BUILTIN_GEN_ITEM_Z_MAX;
        final float DISTANCE_BEHIND_NORTH_FACE = BUILTIN_GEN_ITEM_Z_MIN - NORTH_FACE_POSITION;

        int r0 = 0;

        float delta = 0.001f;

        TextureAtlasSprite t2 = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(dirtyOverlay);
        TextureAtlasSprite t3 = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(dirtyOverlayBack);


        BakedQuad front = createBakedQuadForFace(center1, size,//- 0.031f
                center2, size, -DISTANCE_BEHIND_SOUTH_FACE + delta,
                r0,
                t2,
                EnumFacing.SOUTH);

        BakedQuad back = createBakedQuadForFace(center1-0.075f, size,
                center2-0.125f, size,
                -DISTANCE_BEHIND_NORTH_FACE + delta,
                r0, t2, EnumFacing.NORTH);

        List<BakedQuad> quad = new ArrayList<>();
        quad.add(front);
        quad.add(back);
        return quad;
    }

    private BakedQuad createBakedQuadForFace(float centreLR, float width, float centreUD, float height, float forwardDisplacement,
                                             int itemRenderLayer,
                                             TextureAtlasSprite texture, EnumFacing face)
    {
        float x1, x2, x3, x4;
        float y1, y2, y3, y4;
        float z1, z2, z3, z4;
        final float CUBE_MIN = 0.0F;
        final float CUBE_MAX = 1.0F;
        float u = 16;
        float v = 16;
        float u4 = 0;
        float u2 = 16;
        float u3 = 0;
        float v2 = 0;
        float v3 = 0;
        float v4 = 16;

        switch (face) {
            case UP: {
                x1 = x2 = centreLR + width/2.0F;
                x3 = x4 = centreLR - width/2.0F;
                z1 = z4 = centreUD + height/2.0F;
                z2 = z3 = centreUD - height/2.0F;
                y1 = y2 = y3 = y4 = CUBE_MAX + forwardDisplacement;
                break;
            }
            case DOWN: {
                x1 = x2 = centreLR + width/2.0F;
                x3 = x4 = centreLR - width/2.0F;
                z1 = z4 = centreUD - height/2.0F;
                z2 = z3 = centreUD + height/2.0F;
                y1 = y2 = y3 = y4 = CUBE_MIN - forwardDisplacement;
                break;
            }
            case WEST: {
                z1 = z2 = centreLR + width/2.0F;
                z3 = z4 = centreLR - width/2.0F;
                y1 = y4 = centreUD - height/2.0F;
                y2 = y3 = centreUD + height/2.0F;
                x1 = x2 = x3 = x4 = CUBE_MIN - forwardDisplacement;
                break;
            }
            case EAST: {
                z1 = z2 = centreLR - width/2.0F;
                z3 = z4 = centreLR + width/2.0F;
                y1 = y4 = centreUD - height/2.0F;
                y2 = y3 = centreUD + height/2.0F;
                x1 = x2 = x3 = x4 = CUBE_MAX + forwardDisplacement;
                break;
            }
            case NORTH: {
                x1 = x2 = centreLR - width/2.0F;
                x3 = x4 = centreLR +  width/2.0F;
                y1 = y4 = centreUD - height/2.0F;
                y2 = y3 = centreUD + height/2.0F;
                z1 = z2 = z3 = z4 = CUBE_MIN - forwardDisplacement;
                u = 16;
                v = 0;
                u4 = 0;
                u2 = 16;
                u3 = 0;
                v2 = 16;
                v3 = 16;
                v4 = 0;
                break;
            }
            case SOUTH: {
                x1 = x2 = centreLR + width/2.0F;
                x3 = x4 = centreLR - width/2.0F;
                y1 = y4 = centreUD - height/2.0F;
                y2 = y3 = centreUD + height/2.0F;
                z1 = z2 = z3 = z4 = CUBE_MAX + forwardDisplacement;
                break;
            }
            default: {
                assert false : "Unexpected facing in createBakedQuadForFace:" + face;
                return null;
            }
        }



        return new BakedQuad(Ints.concat(vertexToInts(x1, y1, z1, Color.WHITE.getRGB(), texture, u, v),
                vertexToInts(x2, y2, z2, Color.WHITE.getRGB(), texture, u2, v2),
                vertexToInts(x3, y3, z3, Color.WHITE.getRGB(), texture, u3, v3),
                vertexToInts(x4, y4, z4, Color.WHITE.getRGB(), texture, u4, v4)),
                itemRenderLayer, face);
    }
    private int[] vertexToInts(float x, float y, float z, int color, TextureAtlasSprite texture, float u, float v)
    {
        return new int[] {
                Float.floatToRawIntBits(x),
                Float.floatToRawIntBits(y),
                Float.floatToRawIntBits(z),
                color,
                Float.floatToRawIntBits(texture.getInterpolatedU(u)),
                Float.floatToRawIntBits(texture.getInterpolatedV(v)),
                0
        };
    }

}
