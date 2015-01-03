package dmfmm.StarvationAhoy.FoodEdit.EventHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.KnownFoods;

	public class FoodEatenResult
	{	

		@SubscribeEvent
		public void foodTickEvent(PlayerUseItemEvent.Tick e){
			ItemStack itemstack = e.entityPlayer.inventory.getCurrentItem();
			if(e.entityPlayer.worldObj.isRemote){
            if (e.entityPlayer.getItemInUseCount() <= 0)
            {
            	if(KnownFoods.getFoodHunger(e.item) != -1){
                this.onFinish(e);
                e.setCanceled(true);
            	}
            }
            else
            {
                e.item.getItem().onUsingTick(e.item, e.entityPlayer, e.entityPlayer.getItemInUseCount());
                if (e.entityPlayer.getItemInUseCount() <= 25 && e.entityPlayer.getItemInUseCount() % 4 == 0){
                Random rand = new Random();
                  for (int j = 0; j < 5; ++j)
                  {
	                Vec3 vec3 = Vec3.createVectorHelper(((double)rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
	                vec3.rotateAroundX(-e.entityPlayer.rotationPitch * (float)Math.PI / 180.0F);
	                vec3.rotateAroundY(-e.entityPlayer.rotationYaw * (float)Math.PI / 180.0F);
	                Vec3 vec31 = Vec3.createVectorHelper(((double)rand.nextFloat() - 0.5D) * 0.3D, (double)(-rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
	                vec31.rotateAroundX(-e.entityPlayer.rotationPitch * (float)Math.PI / 180.0F);
	                vec31.rotateAroundY(-e.entityPlayer.rotationYaw * (float)Math.PI / 180.0F);
	                vec31 = vec31.addVector(e.entityPlayer.posX, e.entityPlayer.posY + (double)e.entityPlayer.getEyeHeight(), e.entityPlayer.posZ);
	                String s = "iconcrack_" + Item.getIdFromItem(e.item.getItem());

	                if (e.item.getHasSubtypes())
	                {
	                    s = s + "_" + e.item.getItemDamage();
	                }

	                e.entityPlayer.worldObj.spawnParticle(s, vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord + 0.05D, vec3.zCoord);
	            }
                }
                }
               // if (e.entityPlayer.getItemInUseCount() - 1 == 0 && !e.entityPlayer.worldObj.isRemote)
               // {
               // 	if(KnownFoods.getFoodHunger(e.item) != -1){
               //     this.onFinish(e);
               //     e.setCanceled(true);
                //	}
                //}
            }
			if(!e.entityPlayer.worldObj.isRemote){
				if(KnownFoods.getFoodHunger(e.item) != -1){
                    this.onFinish(e);
                    e.setCanceled(true);
                	}
			}
		}
		
		public void onFinish(PlayerUseItemEvent e){
			if (e.item != null)
	        {
	            //this.updateItemUse(e.item, 16);
				Random rand = new Random();
	            int i = e.item.stackSize;
	            ItemStack itemstack = e.item;
	            ItemFood usingFood = (ItemFood) e.item.getItem();
				//int HealAmount = usingFood.func_150905_g(e.item) / 2;
	            //float SaturationAmt = usingFood.func_150906_h(e.item) / 3;
	            int HealAmount = KnownFoods.getFoodHunger(e.item);
	            float SaturationAmt = KnownFoods.getFoodSaturation(e.item);
				e.entityPlayer.getFoodStats().addStats(HealAmount, SaturationAmt);
				if(e.entityPlayer.worldObj.isRemote){
				e.entityPlayer.playSound("random.eat", 0.5F + 0.5F * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				Minecraft.getMinecraft().theWorld.playSoundAtEntity(e.entityPlayer, "random.burp", 0.5F, rand.nextFloat() * 0.1F + 0.9F);
				}

	            if (itemstack != e.item || itemstack != null && itemstack.stackSize != i)
	            {
	                e.entityPlayer.inventory.mainInventory[e.entityPlayer.inventory.currentItem] = itemstack;

	                if (itemstack != null && itemstack.stackSize == 0)
	                {
	                	e.entityPlayer.inventory.mainInventory[e.entityPlayer.inventory.currentItem] = null;
	                }
	            }
	            --e.item.stackSize;
	            e.entityPlayer.clearItemInUse();
	        }
		}

	}
