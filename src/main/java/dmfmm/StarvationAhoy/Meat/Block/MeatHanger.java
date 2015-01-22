package dmfmm.StarvationAhoy.Meat.Block;

import dmfmm.StarvationAhoy.Meat.MeatRegistry;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;

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
    	int ItemType = ((MeatHangerTileEntity) world.getTileEntity(x, y, z)).getMeatType();
    	int state = ((MeatHangerTileEntity) world.getTileEntity(x, y, z)).getMeatState();
    	if(player.inventory.getCurrentItem().getItem() == MItemLoader.ButcherKnife && ItemType != 0 && state == 1){
			/*IS the player attempting to cut the animal down (when skinned)?*/
    		Item item = player.inventory.getCurrentItem().getItem();
    		hasAnimal = false;

			item = ModuleMeat.registry.getMeatTypeForId(ItemType).items.meat;
			if(!world.isRemote) world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(item, MathHelper.getRandomIntegerInRange(world.rand, 1, 3))));

			((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(0);
			((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatType(0);

			world.markBlockForUpdate(x, y, z);
    		return true;
    	}else if(player.inventory.getCurrentItem().getItem() == MItemLoader.filetKnife && state == 0){
    										/*IS the player Attemping to skin the animal?*/
    		
    		int randomNum = world.rand.nextInt((2 - 0) + 1) + 0;

			if (ModuleMeat.registry.getMeatTypeForId(ItemType).items.skin != null) {

				Item item = ModuleMeat.registry.getMeatTypeForId(ItemType).items.skin;

				if(!world.isRemote) world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(item, randomNum)));

			}

    		//Set to skinned state
    		((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(1);
    		world.markBlockForUpdate(x, y, z);
    		return true;
    	}else if(ModuleMeat.registry.isMeatItem(player.inventory.getCurrentItem()).value){
    							/*IS the player attempting to add a dead animal to the hooks?*/


			int id = ModuleMeat.registry.isMeatItem(player.inventory.getCurrentItem()).meatID;
			((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatState(0);
			((MeatHangerTileEntity) world.getTileEntity(x, y, z)).setMeatType(id);


    		
    	}
    	return false;
    	//SALog.error(((MeatHangerTileEntity) world.getTileEntity(x, y, z)).getMeatType());
        //return false;
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
    		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
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
    	if(hasAnimal)
    		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    	else{
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
}
