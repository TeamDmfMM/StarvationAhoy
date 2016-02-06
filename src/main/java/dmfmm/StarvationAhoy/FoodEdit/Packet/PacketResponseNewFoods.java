package dmfmm.StarvationAhoy.FoodEdit.Packet;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class PacketResponseNewFoods implements IMessage {

    public ArrayList<ArrayList<Object>> foods = new ArrayList<ArrayList<Object>>();
    public PacketResponseNewFoods() {}

    public PacketResponseNewFoods(ArrayList<ArrayList<Object>> input){
        //SALog.error("THIS HAS BEEN CALLED!");
        this.foods = input;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int carl = ByteBufUtils.readVarInt(buf, 5);
        for(int z=0; z < carl; z++){
            NBTTagCompound tag = ByteBufUtils.readTag(buf);
            if(tag.getKeySet().size() == 6){
                ArrayList<Object> food = new ArrayList<>();
                food.add(GameRegistry.makeItemStack(tag.getString("item"), tag.getInteger("meta"), tag.getInteger("stacksize"), tag.getString("NBT")));
                food.add(tag.getInteger("hunger"));
                food.add(tag.getFloat("saturation"));
                foods.add(food);
            }
        }
        /*ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            foods = (ArrayList<ArrayList<Object>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, foods.size(), 5);
        for(int i=0; i < foods.size(); i++){
            NBTTagCompound info = new NBTTagCompound();
            ArrayList<Object> food = foods.get(i);
            if(food.size() == 3){
                info.setString("item", ((ItemStack)food.get(0)).getItem().getRegistryName());
                info.setInteger("meta", ((ItemStack)food.get(0)).getMetadata());
                info.setInteger("stacksize", ((ItemStack)food.get(0)).stackSize);
                if(((ItemStack) food.get(0)).hasTagCompound()) {
                    info.setString("NBT", ((ItemStack) food.get(0)).getTagCompound().toString());
                }else{
                    info.setString("NBT", "");
                }
                info.setInteger("hunger", (Integer) food.get(1));
                info.setFloat("saturation", (Float) food.get(2));
                ByteBufUtils.writeTag(buf, info);
            }
        }
        /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(foods);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buf.writeBytes(bos.toByteArray());*/
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
