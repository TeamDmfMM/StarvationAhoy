package dmfmm.starvationahoy.base;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;

public class BlockRegister {

    private static HashMap<String, Block> blocks = new HashMap<>();


    public static void addBlock(Block block, String name) {
        blocks.put(name, block);
        ItemRegister.addItem(new NamedItemBlock(block, name), name);
    }

    public static void addBlockWithItem(Block block, String name, Item item) {
        blocks.put(name, block);
        ItemRegister.addItem(item, name);
    }

    public static Block getBlockByName(String name) {
        return blocks.get(name);
    }

    public static void registerBlocks() {
        for(String name : blocks.keySet()) {
            Block block = blocks.get(name);
            //block.setRegistryName(name);
            ForgeRegistries.BLOCKS.register(block);
        }
    }

}
