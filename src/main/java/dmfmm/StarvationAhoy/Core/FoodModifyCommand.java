package dmfmm.StarvationAhoy.Core;

import dmfmm.StarvationAhoy.FoodEdit.FoodSet.FoodChanger;
import net.minecraft.command.*;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodModifyCommand implements ICommand{
	
	private List others;
	
	public FoodModifyCommand(){
		this.others = new ArrayList();
		this.others.add("starvationahoy");
		this.others.add("sa");
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "editfoodval";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/sa <item> [new Hunger Value] [new Saturation]";
	}

	@Override
	public List getCommandAliases() {
		return this.others;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] CMDin) {
        if (CMDin.length < 3)
        {
            throw new WrongUsageException(getCommandUsage(null), new Object[0]);
        } else {
			//SALog.error("Hihihihihihihi " + CMDin);
        	Item item = CommandBase.getItemByText(sender, CMDin[0]);
        	int hunger = CommandBase.parseInt(sender, CMDin[1]);
        	float saturation = this.parseFloat(sender, CMDin[2]);
        	
        	try {
				FoodChanger.change(item, hunger, saturation);
				sender.addChatMessage(new ChatComponentText(StatCollector.translateToLocal(item.getUnlocalizedName() + ".name") + " was sucessfully changed to the new levels!"));
			} catch (IOError | IOException e) {
				throw new WrongUsageException(e.getMessage(), new Object[0]);
			} catch (FoodChanger.FoodNotFoundException e){
				throw new WrongUsageException("Food not found in config file to change.", new Object[0]);
			}
        }
		
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return p_71516_2_.length == 1 ? CommandBase.getListOfStringsFromIterableMatchingLastWord(p_71516_2_, Item.itemRegistry.getKeys()) : null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}
	
    protected String[] getPlayers()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }
	
	private static float parseFloat(ICommandSender s, String string){
        try
        {
            return Float.parseFloat(string);
        }
        catch (NumberFormatException numberformatexception)
        {
            throw new NumberInvalidException("commands.generic.num.invalid", new Object[] {s});
        }
	}

}
