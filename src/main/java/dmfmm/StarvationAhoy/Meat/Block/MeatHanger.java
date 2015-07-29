package dmfmm.StarvationAhoy.Meat.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class MeatHanger extends BlockContainer{

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
    public boolean renderAsNormalBlock() {
            return false;
    }
    
    //This is the icon to use for showing the block in your hand.
    public void registerBlockIcons(IIconRegister icon) {
            this.blockIcon = icon.registerIcon("starvationahoy:MeatHookItem");
    }
    
    private void setDefaultDirection(World world, int x, int y, int z, EntityLivingBase entity) {
    	int rotation = MathHelper.floor_double((double)(entity.rotationYaw * 4F / 360F) + 0.5D) & 3;

		if(rotation == 0) {
		world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if(rotation == 1) {
		world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if(rotation == 2) {
		world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if(rotation == 3) {
		world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
	}
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float PlayerXCOORD, float PlayerYCOORD, float PlayerZCOORD) {
    	int ItemType = ((MeatHangerTileEntity) world.getTileEntity(x, y, z)).getMeatType();
    	int state = ((MeatHangerTileEntity) world.getTileEntity(x, y, z)).getMeatState();
		ItemStack temma = player.inventory.getCurrentItem();
		if(temma != null) {
			if (temma.getItem() == MItemLoader.ButcherKnife && ItemType != 0 && state == 1){
    						/*IS the player attempting to cut the animal down (when skinned)?*/
				MinecraftForge.EVENT_BUS.post(new MeatCutEvent.MeatHanger(world, ItemType, x, y, z));
				//hasAnimal = false;
				((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatType(0);
				((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(0);
				world.markBlockForUpdate(x, y, z);
				return true;
			}else if (temma.getItem() == MItemLoader.filetKnife && state == 0) {
    						/*IS the player Attemping to skin the animal?*/
				MinecraftForge.EVENT_BUS.post(new MeatCutEvent.MeatSkinned(world, ItemType, x, y, z));
				//Set to skinned state
				((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(1);
				world.markBlockForUpdate(x, y, z);
				return true;
			} else if (player.inventory.getCurrentItem().getItem() == MItemLoader.deadChicken || player.inventory.getCurrentItem().getItem() == MItemLoader.deadCow || player.inventory.getCurrentItem().getItem() == MItemLoader.deadPig && ItemType == 0) {
    						/*IS the player attempting to add a dead animal to the hooks?*/

				Item item = player.inventory.getCurrentItem().getItem();
				//hasAnimal = true;
				if (item == MItemLoader.deadCow) {
					--player.inventory.getCurrentItem().stackSize;
					((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatType(1);
					((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(0);
					world.markBlockForUpdate(x, y, z);
					return true;
				} else if (item == MItemLoader.deadPig) {
					--player.inventory.getCurrentItem().stackSize;
					((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatType(2);
					((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(0);
					world.markBlockForUpdate(x, y, z);
					return true;
				} else if (item == MItemLoader.deadChicken) {
					--player.inventory.getCurrentItem().stackSize;
					((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatType(3);
					((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(0);
					world.markBlockForUpdate(x, y, z);
					return true;
				}

			}
			return false;
		}
		return false;
    }
		
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack Itemstack) {
		
    	super.onBlockAdded(world, x, y, z);
    	this.setDefaultDirection(world, x, y, z, entity);
 
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
		return this.defaultABB(world, x, y, z);
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
		return this.defaultABB(world, x, y, z);
	}

	private AxisAlignedBB defaultABB(World world, int x, int y, int z){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof MeatHangerTileEntity) {
			int Meat = ((MeatHangerTileEntity) tile).getMeatType();
			if (Meat == ModuleMeat.MEATTYPE_COW) {
				//FIXME: rendering is off
				return AxisAlignedBB.getBoundingBox((double) x + this.minX, (double) y + this.minY - 1.6F, (double) z + this.minZ, (double) x + this.maxX, (double) y + this.maxY, (double) z + this.maxZ);
			} else if (Meat == ModuleMeat.MEATTYPE_PIG) {
				return AxisAlignedBB.getBoundingBox((double) x + this.minX, (double) y + this.minY - 1.2F, (double) z + this.minZ, (double) x + this.maxX, (double) y + this.maxY, (double) z + this.maxZ);
			} else if (Meat == ModuleMeat.MEATTYPE_CHICK) {
				return AxisAlignedBB.getBoundingBox((double) x + this.minX, (double) y + this.minY - 0.3F, (double) z + this.minZ, (double) x + this.maxX, (double) y + this.maxY, (double) z + this.maxZ);
			} else {
				return this.defaultRender(world.getBlockMetadata(x, y, z), x, y, z);
			}
		}
		return this.defaultRender(world.getBlockMetadata(x, y, z), x, y, z);
	}
	private AxisAlignedBB defaultRender(int meta, int x, int y, int z){
		if (meta == 2) {
			return AxisAlignedBB.getBoundingBox((double) ((float) x), (double) y + 0.5, (double) ((float) z + 0.5), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 1)));
		} else if (meta == 3) {
			return AxisAlignedBB.getBoundingBox((double) ((float) x), (double) y + 0.5, (double) ((float) z), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 0.5)));
		} else if (meta == 4) {
			return AxisAlignedBB.getBoundingBox((double) ((float) x + 0.5), (double) y + 0.5, (double) ((float) z), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 1)));
		} else if (meta == 5) {
			return AxisAlignedBB.getBoundingBox((double) ((float) x), (double) y + 0.5, (double) ((float) z), (double) ((float) (x + 0.5)), (double) ((float) y + 1), (double) ((float) (z + 1)));
		}
	return AxisAlignedBB.getBoundingBox((double) ((float) x), (double) y + 0.5, (double) ((float) z + 0.5), (double) ((float) (x + 1)), (double) ((float) y + 1), (double) ((float) (z + 1)));
	}
}
