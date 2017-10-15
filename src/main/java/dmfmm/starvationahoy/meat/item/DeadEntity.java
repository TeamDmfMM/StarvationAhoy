package dmfmm.starvationahoy.meat.item;

import dmfmm.starvationahoy.core.SATabs;
import net.minecraft.item.Item;

public class DeadEntity extends Item{
	
		public DeadEntity(String name){
			super();
			this.setUnlocalizedName(name);
			//this.setTextureName(texture);
			this.setCreativeTab(SATabs.INSTANCE);
		}

}
