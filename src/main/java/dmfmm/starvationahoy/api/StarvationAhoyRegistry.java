package dmfmm.starvationahoy.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;

public class StarvationAhoyRegistry {
	
	public interface IStarvationAhoyProvider {

		
		/**
		 * 
		 * Find the next available meat id. 
		 *  
		 * @return the next available meat id
		 */
		public int getNextAvailableMeatType();
		
		
		/**
		 * 
		 * @param id the meat id (usally from {@code getNextAvailableMeatType()})
		 * @param modelEntity the model to be used for your entity, with the correct rotation (we will rotate it by x degrees depending on where the block was placed)
		 * @param normalTexture the location (used in a new {@link net.minecraft.util.ResourceLocation}) of the animal's regular texture
		 * @param skinnedTexture the location (used in a new {@link net.minecraft.util.ResourceLocation}) of the animal's skinned texture
		 * @param rottenTexture the location (used in a new {@link net.minecraft.util.ResourceLocation}) of the animal's rotten texture
		 */
		public void registerMeatType(int id, ModelBase modelEntity, String normalTexture, String skinnedTexture, String rottenTexture);

		public void registerDeadEntity(int id, Class<? extends EntityLiving> entity, Item dead, Item skinned, Item onskinned, Item meat);
			
		
		
		
	}

	public static IStarvationAhoyProvider instance;
	
	public static IStarvationAhoyProvider getInstance(){
		return instance;
	}
	public static void init(IStarvationAhoyProvider inst){
		if (instance == null) { instance = inst; }
	}
	
	
	
}