package dmfmm.StarvationAhoy.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.EntityEquipmentSlot;

public interface IProxy {
	KeyBinding debugKey = null;

	 void registerKeyBindings();
	 void registerRenderers();

	void registerMeatTypes();

	ModelBiped getArmorModel(ModelBiped model, EntityEquipmentSlot slot);

	void initSounds();

	void preInit();

}
