package dmfmm.StarvationAhoy.CropWash;

import dmfmm.StarvationAhoy.CropWash.DirtyBlocks;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import dmfmm.StarvationAhoy.CropWash.item.DirtyItem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

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
            if(villager.getProfession() == 0){
                EntityItem item = isItemstackInRange(villager.getEntityWorld(), villager.posX, villager.posY, villager.posZ, 2);
                if(item != null){
                    if (item.getEntityItem().getItem() instanceof DirtyItem){
                        Item l = ItemStack.loadItemStackFromNBT(item.getEntityItem().getTagCompound().getCompoundTag("Original")).getItem();

                        EntityItem newItem = new EntityItem(villager.getEntityWorld(), item.posX, item.posY, item.posZ, new ItemStack(l, item.getEntityItem().stackSize));
                        item.setDead();
                        if(!villager.getEntityWorld().isRemote){
                            villager.getEntityWorld().spawnEntityInWorld(newItem);
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
