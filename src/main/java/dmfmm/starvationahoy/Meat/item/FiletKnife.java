package dmfmm.starvationahoy.Meat.item;

import net.minecraft.item.Item;
import dmfmm.starvationahoy.Core.SATabs;

public class FiletKnife extends Item{
	
	public FiletKnife()
	{
        super();
        this.maxStackSize = 1;
        //this.setTextureName("starvationahoy:filetknife");
        this.setContainerItem(this);
        this.setCreativeTab(SATabs.INSTANCE);
    }
}
