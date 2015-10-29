package dmfmm.StarvationAhoy.Meat.Events;

import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Core.util.SALog;

public class Event_KillAnimal {
	
	
	@SubscribeEvent
	public void OverrideDropEvent(LivingDropsEvent e){
		if (!(e.entity instanceof EntityLiving ) ){
			return;
		}
		//SALog.fatal("MEH");
		//SALog.fatal("IS EP" + e.entity);
		//SALog.fatal("DROPS?" + ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.entity).value);
    if(e.entity instanceof EntityPlayer){
    	//How about no messing with player Drops? kk?
	}else if (ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.entity).value == true){

			e.drops.clear();
			//SALog.fatal(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.entityLiving).meat.items.dead);
			e.drops.add(new EntityItem(e.entity.worldObj, e.entity.posX, e.entity.posY, e.entity.posZ, new ItemStack(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.entityLiving).meat.items.dead)));
		}
	}
}
