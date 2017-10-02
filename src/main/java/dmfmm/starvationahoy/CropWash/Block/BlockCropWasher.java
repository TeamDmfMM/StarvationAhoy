package dmfmm.starvationahoy.CropWash.Block;

import dmfmm.starvationahoy.Core.SATabs;
import dmfmm.starvationahoy.Core.lib.WashLib;
import dmfmm.starvationahoy.Core.util.EffectsControl;
import dmfmm.starvationahoy.CropWash.Block.tilentity.TileEntityCropWasher;
import dmfmm.starvationahoy.CropWash.ModuleCropWash;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BlockCropWasher extends Block implements ITileEntityProvider{



    public BlockCropWasher() {
        super(Material.WOOD);
        this.setCreativeTab(SATabs.INSTANCE);
        this.setUnlocalizedName(WashLib.washBarrelName);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCropWasher();
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        //SALog.error(((TileEntityCropWasher) world.getTileEntity(pos)).getFluidAmount());
        ItemStack stack = player.getHeldItemMainhand();
        if (stack == ItemStack.EMPTY){
            return false;
        }
        else if (stack.getItem() == Items.WATER_BUCKET){
            ((TileEntityCropWasher) world.getTileEntity(pos)).fill();
            ItemStack bucket = new ItemStack(Items.BUCKET);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, bucket);
            return true;
        }
        else if (stack.getItem() == ModuleCropWash.cropItemLoader.getItem("dirty_item")){
             player.inventory.setInventorySlotContents(player.inventory.currentItem, ((TileEntityCropWasher) world.getTileEntity(pos)).wash(player.getHeldItemMainhand()));
            return true;
        }

        return false;
    }
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager effectRenderer)
    {
        if(world.getBlockState(pos).getBlock().equals(this)) {
            EffectsControl.addBlockDestroyEffects(world, pos, world.getBlockState(pos), effectRenderer, "starvationahoy:blocks/WashBarrelItem");
            return true;
        }
        return false;
    }
}
