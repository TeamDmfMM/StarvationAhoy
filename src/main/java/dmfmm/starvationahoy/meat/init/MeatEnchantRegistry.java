package dmfmm.starvationahoy.meat.init;

import dmfmm.starvationahoy.meat.EnchantmentMeatSlayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Created by dmf444 on 10/14/2017. Code originally written for StarvationAhoy.
 */

public class MeatEnchantRegistry {

    public static Enchantment meatKiller = new EnchantmentMeatSlayer();

    public static void registerEnchantments(){
        meatKiller.setRegistryName(new ResourceLocation("starvationahoy", "meatkiller")).setName("meatslayer");
        ForgeRegistries.ENCHANTMENTS.register(meatKiller);
    }
}
