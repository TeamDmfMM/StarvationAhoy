package dmfmm.starvationahoy.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by dmf444 on 12/20/2017. Code originally written for StarvationAhoy.
 */
public class NamedItemBlock extends ItemBlock {

    public NamedItemBlock(Block block, String name) {
        super(block);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
}
