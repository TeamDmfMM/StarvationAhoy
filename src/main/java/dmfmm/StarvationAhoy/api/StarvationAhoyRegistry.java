package dmfmm.StarvationAhoy.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

public class StarvationAhoyRegistry {
	
	public interface IStarvationAhoyProvider {
		
		
		
		/**
		 * To maunally add food overrides to Starvation Ahoy, create a class extending {@link dmfmm.StarvationAhoy.api.FoodEdit.Module}, and register with this method.
		 * This will be injected into Starvation Ahoy's known food list at the begining of Pre-init event
		 * 
		 * @param m class of the module to be registerd
		 * @return nothing
		 */
		public void registerModule(Class<? extends Module> m);
		
		
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
		
		public void registerDeadEntity(int id, Class<? extends EntityLiving> entity, Item dead, Item skinned);
			
		
		
		
	}

	public static IStarvationAhoyProvider instance;
	
	public static IStarvationAhoyProvider getInstance(){
		return instance;
	}
	public static void init(IStarvationAhoyProvider inst){
		if (instance == null) { instance = inst; }
	}
	
	
	
}