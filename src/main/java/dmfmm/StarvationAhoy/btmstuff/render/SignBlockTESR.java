package dmfmm.StarvationAhoy.btmstuff.render;

import dmfmm.StarvationAhoy.btmstuff.te.SignBlockTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import static org.lwjgl.opengl.GL11.GL_QUADS;

/**
 * Created by TeamDMFMM on 7/23/2016. Code originally written
 * for Starvatione Ahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class SignBlockTESR extends TileEntitySpecialRenderer<SignBlockTE> {

    static ResourceLocation TEX_LOCATION = new ResourceLocation("starvationahoy:textures/btm/sign.png");

    @Override
    public void renderTileEntityAt(SignBlockTE te, double x, double y, double z, float partialTicks, int destroyStage) {

        // WC: Replace with DynamicTexture stuff.
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

       // GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEX_LOCATION);
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        if (te == null || te.state == null) {
            double[][] tex = new double[][] {
                    new double[] {0.0, 0.0},
                    new double[] {0.0, 0.0},
                    new double[] {0.0, 0.0},
                    new double[] {0.0, 0.0}
            };

            renderCubeFace(te, EnumFacing.UP, false, new float[]{1.0f, 0.0f, 0.0f}, tex);
            renderCubeFace(te, EnumFacing.DOWN, false, new float[]{1.0f, 0.0f, 0.0f}, tex);
            renderCubeFace(te, EnumFacing.EAST, false, new float[]{1.0f, 0.0f, 0.0f}, tex);
            renderCubeFace(te, EnumFacing.WEST, false, new float[]{1.0f, 0.0f, 0.0f}, tex);
            renderCubeFace(te, EnumFacing.NORTH, false, new float[]{1.0f, 0.0f, 0.0f}, tex);
            renderCubeFace(te, EnumFacing.SOUTH, false, new float[]{1.0f, 0.0f, 0.0f}, tex);
            GlStateManager.popMatrix();
            return;
        }

        switch (te.state) {
            case SINGULAR:
                double[][] tex = new double[][] {
                        new double[] {0.0, 0.0},
                        new double[] {0.0, 0.0},
                        new double[] {0.0, 0.0},
                        new double[] {0.0, 0.0}
                };

                renderCubeFace(te, EnumFacing.UP, false, new float[]{0.0f, 0.0f, 0.0f}, tex);
                renderCubeFace(te, EnumFacing.DOWN, false, new float[]{0.0f, 0.0f, 0.0f}, tex);
                renderCubeFace(te, EnumFacing.EAST, false, new float[]{0.0f, 0.0f, 0.0f}, tex);
                renderCubeFace(te, EnumFacing.WEST, false, new float[]{0.0f, 0.0f, 0.0f}, tex);
                renderCubeFace(te, EnumFacing.NORTH, false, new float[]{0.0f, 0.0f, 0.0f}, tex);
                renderCubeFace(te, EnumFacing.SOUTH, false, new float[]{0.0f, 0.0f, 0.0f}, tex);
                break;
            case COMPLETE:


                double[][] blank_tex = new double[][] {
                        new double[] {0.0, 0.0},
                        new double[] {0.0, 0.0},
                        new double[] {0.0, 0.0},
                        new double[] {0.0, 0.0}
                };
                double[][] real_tex;
                if (te.offset_x == 0 && te.offset_y == 0) {
                    double block_x = 1.0 / te.width;
                    double block_y = 1.0 / te.height;

                    real_tex = new double[][] {
                            new double[] {0.0, 1-0.0},
                            new double[] {0.0, 1-block_y},
                            new double[] {block_x, 1-block_y},
                            new double[] {block_x, 1-0.0}
                    };
                }
                else {
                    EnumFacing axis_x = te.direction.getAxisX();
                    EnumFacing axis_y = te.direction.getAxisY();

                    BlockPos newpos = te.getPos().offset(axis_x.getOpposite(), te.offset_x).offset(axis_y.getOpposite(), te.offset_y);
                    SignBlockTE origin = (SignBlockTE) te.getWorld().getTileEntity(newpos);

                    assert origin != null;
                    double block_x = 1.0 / origin.width;
                    double block_y = 1.0 / origin.height;

                    double origin_x = block_x * te.offset_x;
                    double origin_y = block_y * te.offset_y;
                    block_x *= te.offset_x + 1;
                    block_y *= te.offset_y + 1;

                    block_y = 1 - block_y;
                    origin_y = 1 - origin_y;

                    real_tex = new double[][] {
                            new double[] {origin_x, origin_y},
                            new double[] {origin_x, block_y},
                            new double[] {block_x, block_y},
                            new double[] {block_x, origin_y}
                    };
                }
                for (EnumFacing facing : EnumFacing.VALUES) {
                    if (facing != te.front) {
                        renderCubeFace(te, facing, false, new float[]{0.0f, 0.0f, 0.0f}, blank_tex);
                    }
                    else {
                        renderCubeFace(te, facing, false, new float[]{1f, 1f, 1f}, real_tex);
                    }
                }

        }
        GlStateManager.popMatrix();
    }

    public void renderCubeFace(TileEntity te, EnumFacing face, boolean reversed, float[] color, double[][] tex) {
        if (!te.getWorld().getBlockState(te.getPos()).shouldSideBeRendered(te.getWorld(), te.getPos(), face)) {
            return;
        }

        VertexFormat vf = DefaultVertexFormats.POSITION_TEX_COLOR;
        VertexBuffer myBuffer = Tessellator.getInstance().getBuffer();

        int x1 = 0, y1 = 0, z1 = 0, x2 = 0, y2 = 0, z2 = 0, x3 = 0, y3 = 0, z3 = 0, x4 = 0, y4 = 0, z4 = 0;

        switch (face) {
            case UP:
                x1 = 0;
                y1 = 1;
                z1 = 0;

                x2 = 1;
                y2 = 1;
                z2 = 0;

                x3 = 1;
                y3 = 1;
                z3 = 1;

                x4 = 0;
                y4 = 1;
                z4 = 1;
                break;
            case DOWN:
                x1 = 0;
                y1 = 0;
                z1 = 0;

                x2 = 1;
                y2 = 0;
                z2 = 0;

                x3 = 1;
                y3 = 0;
                z3 = 1;

                x4 = 0;
                y4 = 0;
                z4 = 1;
                break;
            case NORTH:
                x1 = 0;
                y1 = 0;
                z1 = 0;

                x2 = 0;
                y2 = 1;
                z2 = 0;

                x3 = 1;
                y3 = 1;
                z3 = 0;

                x4 = 1;
                y4 = 0;
                z4 = 0;
                break;
            case SOUTH:
                x1 = 0;
                y1 = 0;
                z1 = 1;

                x2 = 0;
                y2 = 1;
                z2 = 1;

                x3 = 1;
                y3 = 1;
                z3 = 1;

                x4 = 1;
                y4 = 0;
                z4 = 1;
                break;
            case EAST:
                x1 = 1;
                y1 = 0;
                z1 = 0;

                x2 = 1;
                y2 = 1;
                z2 = 0;

                x3 = 1;
                y3 = 1;
                z3 = 1;

                x4 = 1;
                y4 = 0;
                z4 = 1;
                break;
            case WEST:
                x1 = 0;
                y1 = 0;
                z1 = 0;

                x2 = 0;
                y2 = 1;
                z2 = 0;

                x3 = 0;
                y3 = 1;
                z3 = 1;

                x4 = 0;
                y4 = 0;
                z4 = 1;
                break;
        }

        if (!reversed) {

            myBuffer.begin(GL_QUADS, vf);

            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            myBuffer.pos(x1, y1, z1).tex(tex[3][0], tex[3][1]).color(color[0], color[1], color[2], 1f).endVertex();
            myBuffer.pos(x2, y2, z2).tex(tex[2][0], tex[2][1]).color(color[0], color[1], color[2], 1f).endVertex();
            myBuffer.pos(x3, y3, z3).tex(tex[1][0], tex[1][1]).color(color[0], color[1], color[2], 1f).endVertex();
            myBuffer.pos(x4, y4, z4).tex(tex[0][0], tex[0][1]).color(color[0], color[1], color[2], 1f).endVertex();
            GlStateManager.disableCull();
            GlStateManager.disableLighting();

            Tessellator.getInstance().draw();
        }
        else {

            myBuffer.begin(GL_QUADS, vf);

            myBuffer.pos(x1, y1, z1).color(color[0], color[1], color[2], color[3]).tex(tex[3][0], tex[3][1]).endVertex();
            myBuffer.pos(x2, y2, z2).color(color[0], color[1], color[2], color[3]).tex(tex[2][0], tex[2][1]).endVertex();
            myBuffer.pos(x3, y3, z3).color(color[0], color[1], color[2], color[3]).tex(tex[1][0], tex[1][1]).endVertex();
            myBuffer.pos(x4, y4, z4).color(color[0], color[1], color[2], color[3]).tex(tex[0][0], tex[0][1]).endVertex();

            Tessellator.getInstance().draw();
        }

    }
}
