package dmfmm.StarvationAhoy.FoodEdit.EventHandler;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.KnownEffects;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketRequestNewFoods;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketResponseNewFoods;
import dmfmm.StarvationAhoy.StarvationAhoy;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class FoodEatenResult
{

		@SubscribeEvent
		public void foodTickEvent(PlayerUseItemEvent.Tick e){
			ItemStack itemstack = e.entityPlayer.inventory.getCurrentItem();
            if (e.duration <= 1)
            {
            	if(KnownFoods.getFoodHunger(e.item) != -1){
                this.onFinish(e);
                e.setCanceled(true);
            	}
            }
            else
            {
                e.item.getItem().onUsingTick(e.item, e.entityPlayer, e.duration);
                if (e.duration <= 25 && e.duration % 4 == 0)
                {
                Random rand = new Random();
					if (itemstack.getItemUseAction() == EnumAction.DRINK)
					{
						e.entity.playSound("random.drink", 0.5F, e.entity.worldObj.rand.nextFloat() * 0.1F + 0.9F);
					}

					if (itemstack.getItemUseAction() == EnumAction.EAT)
					{
						for (int i = 0; i < 5; ++i)
						{
							Vec3 vec3 = new Vec3(((double)rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
							vec3 = vec3.rotatePitch(-e.entity.rotationPitch * (float)Math.PI / 180.0F);
							vec3 = vec3.rotateYaw(-e.entity.rotationYaw * (float)Math.PI / 180.0F);
							double d0 = (double)(-rand.nextFloat()) * 0.6D - 0.3D;
							Vec3 vec31 = new Vec3(((double)rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
							vec31 = vec31.rotatePitch(-e.entity.rotationPitch * (float)Math.PI / 180.0F);
							vec31 = vec31.rotateYaw(-e.entity.rotationYaw * (float)Math.PI / 180.0F);
							vec31 = vec31.addVector(e.entity.posX, e.entity.posY + (double)e.entity.getEyeHeight(), e.entity.posZ);

							if (itemstack.getHasSubtypes())
							{
								e.entity.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord + 0.05D, vec3.zCoord, new int[] {Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata()});
							}
							else
							{
								e.entity.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord + 0.05D, vec3.zCoord, new int[] {Item.getIdFromItem(itemstack.getItem())});
							}
						}

						e.entity.playSound("random.eat", 0.5F + 0.5F * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
					}
                }

                if (e.duration - 1 == 0 && !e.entityPlayer.worldObj.isRemote)
                {
                	if(KnownFoods.getFoodHunger(e.item) != -1){
                    this.onFinish(e);
                    e.setCanceled(true);
                	}
                }
            }
		}
		
		public void onFinish(PlayerUseItemEvent e){
			if (e.item != null)
	        {
				//PlayerInstanceHolder.instance.playerEatFood(e.entityPlayer, e.item.getItem());

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
				if (KnownEffects.effects.keySet().contains(e.item.getItem())){
					ArrayList<Double> data = KnownEffects.effects.get(e.item.getItem());
					Double prob = data.get(3);
					if (rand.nextFloat() < prob){
						int potion = (int)((double)data.get(0));
						int duration = (int)((double)data.get(1));
						int amplifier = (int)((double)data.get(2));
						PotionEffect effecter = new PotionEffect(potion, duration, amplifier);
						e.entityPlayer.addPotionEffect(effecter);
					}
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



		// Just adding thing here for testing

		@SubscribeEvent
		public void onServerJoin(PlayerEvent.PlayerLoggedInEvent e){
			//SALog.error("attempt Sending");
			StarvationAhoy.MultiBlockChannel.sendTo(new PacketResponseNewFoods(KnownFoods.knownFoods), (EntityPlayerMP) e.player);
		}


		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public void onServerLeave(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
			KnownFoods.leaveServer();
		}
}
