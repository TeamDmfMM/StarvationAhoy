package dmfmm.starvationahoy.core.items;

import dmfmm.starvationahoy.core.SATabs;
import dmfmm.starvationahoy.core.lib.CoreLib;
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
        this.setUnlocalizedName(CoreLib.HUNGER_POTION);
        this.setRegistryName(CoreLib.HUNGER_POTION);
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.DRINK;
    }
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {return 32;}


}
