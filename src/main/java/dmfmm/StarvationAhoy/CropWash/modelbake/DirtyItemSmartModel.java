package dmfmm.StarvationAhoy.CropWash.modelbake;

import com.google.common.primitives.Ints;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartItemModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DirtyItemSmartModel implements ISmartItemModel {
    String dirtyOverlay = "starvationahoy:items/dirty_overlay";
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
        return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(Items.apple)).getItemCameraTransforms();
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

        BakedQuad front = createBakedQuadForFace(center1- 0.031f, size,
                center2, size, -DISTANCE_BEHIND_SOUTH_FACE + delta,
                r0,
                t2,
                EnumFacing.SOUTH);

        BakedQuad back = createBakedQuadForFace(center1, size,
                center2, size,
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
                x3 = x4 = centreLR + width/2.0F;
                y1 = y4 = centreUD - height/2.0F;
                y2 = y3 = centreUD + height/2.0F;
                z1 = z2 = z3 = z4 = CUBE_MIN - forwardDisplacement;
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

        return new BakedQuad(Ints.concat(vertexToInts(x1, y1, z1, Color.WHITE.getRGB(), texture, 16, 16),
                vertexToInts(x2, y2, z2, Color.WHITE.getRGB(), texture, 16, 0),
                vertexToInts(x3, y3, z3, Color.WHITE.getRGB(), texture, 0, 0),
                vertexToInts(x4, y4, z4, Color.WHITE.getRGB(), texture, 0, 16)),
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
