package dmfmm.StarvationAhoy.Meat.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;

public class MeatHanger extends BlockContainer{

	private boolean hasAnimal = false;
	
	protected MeatHanger() {
		super(Material.iron);
		this.setCreativeTab(CreativeTabs.tabCombat);
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
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float PlayerXCOORD, float PlayerYCOORD, float PlayerZCOORD) {
    	SALog.error(((MeatHangerTileEntity) world.getTileEntity(x, y, z)).getMeatType());
        return false;
    	/*TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity == null || player.isSneaking()) {
                    return false;
            }
    //code to open gui explained later
    player.openGui(ExtraFood.instance, 0, world, x, y, z);
            return true;*/
    }
		
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack Itemstack) {
		
    	super.onBlockAdded(world, x, y, z);
    	this.setDefaultDirection(world, x, y, z, entity);
 
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	if(hasAnimal)
    		return null;
    	else	
    		if (meta == 2){
    			return AxisAlignedBB.getBoundingBox((double)((float)x ), (double)y + 0.5, (double)((float)z + 0.5), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 1)));   
    		} else if (meta == 3){
    			return AxisAlignedBB.getBoundingBox((double)((float)x ), (double)y + 0.5, (double)((float)z), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 0.5)));   
    		} else if (meta == 4){
    			return AxisAlignedBB.getBoundingBox((double)((float)x + 0.5), (double)y + 0.5, (double)((float)z ), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 1)));   
    		} else if (meta == 5){
    			return AxisAlignedBB.getBoundingBox((double)((float)x), (double)y + 0.5, (double)((float)z ), (double)((float)(x + 0.5) ), (double)((float)y + 1), (double)((float)(z + 1)));   
    		}
        return AxisAlignedBB.getBoundingBox((double)((float)x ), (double)y + 0.5, (double)((float)z + 0.5), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 1)));
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 2){
        return AxisAlignedBB.getBoundingBox((double)((float)x ), (double)y + 0.5, (double)((float)z + 0.5), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 1)));   
        } else if (meta == 3){
        	return AxisAlignedBB.getBoundingBox((double)((float)x ), (double)y + 0.5, (double)((float)z), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 0.5)));   
        } else if (meta == 4){
        	return AxisAlignedBB.getBoundingBox((double)((float)x + 0.5), (double)y + 0.5, (double)((float)z ), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 1)));   
        } else if (meta == 5){
        	return AxisAlignedBB.getBoundingBox((double)((float)x), (double)y + 0.5, (double)((float)z ), (double)((float)(x + 0.5) ), (double)((float)y + 1), (double)((float)(z + 1)));   
        }
        return AxisAlignedBB.getBoundingBox((double)((float)x ), (double)y + 0.5, (double)((float)z + 0.5), (double)((float)(x + 1) ), (double)((float)y + 1), (double)((float)(z + 1)));   
     }

}
