package dmfmm.StarvationAhoy.CropWash.Block;

import dmfmm.StarvationAhoy.Client.SAEntityDiggingFX;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Core.lib.WashLib;
import dmfmm.StarvationAhoy.Core.util.EffectsControl;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.CropWash.Block.tilentity.TileEntityCropWasher;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BlockCropWasher extends BlockContainer{



    public BlockCropWasher() {
        super(Material.wood);
        this.setCreativeTab(SATabs.INSTANCE);
        this.setUnlocalizedName(WashLib.washBarrelName);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCropWasher();
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        SALog.error(((TileEntityCropWasher) world.getTileEntity(pos)).getFluidAmount());
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack == null){
            return false;
        }
        else if (stack.getItem() == Items.water_bucket){
            ((TileEntityCropWasher) world.getTileEntity(pos)).fill(EnumFacing.UP, new FluidStack(FluidRegistry.getFluid("water"), 1000), true);
            ItemStack bucket = new ItemStack(Items.bucket);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, bucket);
            return true;
        }
        else if (stack.getItem() == ModuleCropWash.cropItemLoader.getItem("dirty_item")){
             player.inventory.setInventorySlotContents(player.inventory.currentItem, ((TileEntityCropWasher) world.getTileEntity(pos)).wash(player.getCurrentEquippedItem()));
            return true;
        }

        return false;
    }
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean isFullCube() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.EffectRenderer effectRenderer)
    {
        if(world.getBlockState(pos).getBlock().equals(this)) {
            EffectsControl.addBlockDestroyEffects(world, pos, world.getBlockState(pos), effectRenderer, "starvationahoy:blocks/WashBarrelItem");
            return true;
        }
        return false;
    }
}
