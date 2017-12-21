package dmfmm.starvationahoy.meat.block;

import dmfmm.starvationahoy.core.blocks.BlockContainerRotate;
import dmfmm.starvationahoy.core.SATabs;
import dmfmm.starvationahoy.core.lib.MeatLib;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerData;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerTileEntity;
import dmfmm.starvationahoy.api.Event.MeatCutEvent;
import dmfmm.starvationahoy.api.Meat.ISAModel;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MeatHanger extends BlockContainerRotate{

	public MeatHanger() {
		super(Material.IRON);
		this.setCreativeTab(SATabs.INSTANCE);
		this.setName(MeatLib.MEAT_HANGER);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new MeatHangerTileEntity();
	}
    //You don't want the normal render type, or it wont render properly.
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
	public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor)
	{
		if(worldIn instanceof World) {
			IBlockState state = worldIn.getBlockState(pos);
			int rotation = this.getMetaFromState(state);
			//Facing (0-5). The order is D-U-N-S-W-E
			BlockPos p = pos.offset(EnumFacing.VALUES[rotation].getOpposite());
			if (worldIn.getBlockState(p).getBlock().isAir(state, worldIn, p)) {
				this.breakBlock((World)worldIn, pos, state);
				((World)worldIn).setBlockToAir(pos);
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		if(world.getTileEntity(pos) instanceof MeatHangerTileEntity){
			MeatHangerTileEntity tile = (MeatHangerTileEntity)world.getTileEntity(pos);
			if(tile.getMeatType() > 0) {
				if (tile.getMeatState() == MeatHangerData.MeatStates.NORMAL) {
					spawnAsEntity(world, pos, new ItemStack(ModuleMeat.registry.getMeatTypeForId(tile.getMeatType()).items.dead));
				} else if (tile.getMeatState() == MeatHangerData.MeatStates.SKINNED) {
					spawnAsEntity(world, pos, new ItemStack(ModuleMeat.registry.getMeatTypeForId(tile.getMeatType()).items.skinned));
				}
			}
		}
	}


	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		MeatHangerTileEntity tile = (MeatHangerTileEntity) world.getTileEntity(pos);
    	int meatType = tile.getMeatType();
    	MeatHangerData.MeatStates meatState = tile.getMeatState();
		ItemStack temma = player.inventory.getCurrentItem();
		if(temma != ItemStack.EMPTY) {
									/*IS the player attempting to cut the animal down (when skinned)?*/
			if (temma.getItem() == MeatItemRegistry.BUTCHERS_KNIFE && meatType > 0 && meatState == MeatHangerData.MeatStates.SKINNED){

				boolean proceed = MinecraftForge.EVENT_BUS.post(new MeatCutEvent.MeatHanger(world, meatType, pos));
				if(!proceed) {
					Item drop = ModuleMeat.registry.getMeatTypeForId(tile.getMeatType()).items.skinned;
					if (!world.isRemote)
						world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(drop, 1)));
					tile.updateHanger(0, MeatHangerData.MeatStates.EMPTY);
					world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
				}
				return true;
										/*IS the player Attemping to skin the animal?*/
			}else if (temma.getItem() == MeatItemRegistry.FILET_KNIFE && meatState == MeatHangerData.MeatStates.NORMAL) {

				boolean progress = MinecraftForge.EVENT_BUS.post(new MeatCutEvent.MeatSkinned(world, meatType, pos));
				if(!progress){
					Item stack = ModuleMeat.registry.getMeatTypeForId(tile.getMeatType()).items.skin;
					if(stack != null) {
						int randomNum = world.rand.nextInt((5) + 1);
						if (!world.isRemote) {world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(stack, randomNum)));}
					}
				}
				tile.setMeatState(MeatHangerData.MeatStates.SKINNED);
				world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
				return true;
									/*IS the player attempting to add a dead animal to the hooks?*/
			} else if (ModuleMeat.registry.isMeatItem(temma).value && meatType == 0) {

				tile.updateHanger(ModuleMeat.registry.isMeatItem(temma).meatID, MeatHangerData.MeatStates.NORMAL);
				world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
				player.inventory.getCurrentItem().shrink(1);
				return true;
			}
			return false;
		}
		return false;
    }

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return super.getCollisionBoundingBox(state, world, pos);
    }

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {return this.defaultABB(world, pos);}

	@SideOnly(Side.CLIENT)
	private AxisAlignedBB defaultABB(World world, BlockPos pos){
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		TileEntity tile = world.getTileEntity(pos);
		if(tile instanceof MeatHangerTileEntity) {
			int Meat = ((MeatHangerTileEntity) tile).getMeatType();
			if(Meat > 0 && ModuleMeat.registry.getModel(Meat) instanceof ISAModel){
				//TODO: CHANGE API TO USE AABB w/out GIVING 1 & 0
				return ((ISAModel)ModuleMeat.registry.getModel(Meat)).getMeatAABB(x, 0, 1, y, 0, 1, z, 0, 1) != null ? ((ISAModel)ModuleMeat.registry.getModel(Meat)).getMeatAABB(x, 0, 1, y,0, 1, z, 0, 1) : this.defaultRender(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)), x, y, z);
			}else{
				return this.defaultRender(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)), x, y, z);
			}
		}
		return this.defaultRender(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)), x, y, z);
	}
	private AxisAlignedBB defaultRender(int meta, int x, int y, int z){
		if (meta == 2) {
			return new AxisAlignedBB((double) ((float) x), (double) y + 0.5, (double) ((float) z + 0.5), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 1)));
		} else if (meta == 3) {
			return new AxisAlignedBB((double) ((float) x), (double) y + 0.5, (double) ((float) z), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 0.5)));
		} else if (meta == 4) {
			return new AxisAlignedBB((double) ((float) x + 0.5), (double) y + 0.5, (double) ((float) z), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 1)));
		} else if (meta == 5) {
			return new AxisAlignedBB((double) ((float) x), (double) y + 0.5, (double) ((float) z), (double) ((float) (x + 0.5)), (double) ((float) y + 1), (double) ((float) (z + 1)));
		}
	return new AxisAlignedBB((double) ((float) x), (double) y + 0.5, (double) ((float) z + 0.5), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 1)));
	}
}
