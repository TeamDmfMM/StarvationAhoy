package dmfmm.StarvationAhoy.FoodEdit.Packet;


import dmfmm.StarvationAhoy.FoodEdit.FoodSet.FoodChanger;
import dmfmm.StarvationAhoy.StarvationAhoy;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;

public class PacketServerJsonUpdate implements IMessage{

    private ItemStack tochange;
    private int hunger;
    float saturation;


    public PacketServerJsonUpdate(){
        //NULL
    }

    public PacketServerJsonUpdate(ItemStack stack, int h, float s){
        this.tochange = stack;
        this.hunger = h;
        this.saturation = s;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        tochange = ByteBufUtils.readItemStack(buf);
        hunger = ByteBufUtils.readVarInt(buf, 5);
        saturation = buf.getFloat(3);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, tochange);
        ByteBufUtils.writeVarInt(buf, hunger, 5);
        buf.setFloat(3, saturation);
    }

    public static class Handler implements IMessageHandler<PacketServerJsonUpdate, IMessage>{

        @Override
        public IMessage onMessage(PacketServerJsonUpdate message, MessageContext ctx) {

            try {
                FoodChanger.change(message.tochange.getItem(), message.hunger, message.saturation);
                StarvationAhoy.MultiBlockChannel.sendToAll(new PacketFoodUpdate(message.tochange, message.hunger, message.saturation));
            } catch (IOException | FoodChanger.FoodNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
