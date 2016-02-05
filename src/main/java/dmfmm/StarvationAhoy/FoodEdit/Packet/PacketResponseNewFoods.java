package dmfmm.StarvationAhoy.FoodEdit.Packet;

import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class PacketResponseNewFoods implements IMessage {

    public ArrayList<ArrayList<Object>> foods;
    public PacketResponseNewFoods() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            foods = (ArrayList<ArrayList<Object>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(foods);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buf.writeBytes(bos.toByteArray());
    }


    public static class Handler implements IMessageHandler<PacketResponseNewFoods, IMessage> {

        @Override
        public IMessage onMessage(final PacketResponseNewFoods message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    KnownFoods.swapToServer(message.foods);
                }
            });
            return null;
        }
    }
}
