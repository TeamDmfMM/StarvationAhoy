package dmfmm.starvationahoy.meat.init;

import dmfmm.starvationahoy.base.ItemRegister;
import dmfmm.starvationahoy.core.lib.MeatLib;
import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.meat.item.*;
import jline.internal.Log;
import net.minecraft.item.Item;

import java.lang.reflect.Field;

/**
 * Created by dmf444 on 12/20/2017. Code originally written for StarvationAhoy.
 */
public class MeatItemRegistry {

    public static Item DEAD_COW = new DeadEntity(MeatLib.COW_DEAD);
    public static Item DEAD_PIG = new DeadEntity(MeatLib.PIG_DEAD);
    public static Item DEAD_CHICKEN = new DeadEntity(MeatLib.CHICKEN_DEAD);
    public static Item DEAD_RABBIT = new DeadEntity(MeatLib.RABBIT_DEAD);
    public static Item DEAD_SHEEP = new DeadEntity(MeatLib.SHEEP_DEAD);
    public static Item SKINLESS_COW = new SkinnedEntity(MeatLib.COW_SKINNED);
    public static Item SKINLESS_PIG = new SkinnedEntity(MeatLib.PIG_SKINNED);
    public static Item SKINLESS_CHICKEN = new SkinnedEntity(MeatLib.CHICKEN_SKINNED);
    public static Item SKINLESS_RABBIT = new SkinnedEntity(MeatLib.RABBIT_SKINNED);
    public static Item SKINLESS_SHEEP = new SkinnedEntity(MeatLib.SHEEP_SKINNED);
    public static Item BUTCHERS_KNIFE = new ButcherKnife(MeatLib.BUTCHER_KNIFE);
    public static Item FILET_KNIFE = new FiletKnife(MeatLib.FILET_KNIFE);
    public static Item PIG_LEG = new StanFood(5, 6.3F, MeatLib.PIG_LEGS);
    public static Item COOKED_PIG_LEG = new StanFood(8, 5.7F, MeatLib.COOKED_PIG_LEG);



    public static void registerItems() {
        if(ConfigHandler.useMeatOverride()) {
            for (Field field : MeatItemRegistry.class.getDeclaredFields()) {
                if (field.getType() == Item.class) {
                    try {
                        Item item = (Item) field.get(null);
                        ItemRegister.addItem(item, item.getRegistryName().getResourcePath());
                    } catch (IllegalAccessException e) {
                        Log.warn("Somehow failed to get a block from its registrator. WAT?");
                    }
                }
            }
        }
    }
}
