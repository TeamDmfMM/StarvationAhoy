package dmfmm.starvationahoy.meat.item;

import dmfmm.starvationahoy.core.SATabs;
import net.minecraft.item.Item;

public class SkinnedEntity extends Item{
	
	public SkinnedEntity(String name){
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(SATabs.INSTANCE);
	}
}
