package dmfmm.starvationahoy.client;

import java.util.Set;

import dmfmm.starvationahoy.client.Gui.SAGuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class ConfigGUIFactory implements IModGuiFactory{

	@Override
	public void initialize(Minecraft minecraftInstance) {
		
		
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new SAGuiConfig(parentScreen);
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() { return null; }

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() { return null; }

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) { return null; }

}
