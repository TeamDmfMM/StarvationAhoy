package dmfmm.StarvationAhoy.FoodEdit.Packet;

import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class PacketRequestNewFoods implements IMessage{

    public PacketRequestNewFoods() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(5);
    }

    public static class Handler implements IMessageHandler<PacketRequestNewFoods, IMessage> {

        @Override
        public IMessage onMessage(PacketRequestNewFoods message, MessageContext ctx) {
            PacketResponseNewFoods p = new PacketResponseNewFoods();
            p.foods = KnownFoods.knownFoods;
            System.out.println("Hello, i am commandblocktron 3000");
            return p;
        }
    }
}
