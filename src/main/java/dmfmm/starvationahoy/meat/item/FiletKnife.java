package dmfmm.starvationahoy.meat.item;

import net.minecraft.item.Item;
import dmfmm.starvationahoy.core.SATabs;

public class FiletKnife extends Item{
	
	public FiletKnife(String name) {
        super();
        this.maxStackSize = 1;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setContainerItem(this);
        this.setCreativeTab(SATabs.INSTANCE);
    }
}
