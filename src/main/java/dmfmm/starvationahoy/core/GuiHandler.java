package dmfmm.starvationahoy.core;


import dmfmm.starvationahoy.client.gui.InfoBookGUI;
import dmfmm.starvationahoy.core.lib.CoreLib;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == CoreLib.BOOK_GUI_ID){
            return new InfoBookGUI();
        }
        return null;
    }
}
