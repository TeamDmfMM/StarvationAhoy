package dmfmm.StarvationAhoy.Core.items;


import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Core.lib.CoreLib;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfoBook extends Item {

    public InfoBook(){
        this.setCreativeTab(SATabs.INSTANCE);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.openGui(StarvationAhoy.instance, CoreLib.bookGUIID, world, 0, 0, 0);
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return false;
    }
}
