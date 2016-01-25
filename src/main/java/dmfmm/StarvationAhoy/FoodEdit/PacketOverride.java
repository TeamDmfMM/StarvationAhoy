package dmfmm.StarvationAhoy.FoodEdit;

import com.google.gson.GsonBuilder;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */

public class PacketOverride implements  IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler implements IMessageHandler<PacketOverride, PacketOverrideResponse> {

        @Override
        @SideOnly(Side.SERVER)
        public PacketOverrideResponse onMessage(PacketOverride message, MessageContext ctx) {
            return new PacketOverrideResponse(KnownFoods.myKnownFoods);
        }
    }

    public static class PacketOverrideResponse implements IMessage {

        public PacketOverrideResponse(ArrayList<ArrayList<Object>> m) {

            this.m = m;
        }

        ArrayList<ArrayList<Object>> m;

        @SuppressWarnings("unchecked")
        @Override
        public void fromBytes(ByteBuf buf) {
            String re = ByteBufUtils.readUTF8String(buf);
            m = new GsonBuilder().create().fromJson(re, ArrayList.class);
        }

        @Override
        public void toBytes(ByteBuf buf) {
            String re = new GsonBuilder().create().toJson(m);
            ByteBufUtils.writeUTF8String(buf, re);
        }

        public static class Handler implements IMessageHandler<PacketOverrideResponse, IMessage> {
            @SideOnly(Side.CLIENT)
            @Override
            public IMessage onMessage(PacketOverrideResponse message, MessageContext ctx) {
                KnownFoods.joinM(message.m);
                return null;
            }
        }
    }
}