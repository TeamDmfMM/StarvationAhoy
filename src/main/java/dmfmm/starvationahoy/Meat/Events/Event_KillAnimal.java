package dmfmm.starvationahoy.Meat.Events;

import dmfmm.starvationahoy.Meat.ModuleMeat;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class Event_KillAnimal {
	
	
	@SubscribeEvent
	public void OverrideDropEvent(LivingDropsEvent e){
		if (!(e.getEntityLiving() instanceof EntityLiving ) ){
			return;
		}
		//SALog.fatal("MEH");
		//SALog.fatal("IS EP" + e.getEntityLiving());
		//SALog.fatal("DROPS?" + ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()).value);
    if(e.getEntityLiving() instanceof EntityPlayer){
    	//How about no messing with player Drops? kk?
		return;
	}else if (ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()).value == true){

			e.getDrops().clear();
			//SALog.fatal(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()Living).meat.items.dead);
			e.getDrops().add(new EntityItem(e.getEntityLiving().world, e.getEntityLiving().posX, e.getEntityLiving().posY, e.getEntityLiving().posZ, new ItemStack(ModuleMeat.registry.overrideFoodDropsFor((EntityLiving)e.getEntityLiving()).meat.items.dead)));
		}
	}
}
