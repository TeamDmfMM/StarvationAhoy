package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import dmfmm.StarvationAhoy.Core.util.CRef;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import dmfmm.StarvationAhoy.api.FoodEdit.Module;

import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.reflect.*;

public class ModuleBase extends Module {

	@Override
	public void init(KnownFoods food) {
		// TODO Auto-generated method stub
		
		int percentage = CRef.getFoodPrecent();
		
		for (ResourceLocation key : Item.itemRegistry.getKeys()){
			if (Item.itemRegistry.getObject(key) instanceof ItemFood){
				ItemFood foo = (ItemFood) Item.itemRegistry.getObject(key);
				
				//SALog.fatal(Item.itemRegistry.getNameForObject(foo).toString());
				try {
					int effect = (int) FieldUtils.readField(foo.getClass().getDeclaredField("potionId"), foo, true);
					int duration = (int) FieldUtils.readField(foo.getClass().getDeclaredField("potionDuration"), foo, true);
					int amplifier = (int) FieldUtils.readField(foo.getClass().getDeclaredField("potionAmplifier"), foo, true);
					float prob = (float) FieldUtils.readField(foo.getClass().getDeclaredField("potionEffectProbability"), foo, true);
					if (prob != 0.0F){
						
					
					//SALog.info("Potion data for: " + foo.getUnlocalizedName() + " Effect: " + effect + " Duration: " + duration + " Amplifier: " + amplifier + " Prob: " + prob);
					
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

				// Percent of for ALL foods. (anything that extends ItemFood in the item registry)

				int hunger = (int) (foo.getHealAmount(new ItemStack(foo, 1))*(percentage/100.0f));
				float sturan = foo.getSaturationModifier(new ItemStack(foo, 1))*(percentage/100.0f);
				
				food.insertFood(new ItemStack(foo, 1), hunger, sturan);
				
			}
		}
		
		
	}

}