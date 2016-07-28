package dmfmm.StarvationAhoy.btmstuff.blocks;

import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.btmstuff.te.AutoRoasterTE;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by TeamDMFMM on 7/23/2016. Code originally written
 * for StarvationAhoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class AutomaticRoaster extends Block implements ITileEntityProvider{

    public AutomaticRoaster() {
        super(Material.ANVIL);
        this.setRegistryName("autoroaster");
        this.setCreativeTab(SATabs.INSTANCE);

        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new AutoRoasterTE();
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (player.inventory.mainInventory[player.inventory.currentItem] == null) {return false;}

        if (ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory[player.inventory.currentItem]).value) {
            if(world.getTileEntity(pos) instanceof AutoRoasterTE){
                ((AutoRoasterTE)world.getTileEntity(pos)).setEntity(player.inventory.mainInventory[player.inventory.currentItem]);
                if(!world.isRemote) {
                    player.addChatComponentMessage(new TextComponentString("Added " + player.inventory.mainInventory[player.inventory.currentItem].getItem().getUnlocalizedName() + " as Roasting Item"));
                }
                return true;
            }
        }
        return false;
    }
}
