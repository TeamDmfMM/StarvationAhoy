package dmfmm.starvationahoy.meat.init;

import dmfmm.starvationahoy.meat.EnchantmentMeatSlayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by dmf444 on 10/14/2017. Code originally written for StarvationAhoy.
 */
@Mod.EventBusSubscriber
public class MeatEnchantRegistry {

    public static Enchantment meatKiller = new EnchantmentMeatSlayer(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event){
        meatKiller.setRegistryName(new ResourceLocation("starvationahoy", "meatkiller")).setName("meatslayer");
        event.getRegistry().register(meatKiller);

    }
}
