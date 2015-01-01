package dmfmm.StarvationAhoy.Core;


import dmfmm.StarvationAhoy.FoodEdit.FoodSet.ModuleLoad;
import dmfmm.StarvationAhoy.api.StarvationAhoyRegistry.IStarvationAhoyProvider;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

public class StarvationAhoyProvider implements IStarvationAhoyProvider {

	@Override
	public void registerModule(Class<? extends Module> m) {
		// TODO Auto-generated method stub
		ModuleLoad.registerModule(m);
	}

}
