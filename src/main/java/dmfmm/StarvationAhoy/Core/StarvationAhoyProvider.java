package dmfmm.StarvationAhoy.Core;


import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.Meat.ISAModel;
import dmfmm.StarvationAhoy.api.StarvationAhoyRegistry.IStarvationAhoyProvider;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;

import java.util.Map;

public class StarvationAhoyProvider implements IStarvationAhoyProvider {
	
	private int nextId = 6;


	private Map<String, Item> internalModMapItems;

	@Override
	public int getNextAvailableMeatType() {
		nextId++;
		return nextId-1;
	}

	@Override
	public void registerMeatType(int id, ModelBase modelEntity, String normalTexture, String skinnedTexture, String rottenTexture) {
		if (!(modelEntity instanceof ISAModel)) {
			SALog.warn("Some mod has tried to add a new meat type w/o implementing ISAModel");
		}

		ModuleMeat.registry.onMeatType(id, modelEntity, normalTexture, skinnedTexture, rottenTexture);


	}



	@Override
	public void registerDeadEntity(int id, Class<? extends EntityLiving> entity, Item dead, Item skinned, Item onskinned, Item meat) {
		ModuleMeat.registry.onDeadEntity(id, entity, dead, skinned, meat, onskinned);
	}

}