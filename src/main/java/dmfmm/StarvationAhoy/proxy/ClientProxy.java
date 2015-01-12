package dmfmm.StarvationAhoy.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dmfmm.StarvationAhoy.Client.Renderer.MeatHangerRenderer;
import dmfmm.StarvationAhoy.Client.Renderer.PigItemRenderer;
import dmfmm.StarvationAhoy.Core.HUD.OverlaySaturationBar;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;


public class ClientProxy extends CommonProxy{

	@Override
	public void registerKeyBindings() {
		
	}
	public void registerRenderers(){
		ClientRegistry.bindTileEntitySpecialRenderer(MeatHangerTileEntity.class, new MeatHangerRenderer());
		MinecraftForgeClient.registerItemRenderer(MItemLoader.deadPig, new PigItemRenderer());
	}


}
