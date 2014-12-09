package dmfmm.StarvationAhoy.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import dmfmm.StarvationAhoy.Client.Renderer.MeatHangerRenderer;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;


public class ClientProxy extends CommonProxy{

	@Override
	public void registerKeyBindings() {
		
	}
	public void registerRenderers(){
		ClientRegistry.bindTileEntitySpecialRenderer(MeatHangerTileEntity.class, new MeatHangerRenderer());
	}

}
