package dmfmm.StarvationAhoy.Meat.item;

import net.minecraft.item.Item;
import dmfmm.StarvationAhoy.Core.SATabs;

public class FiletKnife extends Item{
	
	public FiletKnife()
	{
        super();
        this.maxStackSize = 1;
        this.setTextureName("starvationahoy:filetknife");
        this.setContainerItem(this);
        this.setCreativeTab(SATabs.INSTANCE);
    }
}
