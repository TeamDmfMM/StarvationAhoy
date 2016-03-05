package dmfmm.StarvationAhoy.Core;


import dmfmm.StarvationAhoy.Client.Gui.book_gui.BookPageGui;
import dmfmm.StarvationAhoy.Core.lib.CoreLib;
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
        if(ID == CoreLib.bookGUIID){
            //return new InfoBookGUI();
            return new BookPageGui("test");
        }
        return null;
    }
}
