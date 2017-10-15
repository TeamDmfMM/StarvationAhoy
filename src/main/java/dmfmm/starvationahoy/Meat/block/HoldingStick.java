package dmfmm.starvationahoy.Meat.block;

import dmfmm.starvationahoy.Core.Blocks.BlockContainerRotate;
import dmfmm.starvationahoy.Core.SATabs;
import dmfmm.starvationahoy.Core.lib.MeatLib;
import dmfmm.starvationahoy.Meat.block.multiblock.MultiBlockChecking;
import dmfmm.starvationahoy.Meat.block.multiblock.TileEntityMultiBlock;
import dmfmm.starvationahoy.Meat.block.tileentity.HoldingStickTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HoldingStick extends BlockContainerRotate{

	public HoldingStick() {
		super(Material.WOOD);
		this.setCreativeTab(SATabs.INSTANCE);
		this.setName(MeatLib.HOLDING_STICK);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p) {
		return new HoldingStickTileEntity(null);
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

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(player.inventory.getCurrentItem() != ItemStack.EMPTY) {
			if (player.inventory.getCurrentItem().getItem() == Items.STICK) {
				boolean s = MultiBlockChecking.checkCookerStructure(world, pos.getX(), pos.getY(), pos.getZ());
				if (s) {
					player.inventory.mainInventory.get(player.inventory.currentItem).shrink(1);
					//TODO: CHECK IF I HAVE TO HANDLE ITEM SHRINKS
					//if (player.inventory.mainInventory.get(player.inventory.currentItem].stackSize < 1) {
					//	player.inventory.mainInventory.get(player.inventory.currentItem] = null;

					//}
					return true;
				}
				return false;


			} else return false;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return new AxisAlignedBB(0, 0, 0, 1, 1.30F, 1);
	}


	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
        if(world.getTileEntity(pos) instanceof TileEntityMultiBlock){
            TileEntityMultiBlock tile = (TileEntityMultiBlock) world.getTileEntity(pos);
            if(tile.multiBlockStructure != null) {
                tile.multiBlockStructure.destroy(world);
            }
        }
    }
}
