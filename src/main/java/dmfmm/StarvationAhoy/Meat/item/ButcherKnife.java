package dmfmm.StarvationAhoy.Meat.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;

public class ButcherKnife extends Item{
	public ButcherKnife()
	{
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(10);
        this.setTextureName("starvationahoy:knife");
        this.setContainerItem(this);
        this.setCreativeTab(SATabs.INSTANCE);
    }

	public Item setNoRepair() {
		boolean canRepairng = false;
		return this;
	}
	
	@Override
	public boolean isRepairable() {
		return false;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemstack) {
		 ItemStack copiedStack = itemstack.copy();

	        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);
	        copiedStack.stackSize = 1;
	        return copiedStack;
	}

    
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack) {
    	return false;
    }
    
    public boolean getShareTag(){
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isFull3D(){
        return true;
    }

}
