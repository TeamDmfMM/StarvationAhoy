package dmfmm.starvationahoy.crops.init;


import dmfmm.starvationahoy.base.ItemRegister;
import dmfmm.starvationahoy.core.util.ConfigHandler;
import dmfmm.starvationahoy.crops.item.DirtyItem;
import jline.internal.Log;
import net.minecraft.item.Item;

import java.lang.reflect.Field;


public class CropItemRegistry {

    public static Item DIRTY_ITEM = new DirtyItem();


    public static void registerItems() {
        if(ConfigHandler.useCropwash()) {
            for (Field field : CropItemRegistry.class.getDeclaredFields()) {
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
