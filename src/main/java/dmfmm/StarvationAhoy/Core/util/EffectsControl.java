package dmfmm.StarvationAhoy.Core.util;


import dmfmm.StarvationAhoy.Client.SAEntityDiggingFX;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class EffectsControl {


    public static void addBlockDestroyEffects(World world, BlockPos pos, IBlockState state, net.minecraft.client.particle.EffectRenderer effectRenderer, String iconName) {
        if (!state.getBlock().isAir(world, pos))
        {
            state = state.getBlock().getActualState(state, world, pos);
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
                        dig.setParticleIcon(sprite);
                        dig.func_174846_a(pos).multiplyVelocity(0.2f).multipleParticleScaleBy(0.6F);
                        effectRenderer.addEffect(dig);
                    }
                }
            }
        }
    }

}
