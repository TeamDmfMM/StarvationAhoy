package dmfmm.starvationahoy.core.items;


import dmfmm.starvationahoy.core.SATabs;
import dmfmm.starvationahoy.core.lib.CoreLib;
import dmfmm.starvationahoy.StarvationAhoy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfoBook extends Item {

    public InfoBook(){
        this.setCreativeTab(SATabs.INSTANCE);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        player.openGui(StarvationAhoy.instance, CoreLib.bookGUIID, world, 0, 0, 0);
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return false;
    }
}
