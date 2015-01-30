package dmfmm.StarvationAhoy.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import dmfmm.StarvationAhoy.Client.Renderer.HoldingStickRenderer;
import dmfmm.StarvationAhoy.Client.Renderer.MeatHangerRenderer;
import dmfmm.StarvationAhoy.Client.Renderer.PigItemRenderer;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;


public class ClientProxy extends CommonProxy{

	@Override
	public void registerKeyBindings() {
		
	}
	public void registerRenderers(){
		ClientRegistry.bindTileEntitySpecialRenderer(MeatHangerTileEntity.class, new MeatHangerRenderer());
		MinecraftForgeClient.registerItemRenderer(MItemLoader.deadPig, new PigItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(HoldingStickTileEntity.class, new HoldingStickRenderer());
	}


}
