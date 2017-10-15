package dmfmm.starvationahoy.core;

import dmfmm.starvationahoy.core.lib.ModInfo;
import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SATabs extends CreativeTabs {
	
	public static SATabs INSTANCE = new SATabs();


	public SATabs() {
		super(ModInfo.MOD_ID);
	}


	@Override
	public ItemStack getTabIconItem() {//H
		return new ItemStack(MeatBlockRegistry.MeatHanger);
	}
}
