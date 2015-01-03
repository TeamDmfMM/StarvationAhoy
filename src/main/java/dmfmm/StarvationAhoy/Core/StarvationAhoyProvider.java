package dmfmm.StarvationAhoy.Core;


import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import dmfmm.StarvationAhoy.FoodEdit.FoodSet.ModuleLoad;
import dmfmm.StarvationAhoy.api.StarvationAhoyRegistry.IStarvationAhoyProvider;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

public class StarvationAhoyProvider implements IStarvationAhoyProvider {
	
	private int nextId = 4;
	
	@Override
	public void registerModule(Class<? extends Module> m) {
		// TODO Auto-generated method stub
		ModuleLoad.registerModule(m);
	}

	@Override
	public int getNextAvaiableMeatType() {
		// TODO Auto-generated method stub
		nextId++;
		return nextId-1;
	}

	@Override
	public void registerMeatType(int id,
			Class<? extends ModelBase> modelEntity, String normalTexture,
			String skinnedTexture, String rottenTexture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerDeadEntity(int id,
			Class<? extends EntityLiving> entity, Item dead, Item skinned) {
		// TODO Auto-generated method stub
		
	}

}
