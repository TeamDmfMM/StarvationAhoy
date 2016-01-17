package dmfmm.StarvationAhoy.Meat.Block;

import dmfmm.StarvationAhoy.Core.Blocks.BlockContainerRotate;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.MultiBlockChecking;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HoldingStick extends BlockContainerRotate{

	protected HoldingStick() {
		super(Material.wood);
		this.setCreativeTab(SATabs.INSTANCE);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p) {
		return new HoldingStickTileEntity(null);
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
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState statez, EntityPlayer player, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.inventory.getCurrentItem() != null) {
			if (player.inventory.getCurrentItem().getItem() == Items.stick) {
				boolean s = MultiBlockChecking.checkCookerStructure(world, pos.getX(), pos.getY(), pos.getZ());
				if (s) {
					player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
					if (player.inventory.mainInventory[player.inventory.currentItem].stackSize < 1) {
						player.inventory.mainInventory[player.inventory.currentItem] = null;

					}
					return true;
				}
				return false;


			} else return false;
		}
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		return new AxisAlignedBB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY +0.30F, (double)z + this.maxZ);
	}
	    
	    @SideOnly(Side.CLIENT)
	    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
	    		return new AxisAlignedBB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY +0.30F, (double)z + this.maxZ);
	    }

    public void breakBlock(World world, BlockPos pos, IBlockState state){
        if(world.getTileEntity(pos) instanceof TileEntityMultiBlock){
            TileEntityMultiBlock tile = (TileEntityMultiBlock) world.getTileEntity(pos);
            if(tile.multiBlockStructure != null) {
                tile.multiBlockStructure.destroy(world);
            }
        }
    }
}
