package dmfmm.StarvationAhoy.Meat.Block;

import dmfmm.StarvationAhoy.Meat.Block.multiblock.CookerTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Mm12 on 2/28/2015.
 */
public class Cooker extends BlockContainer {
    protected Cooker() {
        super(Material.anvil);
        //this.setCreativeTab(SATabs.INSTANCE);
       // this.setBlockBounds(0.0F, 0.6F, 0.0F, 1.0F, 1.0F, 1.0F);

        //this.setBlockTextureName("starvationahoy:clearBlock");


    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new CookerTileEntity(null);
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (player.inventory.mainInventory[player.inventory.currentItem] == null){
            return false;
        }
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        //TODO CLEAN THIS CODE !!!!!!!!!!!!!
        if (player.inventory.mainInventory[player.inventory.currentItem].getItem() == MItemLoader.ButcherKnife){
            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(new BlockPos(x, y, z));
            if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")){
                ItemStack toSpawnInWorld = ItemStack.loadItemStackFromNBT(te.multiBlockStructure.sharedData.getCompoundTag("RoastingItem"));
                MinecraftForge.EVENT_BUS.post(new MeatCutEvent.SpitRoast(world, te.multiBlockStructure.sharedData.getInteger("EntityID"), new BlockPos(x, y, z), toSpawnInWorld.getItem().equals(Items.bone), toSpawnInWorld.getItem()));
                te.multiBlockStructure.sharedData = new NBTTagCompound();
                EntityItem e = new EntityItem(world, x, y+2, z, toSpawnInWorld);
                if (!world.isRemote){world.spawnEntityInWorld(e);}
                /*(toSpawnInWorld.getItem().equals(Items.cooked_porkchop)){
                    e = new EntityItem(world, x, y+2, z, new ItemStack(MItemLoader.pigleg, 4));
                    if(!world.isRemote){world.spawnEntityInWorld(e);}
                }*/
                te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos,new BlockPos(te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z), world);
            }
        }
        if (ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory[player.inventory.currentItem]).value){
            MeatType t = ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory[player.inventory.currentItem]).meat;
            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(new BlockPos(x, y, z));
            if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")){ return false;}
            te.multiBlockStructure.sharedData.setTag("RoastingItem", player.inventory.mainInventory[player.inventory.currentItem].writeToNBT(new NBTTagCompound()));
            te.multiBlockStructure.sharedData.setTag("CookedItem", new ItemStack(t.items.meat, 1).writeToNBT(new NBTTagCompound()));
            te.multiBlockStructure.sharedData.setInteger("EntityID", t.id);
            te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos, new BlockPos(te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z), world);
            player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
            if (player.inventory.mainInventory[player.inventory.currentItem].stackSize < 1){
                player.inventory.mainInventory[player.inventory.currentItem] = null;
            }
        }


        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        CookerTileEntity tile = (CookerTileEntity)source.getTileEntity(pos);
        if(tile.multiBlockStructure.orient == 0) {
            return new AxisAlignedBB((double) x,
                    (double) y + 1.16f,
                    (double) z + 0.4389,
                    (double) x + 1,
                    (double) y + 1 + 0.31f,
                    (double) z + 1 - 0.3989);
        }else{
            return new AxisAlignedBB((double) x + 0.4389,
                    (double) y + 1.16f,
                    (double) z  ,
                    (double) x + 1 - 0.3989,
                    (double) y + 1 + 0.31f,
                    (double) z + 1);
        }
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        CookerTileEntity tile = (CookerTileEntity)world.getTileEntity(pos);
        if(tile.multiBlockStructure.orient == 0) {
            return new AxisAlignedBB((double) x,
                    (double) y + 1.16f,
                    (double) z + 0.4389,
                    (double) x + 1,
                    (double) y + 1 + 0.31f,
                    (double) z + 1 - 0.3989);
        }else{
            return new AxisAlignedBB((double) x + 0.4389,
                    (double) y + 1.16f,
                    (double) z  ,
                    (double) x + 1 - 0.3989,
                    (double) y + 1 + 0.31f,
                    (double) z + 1);
        }
    }
    
    public boolean isOpaqueCube(){
        return false;
    }

    public boolean isFullCube(){
        return false;
    }
}
