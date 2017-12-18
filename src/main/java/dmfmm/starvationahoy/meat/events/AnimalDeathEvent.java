package dmfmm.starvationahoy.meat.events;

import dmfmm.starvationahoy.meat.EnchantmentMeatSlayer;
import dmfmm.starvationahoy.meat.ModuleMeat;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class AnimalDeathEvent {
	
	
	@SubscribeEvent
	public void OverrideDropEvent(LivingDropsEvent e){
		if (!(e.getEntityLiving() instanceof EntityLiving) || e.getEntityLiving() instanceof EntityPlayer){
			return;
		}

		if(e.getSource().getDamageType() == "player"){
			EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();
			if (player.getHeldItem(player.getActiveHand()).getItem() instanceof ItemSword && this.doesEnchantmentExist(player.getHeldItem(player.getActiveHand()))) {
				return;
			}
		}
		//SALog.fatal("MEH");
		//SALog.fatal("IS EP" + e.getEntityLiving());
		//SALog.fatal("DROPS?" + ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()).value);
		if (ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()).value){
			e.getDrops().clear();
			//SALog.fatal(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()Living).meat.items.dead);
			e.getDrops().add(new EntityItem(e.getEntityLiving().world, e.getEntityLiving().posX, e.getEntityLiving().posY, e.getEntityLiving().posZ, new ItemStack(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()).meat.items.dead)));
		}
	}

	private boolean doesEnchantmentExist(ItemStack stack) {
		NBTTagList nbtTagList = stack.getEnchantmentTagList();
		for (int j = 0; j < nbtTagList.tagCount(); ++j) {
			NBTTagCompound nbttagcompound = nbtTagList.getCompoundTagAt(j);
			int k = nbttagcompound.getShort("id");
			Enchantment enchantment = Enchantment.getEnchantmentByID(k);
			if (enchantment instanceof EnchantmentMeatSlayer) {
				return true;
			}
		}
		return false;
	}
}
