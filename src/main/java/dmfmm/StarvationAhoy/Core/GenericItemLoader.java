package dmfmm.StarvationAhoy.Core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class GenericItemLoader {


    public Map<String, Item> items = new HashMap<>();

    public Item getItem(String name){
        return items.get(name);
    }

    public void addItem(String name, Class<? extends Item> item){
        try {
            items.put(name, item.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void load(){


        for (String s : items.keySet()) {
            GameRegistry.registerItem(items.get(s), s);
        }

    }


}
