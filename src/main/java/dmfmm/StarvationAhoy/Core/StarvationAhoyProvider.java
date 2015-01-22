package dmfmm.StarvationAhoy.Core;


import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.DeadEntity;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.ModuleLoad;
import dmfmm.StarvationAhoy.api.StarvationAhoyRegistry.IStarvationAhoyProvider;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

import java.util.Map;

public class StarvationAhoyProvider implements IStarvationAhoyProvider {
	
	private int nextId = 4;


	private Map<String, Item> internalModMapItems;
	
	@Override
	public void registerModule(Class<? extends Module> m) {
		// TODO Auto-generated method stub
		ModuleLoad.registerModule(m);
	}

	@Override
	public int getNextAvailableMeatType() {
		// TODO Auto-generated method stub
		nextId++;
		return nextId-1;
	}

	@Override
	public void registerMeatType(int id,
			ModelBase modelEntity, String normalTexture,
			String skinnedTexture, String rottenTexture) {


		ModuleMeat.registry.onMeatType(id, modelEntity, normalTexture, skinnedTexture, rottenTexture);

		if (ModuleMeat.registry.constructedMeatTypeExists(id) == false){

			DeadEntity newDeadEntity = new DeadEntity("externalmod_meat_" + id + "_deaditem", "starvationahoy:placeholder_meat_texture");
			MItemLoader.modMeatItems.put("externalmod_meat_" + id + "_deaditem", newDeadEntity);

		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerDeadEntity(int id,
			Class<? extends EntityLiving> entity, Item dead, Item skinned) {
		// TODO Auto-generated method stub
		
	}

}