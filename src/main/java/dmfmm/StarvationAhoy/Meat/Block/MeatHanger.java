package dmfmm.StarvationAhoy.Meat.Block;

import dmfmm.StarvationAhoy.Core.Blocks.BlockContainerRotate;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.api.Meat.ISAModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class MeatHanger extends BlockContainerRotate{

	//private boolean hasAnimal = false;
	
	protected MeatHanger() {
		super(Material.iron);
		this.setCreativeTab(SATabs.INSTANCE);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new MeatHangerTileEntity();
	}
    //You don't want the normal render type, or it wont render properly.
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState statet, EntityPlayer player, EnumFacing side, float PlayerXCOORD, float PlayerYCOORD, float PlayerZCOORD) {
    	int ItemType = ((MeatHangerTileEntity) world.getTileEntity(pos)).getMeatType();
    	MeatHangerTileEntity.MeatStates state = ((MeatHangerTileEntity) world.getTileEntity(pos)).getMeatState();
		ItemStack temma = player.inventory.getCurrentItem();
		if(temma != null) {
									/*IS the player attempting to cut the animal down (when skinned)?*/
			if (temma.getItem() == MItemLoader.ButcherKnife && ItemType != 0 && state == MeatHangerTileEntity.MeatStates.SKINNED){

				boolean proceed = MinecraftForge.EVENT_BUS.post(new MeatCutEvent.MeatHanger(world, ItemType, pos));
				if(proceed) {
					Item drop = ModuleMeat.registry.getMeatTypeForId(((MeatHangerTileEntity) world.getTileEntity(pos)).getMeatType()).items.skinned;
					if (!world.isRemote) {
						world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(drop, 1)));
					}
					((MeatHangerTileEntity) world.getTileEntity(pos)).setMeatType(0);
					((MeatHangerTileEntity) world.getTileEntity(pos)).setMeatState(MeatHangerTileEntity.MeatStates.NORMAL);
					world.markBlockForUpdate(pos);
				}
				return true;
										/*IS the player Attemping to skin the animal?*/
			}else if (temma.getItem() == MItemLoader.filetKnife && state == MeatHangerTileEntity.MeatStates.NORMAL) {

				boolean progress = MinecraftForge.EVENT_BUS.post(new MeatCutEvent.MeatSkinned(world, ItemType, pos));
				if(progress){
					Item stack = ModuleMeat.registry.getMeatTypeForId(((MeatHangerTileEntity) world.getTileEntity(pos)).getMeatType()).items.skin;
					int randomNum = world.rand.nextInt((5 - 0) + 1) + 0;
					if (!world.isRemote) {world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(stack, randomNum)));}
				}
				((MeatHangerTileEntity) world.getTileEntity(pos)).setMeatState(MeatHangerTileEntity.MeatStates.SKINNED);
				world.markBlockForUpdate(pos);
				return true;
									/*IS the player attempting to add a dead animal to the hooks?*/
			} else if (ModuleMeat.registry.isMeatItem(temma).value && ItemType == 0) {

				--player.inventory.getCurrentItem().stackSize;
				((MeatHangerTileEntity) world.getTileEntity(pos)).setMeatType(ModuleMeat.registry.isMeatItem(temma).meatID);
				((MeatHangerTileEntity) world.getTileEntity(pos)).setMeatState(MeatHangerTileEntity.MeatStates.NORMAL);
				world.markBlockForUpdate(pos);
				return true;
			}
			return false;
		}
		return false;
    }

    
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
		return this.defaultABB(world, pos);
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
    {
		return this.defaultABB(world, pos);
	}

	private AxisAlignedBB defaultABB(World world, BlockPos pos){
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		TileEntity tile = world.getTileEntity(pos);
		if(tile instanceof MeatHangerTileEntity) {
			int Meat = ((MeatHangerTileEntity) tile).getMeatType();
			if(Meat > 0 && ModuleMeat.registry.getModel(Meat) instanceof ISAModel){
				return ((ISAModel)ModuleMeat.registry.getModel(Meat)).getMeatAABB(x, minX, maxX, y, minY, maxY, z, minZ, maxZ) != null ? ((ISAModel)ModuleMeat.registry.getModel(Meat)).getMeatAABB(x, minX, maxX, y, minY, maxY, z, minZ, maxZ) : this.defaultRender(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)), x, y, z);
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
