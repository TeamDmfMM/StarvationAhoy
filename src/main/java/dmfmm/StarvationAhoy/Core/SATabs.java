package dmfmm.StarvationAhoy.Core;

import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SATabs extends CreativeTabs {
	
	public static SATabs INSTANCE = new SATabs();


	public SATabs() {
		super(ModInfo.MOD_ID);
	}


	@Override
	public Item getTabIconItem() {//H
		return Item.getItemFromBlock(MBlockLoader.MeatHanger);
	}
}
