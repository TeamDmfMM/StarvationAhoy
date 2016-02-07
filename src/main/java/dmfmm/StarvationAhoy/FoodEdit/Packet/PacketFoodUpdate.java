package dmfmm.StarvationAhoy.FoodEdit.Packet;


import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFoodUpdate implements IMessage{
    private ItemStack tochange;
    private int hunger;
    private float saturation;


    public PacketFoodUpdate(){
        //NULL
    }

    public PacketFoodUpdate(ItemStack stack, int h, float s){
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

    public static class Handler implements IMessageHandler<PacketFoodUpdate, IMessage> {

        @Override
        public IMessage onMessage(PacketFoodUpdate message, MessageContext ctx) {
            KnownFoods.changeFood(message.tochange, message.hunger, message.saturation);
            return null;
        }
    }
}
