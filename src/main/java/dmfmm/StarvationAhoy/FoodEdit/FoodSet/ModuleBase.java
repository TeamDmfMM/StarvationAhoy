package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.Core.util.ConfigHandler;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

import org.apache.commons.lang3.reflect.*;

public class ModuleBase extends Module {

	@Override
	public void init(KnownFoods food) {
		// TODO Auto-generated method stub
		
		int percentage = CRef.getFoodPrecent();
		
		for (Object key : Item.itemRegistry.getKeys()){
			if (Item.itemRegistry.getObject(key) instanceof ItemFood){
				ItemFood foo = (ItemFood) Item.itemRegistry.getObject(key);
				
				try {
					int effect = (int) FieldUtils.readField(foo.getClass().getDeclaredField("potionId"), foo, true);
					int duration = (int) FieldUtils.readField(foo.getClass().getDeclaredField("potionDuration"), foo, true);
					int amplifier = (int) FieldUtils.readField(foo.getClass().getDeclaredField("potionAmplifier"), foo, true);
					float prob = (float) FieldUtils.readField(foo.getClass().getDeclaredField("potionEffectProbability"), foo, true);
					if (prob != 0.0F){
						
					
					SALog.info("Potion data for: " + foo.getUnlocalizedName() + " Effect: " + effect + " Duration: " + duration + " Amplifier: " + amplifier + " Prob: " + prob);
					
					KnownEffects.addEffect(foo, effect, duration, amplifier, prob);
					}
					
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					SALog.error("Could not extract potion data for: " + foo.getUnlocalizedName());
					
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					
					
					SALog.error("Could not extract potion data for: " + foo.getUnlocalizedName());
				}
				
				int hunger = (int) (foo.func_150905_g(new ItemStack(foo, 1))*(percentage/100.0f));
				float sturan = foo.func_150906_h(new ItemStack(foo, 1))*(percentage/100.0f);
				
				food.insertFoodI(new ItemStack(foo, 1), hunger, sturan);
				
			}
		}
		
		
	}

}
