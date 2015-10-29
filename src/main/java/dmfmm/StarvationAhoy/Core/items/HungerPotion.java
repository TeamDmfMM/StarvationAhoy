package dmfmm.StarvationAhoy.Core.items;

import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Core.lib.CoreLib;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by David on 2015-10-23.
 */
public class HungerPotion extends ItemFood {

    public HungerPotion()
    {
        super(1,0.01f, false);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setCreativeTab(SATabs.INSTANCE);
        this.setTextureName(ModInfo.MOD_ID +":"+ CoreLib.potion);
    }

   /* public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.canEat(true))
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }*/

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }
        //if (!world.isRemote) {
            player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - 5);
            //player.getFoodStats()

            if (!player.capabilities.isCreativeMode)
            {
                   player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
            }
       // }
        this.onFoodEaten(stack, world, player);
        return stack;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {return 32;}


}
