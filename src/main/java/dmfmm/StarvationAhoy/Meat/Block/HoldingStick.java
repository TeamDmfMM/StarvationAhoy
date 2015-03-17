package dmfmm.StarvationAhoy.Meat.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.MultiBlockChecking;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HoldingStick extends BlockContainer{

	protected HoldingStick() {
		super(Material.wood);
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
	    public boolean renderAsNormalBlock() {
	            return false;
	    }
	    
	    //This is the icon to use for showing the block in your hand.
	    public void registerBlockIcons(IIconRegister icon) {
	            //this.blockIcon = icon.registerIcon("extrafood:Zycrafted");
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

		public void changeRot(World world, int x, int y, int z, int rotation){
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		if (player.inventory.getCurrentItem().getItem() == Items.stick) return MultiBlockChecking.checkCookerStructure(world, x, y, z);
		else return false;
	}


	    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack Itemstack) {
			
	    	super.onBlockAdded(world, x, y, z);
	    	this.setDefaultDirection(world, x, y, z, entity);
	 
	    }
	    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	    {
	    	int meta = world.getBlockMetadata(x, y, z);
	   
	    	return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY +0.30F, (double)z + this.maxZ);
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	    {
	    		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY +0.30F, (double)z + this.maxZ);
	    }
}
