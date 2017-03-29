package dmfmm.StarvationAhoy.Core;

import dmfmm.StarvationAhoy.FoodEdit.FoodSet.FoodChanger;
import dmfmm.StarvationAhoy.FoodEdit.Packet.PacketFoodUpdate;
import dmfmm.StarvationAhoy.StarvationAhoy;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

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
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "editfoodval";
	}

	@Override
	public String getUsage(ICommandSender p_71518_1_) {
		return "/sa <item> [new Hunger Value] [new Saturation]";
	}

	@Override
	public List getAliases() {
		return this.others;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] CMDin) throws WrongUsageException, NumberInvalidException {
        if (CMDin.length < 3)
        {
            throw new WrongUsageException(getUsage(null), new Object[0]);
        } else {
			//SALog.error("Hihihihihihihi " + CMDin);
        	Item item = CommandBase.getItemByText(sender, CMDin[0]);
        	int hunger = CommandBase.parseInt(CMDin[1]);
        	float saturation = this.parseFloat(sender, CMDin[2]);

			if (server.isSinglePlayer()) {
				try {
					FoodChanger.change(item, hunger, saturation);
					sender.sendMessage(new TextComponentString(I18n.format(item.getUnlocalizedName() + ".name") + " was sucessfully changed to the new levels!"));
				} catch (IOError | IOException e) {
					throw new WrongUsageException(e.getMessage(), new Object[0]);
				} catch (FoodChanger.FoodNotFoundException e){
					throw new WrongUsageException("Food not found in config file to change.", new Object[0]);
				}
			}else{

				try {
					FoodChanger.change(item, hunger, saturation);
					StarvationAhoy.MultiBlockChannel.sendToAll(new PacketFoodUpdate(new ItemStack(item), hunger, saturation));
				} catch (IOException | FoodChanger.FoodNotFoundException e) {
					e.printStackTrace();
				}
			}

        }
		
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return sender.canUseCommand(2, this.getName());
	}



	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		return args.length == 1 ? CommandBase.getListOfStringsMatchingLastWord(args, Item.REGISTRY.getKeys()) : null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

	
	private static float parseFloat(ICommandSender s, String string) throws NumberInvalidException {
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
