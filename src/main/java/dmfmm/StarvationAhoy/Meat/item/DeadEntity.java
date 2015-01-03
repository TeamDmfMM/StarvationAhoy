package dmfmm.StarvationAhoy.Meat.item;

import dmfmm.StarvationAhoy.Core.SATabs;
import net.minecraft.item.Item;

public class DeadEntity extends Item{
	
		public DeadEntity(String name, String texture){
			super();
			this.setUnlocalizedName(name);
			this.setTextureName(texture);
			this.setCreativeTab(SATabs.INSTANCE);
		}

}
