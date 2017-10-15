package dmfmm.starvationahoy.Meat;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;

/**
 * Created by dmf444 on 10/9/2017. Code originally written for StarvationAhoy.
 */
public class EnchantmentMeatSlayer extends Enchantment{

    public static final DamageSource AVOIDANCE = new DamageSource("meat_avoidance");

    public EnchantmentMeatSlayer(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    public void onEntityDamaged(EntityLivingBase user, Entity target, int level)
    {
        if(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)target).value) {
            target.attackEntityFrom(AVOIDANCE, 20.0f);
        }
    }
}
