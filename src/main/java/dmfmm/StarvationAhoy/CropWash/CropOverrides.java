package dmfmm.StarvationAhoy.CropWash;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Matthew on 3/14/2015.
 */
public class CropOverrides {

    public static CropOverrides crops = new CropOverrides();


    public static class CropOverride {
        private final Block toOverride;
        private final Item replace;
        private final Item replaceWith;

        public CropOverride(Block toOverride, Item replace, Item replaceWith){

            this.toOverride = toOverride;
            this.replace = replace;
            this.replaceWith = replaceWith;
        }

        public boolean shouldUse(Block t, ArrayList<ItemStack> drops){
            if (toOverride == t){
                for (ItemStack i : drops){
                    if (i.getItem() == replace){
                        return true;
                    }
                }
            }
            return false;
        }

        public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> drops) {
            for (ItemStack i : drops){
                if (i.getItem() == replace){
                    ItemStack replacer = i;
                    ItemStack replaced = new ItemStack(replaceWith, replacer.stackSize);
                    drops.set(drops.indexOf(i), replaced);
                    break;
                }
            }
            return drops;
        }

    }

    public ArrayList<CropOverride> overrides = new ArrayList<>();

    public CropOverrides(){

    }

    public ArrayList<ItemStack> shouldOverride(Block t, ArrayList<ItemStack> drops){
        for (CropOverride crop : overrides){
            if (crop.shouldUse(t, drops) == true) return crop.getDrops(drops);

        }
        return drops;
    }

}
