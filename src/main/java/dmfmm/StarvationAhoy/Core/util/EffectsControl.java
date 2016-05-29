package dmfmm.StarvationAhoy.Core.util;


import dmfmm.StarvationAhoy.Client.SAEntityDiggingFX;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectsControl {


    public static void addBlockDestroyEffects(World world, BlockPos pos, IBlockState state, ParticleManager effectRenderer, String iconName) {
        if (!state.getBlock().isAir(state, world, pos))
        {
            state = state.getActualState(world, pos);
            int i = 4;

            for (int j = 0; j < i; ++j)
            {
                for (int k = 0; k < i; ++k)
                {
                    for (int l = 0; l < i; ++l)
                    {
                        double d0 = (double)pos.getX() + ((double)j + 0.5D) / (double)i;
                        double d1 = (double)pos.getY() + ((double)k + 0.5D) / (double)i;
                        double d2 = (double)pos.getZ() + ((double)l + 0.5D) / (double)i;
                        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(iconName);
                        SAEntityDiggingFX dig = new SAEntityDiggingFX(world, d0, d1, d2, d0 - (double)pos.getX() - 0.5D, d1 - (double)pos.getY() - 0.5D, d2 - (double)pos.getZ() - 0.5D, state);
                        dig.setParticleTexture(sprite);
                        dig.setBlockPos(pos).multiplyVelocity(0.2f).multipleParticleScaleBy(0.6F);
                        effectRenderer.addEffect(dig);
                    }
                }
            }
        }
    }

}
