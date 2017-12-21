package dmfmm.starvationahoy.base;


import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;

public class ItemRegister {

    private static HashMap<String, Item> items = new HashMap<>();


    public static void addItem(Item item, String name) {
        items.put(name, item);
    }

    public static Item getItemByName(String name) {
        return items.get(name);
    }

    public static void registerItems() {
        for(String name : items.keySet()) {
            Item item = items.get(name);
            //item.setRegistryName(name);
            ForgeRegistries.ITEMS.register(item);
        }
    }

}
