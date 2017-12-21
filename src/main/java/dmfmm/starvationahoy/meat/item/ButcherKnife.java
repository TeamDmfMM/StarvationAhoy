package dmfmm.starvationahoy.meat.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import dmfmm.starvationahoy.core.SATabs;

public class ButcherKnife extends Item{
	public ButcherKnife(String name)
	{
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(10);
        this.setUnlocalizedName(name);
		this.setRegistryName(name);
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
	        copiedStack.setCount(1);
	        return copiedStack;
	}

    
    //public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack) {return false;}
    
    public boolean getShareTag(){
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isFull3D(){
        return true;
    }

}
