package dmfmm.StarvationAhoy.Core.items;

import dmfmm.StarvationAhoy.Core.SATabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;


public class HungerPotion extends ItemFood {

    public HungerPotion()
    {
        super(-8,0.00f, false);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setCreativeTab(SATabs.INSTANCE);
        //this.setTextureName(ModInfo.MOD_ID +":"+ CoreLib.potion);
    }

   /* public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.canEat(true))
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }*/


    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.DRINK;
    }
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {return 32;}


}
