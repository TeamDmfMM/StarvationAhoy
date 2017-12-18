package dmfmm.starvationahoy.client;

import dmfmm.starvationahoy.client.Gui.SAGuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

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
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() { return null; }


}
