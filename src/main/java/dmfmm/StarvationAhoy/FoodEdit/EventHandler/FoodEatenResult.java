package dmfmm.StarvationAhoy.FoodEdit.EventHandler;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.KnownEffects;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketRequestNewFoods;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketResponseNewFoods;
import dmfmm.StarvationAhoy.StarvationAhoy;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
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
	public void foodTickEvent(LivingEntityUseItemEvent.Tick e){
		if(e.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer)e.getEntityLiving();
			ItemStack itemstack = entityPlayer.inventory.getCurrentItem();
			if (e.getDuration() <= 1) {
				if (KnownFoods.getFoodHunger(e.getItem()) != -1) {
					this.onFinish(e);
					e.setCanceled(true);
				}
			} else {
				e.getItem().getItem().onUsingTick(e.getItem(), entityPlayer, e.getDuration());
				if (e.getDuration() <= 25 && e.getDuration() % 4 == 0) {
					Random rand = new Random();
					if (itemstack.getItemUseAction() == EnumAction.DRINK) {
						e.getEntity().playSound(SoundEvents.entity_generic_death, 0.5F, e.getEntity().worldObj.rand.nextFloat() * 0.1F + 0.9F);
					}

					if (itemstack.getItemUseAction() == EnumAction.EAT) {
						for (int i = 0; i < 5; ++i) {
							Vec3d vec3 = new Vec3d(((double) rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
							vec3 = vec3.rotatePitch(-e.getEntity().rotationPitch * (float) Math.PI / 180.0F);
							vec3 = vec3.rotateYaw(-e.getEntity().rotationYaw * (float) Math.PI / 180.0F);
							double d0 = (double) (-rand.nextFloat()) * 0.6D - 0.3D;
							Vec3d vec31 = new Vec3d(((double) rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
							vec31 = vec31.rotatePitch(-e.getEntity().rotationPitch * (float) Math.PI / 180.0F);
							vec31 = vec31.rotateYaw(-e.getEntity().rotationYaw * (float) Math.PI / 180.0F);
							vec31 = vec31.addVector(e.getEntity().posX, e.getEntity().posY + (double) e.getEntity().getEyeHeight(), e.getEntity().posZ);

							if (itemstack.getHasSubtypes()) {
								e.getEntity().worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord + 0.05D, vec3.zCoord, new int[]{Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata()});
							} else {
								e.getEntity().worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord + 0.05D, vec3.zCoord, new int[]{Item.getIdFromItem(itemstack.getItem())});
							}
						}

						e.getEntity().playSound(SoundEvents.entity_generic_eat, 0.5F + 0.5F * (float) rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
					}
				}

				if (e.getDuration() - 1 == 0 && !entityPlayer.worldObj.isRemote) {
					if (KnownFoods.getFoodHunger(e.getItem()) != -1) {
						this.onFinish(e);
						e.setCanceled(true);
					}
				}
			}
		}
	}
		
	public void onFinish(LivingEntityUseItemEvent e){
		if (e.getItem() != null)
		{
				//PlayerInstanceHolder.instance.playerEatFood(entityPlayer, e.getItem().getItem());
			EntityPlayer entityPlayer = (EntityPlayer)e.getEntityLiving();
	            //this.updateItemUse(e.getItem(), 16);
				Random rand = new Random();
	            int i = e.getItem().stackSize;
	            ItemStack itemstack = e.getItem();
	            ItemFood usingFood = (ItemFood) e.getItem().getItem();
				//int HealAmount = usingFood.func_150905_g(e.getItem()) / 2;
	            //float SaturationAmt = usingFood.func_150906_h(e.getItem()) / 3;
	            int HealAmount = KnownFoods.getFoodHunger(e.getItem());
	            float SaturationAmt = KnownFoods.getFoodSaturation(e.getItem());
				entityPlayer.getFoodStats().addStats(HealAmount, SaturationAmt);
				if(entityPlayer.worldObj.isRemote){
				entityPlayer.playSound(SoundEvents.entity_generic_eat, 0.5F + 0.5F * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				Minecraft.getMinecraft().theWorld.playSound((EntityPlayer)null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, SoundEvents.entity_player_burp, SoundCategory.PLAYERS, 0.5F, Minecraft.getMinecraft().theWorld.rand.nextFloat() * 0.1F + 0.9F);
				}
				if (KnownEffects.effects.keySet().contains(e.getItem().getItem())){
					ArrayList<Double> data = KnownEffects.effects.get(e.getItem().getItem());
					Double prob = data.get(3);
					if (rand.nextFloat() < prob){
						int potion = (int)((double)data.get(0));
						int duration = (int)((double)data.get(1));
						int amplifier = (int)((double)data.get(2));
						PotionEffect effecter = new PotionEffect(Potion.getPotionById(potion), duration, amplifier);
						entityPlayer.addPotionEffect(effecter);
					}
				}
	            if (itemstack != e.getItem() || itemstack != null && itemstack.stackSize != i)
	            {
	                entityPlayer.inventory.mainInventory[entityPlayer.inventory.currentItem] = itemstack;

	                if (itemstack != null && itemstack.stackSize == 0)
	                {
	                	entityPlayer.inventory.mainInventory[entityPlayer.inventory.currentItem] = null;
	                }
	            }
	            --e.getItem().stackSize;
	            entityPlayer.resetActiveHand();
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
