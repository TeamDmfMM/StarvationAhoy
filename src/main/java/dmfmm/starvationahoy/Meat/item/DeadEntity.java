package dmfmm.starvationahoy.Meat.item;

import dmfmm.starvationahoy.Core.SATabs;
import net.minecraft.item.Item;

public class DeadEntity extends Item{
	
		public DeadEntity(String name){
			super();
			this.setUnlocalizedName(name);
			//this.setTextureName(texture);
			this.setCreativeTab(SATabs.INSTANCE);
		}

}
