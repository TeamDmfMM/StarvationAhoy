package dmfmm.StarvationAhoy.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;

public interface IProxy {
	public KeyBinding debugKey = null;

	public abstract void registerKeyBindings();
	public abstract void registerRenderers();

	public abstract void registerMeatTypes();

	public abstract ModelBiped getArmorModel(int i);

}
