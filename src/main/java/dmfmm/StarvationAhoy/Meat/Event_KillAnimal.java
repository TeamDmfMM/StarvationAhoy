package dmfmm.StarvationAhoy.Meat;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;

public class Event_KillAnimal {
	
	@SubscribeEvent
	public void OverrideDropEvent(LivingDropsEvent e){
		e.drops.clear();
		if(e.entity instanceof EntityCow){
			e.drops.add(new EntityItem(e.entity.worldObj, e.entity.posX, e.entity.posY, e.entity.posZ, new ItemStack(MItemLoader.deadCow)));
		} else if(e.entity instanceof EntityPig){
			e.drops.add(new EntityItem(e.entity.worldObj, e.entity.posX, e.entity.posY, e.entity.posZ, new ItemStack(MItemLoader.deadPig)));
		}else if(e.entity instanceof EntityChicken){
			e.drops.add(new EntityItem(e.entity.worldObj, e.entity.posX, e.entity.posY, e.entity.posZ, new ItemStack(MItemLoader.deadChicken)));
		}
	}

}
