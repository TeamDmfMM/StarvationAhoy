package dmfmm.starvationahoy.meat.block;

import dmfmm.starvationahoy.core.lib.MeatLib;
import dmfmm.starvationahoy.meat.MeatType;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.meat.block.multiblock.CookerMultiBlock;
import dmfmm.starvationahoy.meat.block.multiblock.CookerTileEntity;
import dmfmm.starvationahoy.meat.block.multiblock.TileEntityMultiBlock;
import dmfmm.starvationahoy.api.Event.MeatCutEvent;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

import java.util.Random;

/**
 * Created by Mm12 on 2/28/2015.
 */
public class Cooker extends Block implements ITileEntityProvider {
    public Cooker() {
        super(Material.ANVIL);
        this.setRegistryName(MeatLib.DEV_COOKER);
        this.setUnlocalizedName(MeatLib.DEV_COOKER);
        this.setHardness(2.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new CookerTileEntity(null);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (player.inventory.mainInventory.get(player.inventory.currentItem) == ItemStack.EMPTY){
            return false;
        }
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        //TODO CLEAN THIS CODE !!!!!!!!!!!!!
        if (player.inventory.mainInventory.get(player.inventory.currentItem).getItem() == MeatItemRegistry.BUTCHERS_KNIFE){
            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(new BlockPos(x, y, z));
            if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")){
                ItemStack toSpawnInWorld = new ItemStack(te.multiBlockStructure.sharedData.getCompoundTag("RoastingItem"));
                MinecraftForge.EVENT_BUS.post(new MeatCutEvent.SpitRoast(world, te.multiBlockStructure.sharedData.getInteger("EntityID"), new BlockPos(x, y, z), toSpawnInWorld.getItem().equals(Items.BONE), toSpawnInWorld.getItem()));
                te.multiBlockStructure.sharedData = new NBTTagCompound();
                EntityItem e = new EntityItem(world, x, y+2, z, toSpawnInWorld);
                if (!world.isRemote){world.spawnEntity(e);}
                /*(toSpawnInWorld.getItem().equals(Items.cooked_porkchop)){
                    e = new EntityItem(world, x, y+2, z, new ItemStack(MItemLoader.pigleg, 4));
                    if(!world.isRemote){world.spawnEntityInWorld(e);}
                }*/
                te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos,new BlockPos(te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z), world);
            }
        }
        if (ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory.get(player.inventory.currentItem)).value){
            MeatType t = ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory.get(player.inventory.currentItem)).meat;
            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(new BlockPos(x, y, z));
            if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")){ return false;}
            te.multiBlockStructure.sharedData.setTag("RoastingItem", player.inventory.mainInventory.get(player.inventory.currentItem).writeToNBT(new NBTTagCompound()));
            te.multiBlockStructure.sharedData.setTag("CookedItem", new ItemStack(t.items.meat, 1).writeToNBT(new NBTTagCompound()));
            te.multiBlockStructure.sharedData.setInteger("EntityID", t.id);
            te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos, new BlockPos(te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z), world);
            player.inventory.mainInventory.get(player.inventory.currentItem).shrink(1);
            //TODO: CHECK IF I HAVE TO HANDLE NULL STACKS
            //if (player.inventory.mainInventory[player.inventory.currentItem].stackSize < 1){
            //    player.inventory.mainInventory[player.inventory.currentItem] = null;
            //}
        }


        return false;
    }
    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World source, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        CookerTileEntity tile = (CookerTileEntity)source.getTileEntity(pos);
        if (tile != null) {
            if (tile.multiBlockStructure != null) {
                if (tile.multiBlockStructure.orient == 0) {
                    return new AxisAlignedBB((double) x,
                            (double) y + 1.16f,
                            (double) z + 0.4389,
                            (double) x + 1,
                            (double) y + 1 + 0.31f,
                            (double) z + 1 - 0.3989);
                } else {
                    return new AxisAlignedBB((double) x + 0.4389,
                            (double) y + 1.16f,
                            (double) z,
                            (double) x + 1 - 0.3989,
                            (double) y + 1 + 0.31f,
                            (double) z + 1).offset(-0.06, 0, 0);
                }
            }
        }
        return Block.FULL_BLOCK_AABB;
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return new AxisAlignedBB(0.0F, 0.6F, 0.0F, 1.0F, 1.0F, 1.0F).offset(0, 0.2, 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        CookerTileEntity tile = (CookerTileEntity)world.getTileEntity(pos);
        if (tile != null) {
            if (tile.multiBlockStructure != null) {
                if (tile.multiBlockStructure.orient == 0) {
                    return new AxisAlignedBB((double) 0,
                            (double) 0 + 1.16f,
                            (double) 0 + 0.4389,
                            (double)  1,
                            (double) 1 + 0.31f,
                            (double)  1 - 0.3989);
                } else {
                    return new AxisAlignedBB((double) 0 + 0.4389,
                            (double) 0 + 1.16f,
                            (double) 0,
                            (double) 1 - 0.3989,
                            (double)  1 + 0.31f,
                            (double)  1).offset(-0.06, 0, 0);
                }
            }
        }
        return Block.FULL_BLOCK_AABB;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityMultiBlock tileEntityMultiBlock = (TileEntityMultiBlock) worldIn.getTileEntity(pos);
        CookerMultiBlock multiBlock = (CookerMultiBlock) tileEntityMultiBlock.multiBlockStructure;
        multiBlock.destroy(worldIn);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }
}
