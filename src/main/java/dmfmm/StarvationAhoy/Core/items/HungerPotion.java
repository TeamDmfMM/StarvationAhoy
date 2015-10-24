package dmfmm.StarvationAhoy.Core.items;

import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Core.lib.CoreLib;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by David on 2015-10-23.
 */
public class HungerPotion extends Item {

    public HungerPotion()
    {
        //super(0,0, false);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setCreativeTab(SATabs.INSTANCE);
        this.setTextureName(ModInfo.MOD_ID +":"+ CoreLib.potion);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.canEat(true))
        {
            player.setItemInUse(stack, 32);
        }

        return stack;
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }
        SALog.error("TRYING");
        player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - 4);
        SALog.error("DONE?");
        if (!player.capabilities.isCreativeMode)
        {
            if (stack.stackSize <= 0)
            {
                return new ItemStack(Items.glass_bottle);
            }

            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return stack;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }


}
