package dmfmm.StarvationAhoy.Meat.Block.multiblock.net;

import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by mincrmatt12 on 3/17/2015.
 */
public class PacketMultiBlock implements IMessage {

    private int multiBlockIndex;
    private int multiBlockOrient;
    private NBTTagCompound multiBlockShared;
    private int x;
    private int y;
    private int z;

    public PacketMultiBlock(){

    }

    public PacketMultiBlock(int MultiBlockIndex, int MultiBlockOrient, NBTTagCompound MultiBlockShared, int x, int y, int z){

        multiBlockIndex = MultiBlockIndex;
        multiBlockOrient = MultiBlockOrient;

        multiBlockShared = MultiBlockShared;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = ByteBufUtils.readVarInt(buf, 5);
        y = ByteBufUtils.readVarInt(buf, 5);
        z = ByteBufUtils.readVarInt(buf, 5);
        multiBlockIndex = ByteBufUtils.readVarInt(buf, 5);
        multiBlockOrient = ByteBufUtils.readVarInt(buf, 5);
        multiBlockShared = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, x, 5);
        ByteBufUtils.writeVarInt(buf, y, 5);
        ByteBufUtils.writeVarInt(buf, z, 5);
        ByteBufUtils.writeVarInt(buf, multiBlockIndex, 5);
        ByteBufUtils.writeVarInt(buf, multiBlockOrient, 5);
        ByteBufUtils.writeTag(buf, multiBlockShared);
    }

    public static class Handler implements IMessageHandler<PacketMultiBlock, IMessage> {


        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketMultiBlock message, MessageContext ctx) {

            //SALog.error("I got sent :)");
            World w = Minecraft.getMinecraft().theWorld;
            if (!w.isAirBlock(new BlockPos(message.x, message.y, message.z))){

                TileEntityMultiBlock te = (TileEntityMultiBlock) w.getTileEntity(new BlockPos(message.x, message.y, message.z));
                if (te == null){return null;}
                if (te.multiBlockStructure == null) {
                    try {
                        te.multiBlockStructure = te.getMultiBlock().newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                    te.multiBlockStructure.bPos = message.multiBlockIndex;
                    te.multiBlockStructure.orient = message.multiBlockOrient;
                    te.multiBlockStructure.sharedData = message.multiBlockShared;
                    te.multiBlockStructure.x = message.x;
                    te.multiBlockStructure.y = message.y;
                    te.multiBlockStructure.z = message.z;
                te.r = true;
                    //SALog.error("I set some data");


            }
            return null;
        }
    }
}
