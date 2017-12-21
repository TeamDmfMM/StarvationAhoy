package dmfmm.starvationahoy.proxy;

import dmfmm.starvationahoy.base.BlockRegister;
import dmfmm.starvationahoy.base.ItemRegister;
import dmfmm.starvationahoy.crops.ModuleCropWash;
import dmfmm.starvationahoy.meat.ModuleMeat;
import net.minecraft.client.settings.KeyBinding;

public abstract class CommonProxy implements IProxy {

    public KeyBinding debugKey = null;


    public void preInit() {
        ModuleMeat.preinit();
        ModuleCropWash.preinit();

        BlockRegister.registerBlocks();
        ItemRegister.registerItems();
    }
}
