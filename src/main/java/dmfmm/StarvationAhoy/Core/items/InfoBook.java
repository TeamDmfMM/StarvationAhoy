package dmfmm.StarvationAhoy.Core.items;


import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Core.lib.CoreLib;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfoBook extends Item {

    public InfoBook(){
        this.setCreativeTab(SATabs.INSTANCE);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand){
        player.openGui(StarvationAhoy.instance, CoreLib.bookGUIID, world, 0, 0, 0);
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return false;
    }
}
