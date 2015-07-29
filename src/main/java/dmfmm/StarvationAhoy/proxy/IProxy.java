package dmfmm.StarvationAhoy.proxy;

import net.minecraft.client.settings.KeyBinding;

public interface IProxy {
	public KeyBinding debugKey = null;

	public abstract void registerKeyBindings();
	public abstract void registerRenderers();

}
