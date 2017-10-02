package dmfmm.starvationahoy.CropWash;

import dmfmm.starvationahoy.CropWash.item.DirtyItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by TeamDMFMM on 4/17/2016. Code originally written
 * for StarvationAhoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therefore
 * credit us, just don't steal large portions of this.
 */
public class VillagerCropOverride {


    @SubscribeEvent
    public void overideDirtyCropCreation(EntityEvent e){
        if(e.getEntity() instanceof EntityVillager){
            EntityVillager villager = (EntityVillager)e.getEntity();
            if(villager.getProfessionForge().getCareer(0).getName().equals("farmer")){
                EntityItem item = isItemstackInRange(villager.getEntityWorld(), villager.posX, villager.posY, villager.posZ, 2);
                if(item != null){
                    if (item.getEntityItem().getItem() instanceof DirtyItem){
                        Item l = new ItemStack(item.getEntityItem().getTagCompound().getCompoundTag("Original")).getItem();

                        EntityItem newItem = new EntityItem(villager.getEntityWorld(), item.posX, item.posY, item.posZ, new ItemStack(l, item.getEntityItem().getCount()));
                        item.setDead();
                        if(!villager.getEntityWorld().isRemote){
                            villager.getEntityWorld().spawnEntity(newItem);
                        }
                    }
                }
            }
        }
    }

    public EntityItem isItemstackInRange(World world, double posX, double posY, double posZ, double distance)
    {
        double d0 = -1.0D;

        for (int i = 0; i < world.getLoadedEntityList().size(); ++i)
        {
            Entity entity = world.getLoadedEntityList().get(i);
            if(entity instanceof EntityItem) {
                    double d1 = entity.getDistanceSq(posX, posY, posZ);

                    if ((distance < 0.0D || d1 < distance * distance) && (d0 == -1.0D || d1 < d0)) {
                        d0 = d1;
                        return (EntityItem) entity;
                    }
            }
        }
        return null;
    }
}
