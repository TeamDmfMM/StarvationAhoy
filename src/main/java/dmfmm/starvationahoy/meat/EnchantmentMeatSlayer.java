package dmfmm.starvationahoy.meat;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by dmf444 on 10/9/2017. Code originally written for StarvationAhoy.
 */
public class EnchantmentMeatSlayer extends Enchantment{

    public EnchantmentMeatSlayer() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
    }

}
