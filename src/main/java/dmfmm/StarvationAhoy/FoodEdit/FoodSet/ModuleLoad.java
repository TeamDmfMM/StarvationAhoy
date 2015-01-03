package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import dmfmm.StarvationAhoy.FoodEdit.FoodSet.User.ModuleUser;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

public class ModuleLoad {

	
	public static ArrayList<Class<? extends Module>> things = new ArrayList<Class<? extends Module>>();
	
	public static void registerModule(Class<? extends Module> toRegister){
		things.add(toRegister);
	}
	
	public static void loadModules(){
		KnownFoods give = new KnownFoods();
		new ModuleBase().init(give);
		ModuleVanilla.init();
		
		for (Class<? extends Module> toLoad : things){
			try {
				Method init = toLoad.getMethod("init", KnownFoods.class);
				init.invoke(toLoad.newInstance(), give);
			} catch (NoSuchMethodException | SecurityException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new ModuleUser().init(give);
		
	}
}