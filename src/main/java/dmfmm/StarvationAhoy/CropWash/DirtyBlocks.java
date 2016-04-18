package dmfmm.StarvationAhoy.CropWash;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.*;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class DirtyBlocks {





    public static Map<Block,ArrayList<Item>> replace;

    public DirtyBlocks(){
        replace = new HashMap<>();

        addReplace(Blocks.carrots, Items.carrot);
        addReplace(Blocks.potatoes, Items.poisonous_potato, Items.potato);
        addReplace(Blocks.wheat, Items.wheat);
        addReplace(Blocks.beetroots, Items.beetroot);

    }

    public static void addReplace(Block block, Item... change){


        ArrayList<Item> user = new ArrayList<>();

        for (Item i :  Arrays.asList(change)){
            user.add(i);
        }

        replace.put(block, user);
    }

    public boolean isReplace(Block block){
        return replace.containsKey(block);
    }

    public ArrayList<Item> toReplace(Block block){
        return replace.get(block);
    }

    public Block getBlockFromDrop(Item item){
        Iterator<Block> set = replace.keySet().iterator();
        while(set.hasNext()){
            Block block = set.next();
            for(int z=0; z < toReplace(block).size(); z++){
                if(toReplace(block).get(z).equals(item)){
                    return block;
                }
            }
        }
        return null;
    }


}
