package dmfmm.starvationahoy.Core;

import dmfmm.starvationahoy.Core.lib.ModInfo;
import dmfmm.starvationahoy.Meat.init.MBlockLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SATabs extends CreativeTabs {
	
	public static SATabs INSTANCE = new SATabs();


	public SATabs() {
		super(ModInfo.MOD_ID);
	}


	@Override
	public ItemStack getTabIconItem() {//H
		return new ItemStack(MBlockLoader.MeatHanger);
	}
}
