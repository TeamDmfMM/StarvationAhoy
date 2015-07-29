package dmfmm.StarvationAhoy.proxy;

import net.minecraft.client.model.ModelBiped;

public interface IProxy {
	public abstract void registerKeyBindings();
	public abstract void registerRenderers();
    public abstract void registerMeatTypes();
	public abstract ModelBiped getArmorModel(int type);

}
