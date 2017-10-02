package dmfmm.starvationahoy.Client.Gui.book_gui;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;

import java.util.*;


/**
 * Created by dmf444 on 3/6/2016. Code originally written
 * for StarvationAhoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class FurnaceHelper {

    public static HashSet<Block> efuels;
    public static ArrayList<FuelPair> afuels;
    private static LinkedList<ItemStack> iteml = new LinkedList<ItemStack>();

    private static Set<Item> excludedFuels() {
        Set<Item> efuels = new HashSet<Item>();
        efuels.add(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM));
        efuels.add(Item.getItemFromBlock(Blocks.RED_MUSHROOM));
        efuels.add(Item.getItemFromBlock(Blocks.STANDING_SIGN));
        efuels.add(Item.getItemFromBlock(Blocks.WALL_SIGN));
        efuels.add(Item.getItemFromBlock(Blocks.TRAPPED_CHEST));
        return efuels;
    }

    public static class FuelPair
    {
        public FuelPair(ItemStack ingred, int burnTime) {
            this.stack = ingred;
            this.burnTime = burnTime;
        }
        public int burnTime;
        public ItemStack stack;
    }

    public static void findFuels() {
        afuels = new ArrayList<FuelPair>();
        Set<Item> efuels = excludedFuels();

        for (ItemStack item : iteml) {
            Block block = Block.getBlockFromItem(item.getItem());
            if (block instanceof BlockDoor)
                continue;
            if (efuels.contains(item.getItem()))
                continue;

            int burnTime = TileEntityFurnace.getItemBurnTime(item);
            if (burnTime > 0)
                afuels.add(new FuelPair(item.copy(), burnTime));
        }
    }

    public static void iterate(){

        LinkedList<ItemStack> items = new LinkedList<ItemStack>();
        for (Item item : (Iterable<Item>) Item.REGISTRY) {

            if (item == null)
                continue;

            NonNullList<ItemStack> list = NonNullList.create();
            item.getSubItems(item, item.getCreativeTab(), list);
            items.addAll(new ArrayList<>(Arrays.asList(list.toArray(new ItemStack[list.size()]))));
        }
        iteml = items;

    }

}
