package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import dmfmm.StarvationAhoy.Core.util.SALog;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Mm12 on 2/7/2015.
 */
public class CookerMultiBlock extends MultiBlockStructure{


    public CookerMultiBlock(){

    }

    @Override
    public int[] getPosForBlock(int bPos, int sBPos, int x, int y, int z, World world) {

        if (orient == 0){
            int[] r = new int[3];
            r[0] = (x-sBPos)+bPos;
            r[1] = y;
            r[2] = z;
            return r;
        }
        else {
            int[] r = new int[3];
            r[0] = x;
            r[1] = y;
            r[2] = (z-sBPos)+bPos;
            return r;
        }

    }

    private boolean checkForFire(World w) {

        int[] p2 = getPosForBlock(1, bPos, x, y, z, w);
        int[] p3 = getPosForBlock(2, bPos, x, y, z, w);

        if (w.getBlockState(new BlockPos(p2[0], p2[1] - 1, p2[2])).getBlock() == Blocks.fire) if (w.getBlockState(new BlockPos(p3[0], p3[1] - 1, p3[2])).getBlock() == Blocks.fire) return true;
        else return false;
        return false;
    }

    @Override
    public int bPosMax() { return 4; }

    @Override
    public void onUpdate(World world) {
        super.onUpdate(world);
        if (sharedData.hasKey("CookTime") == false){
            sharedData.setInteger("CookTime", 0);
            //sharedData.setInteger("CookBurn", 0);
        }
        if (sharedData.hasKey("CookBurn") == false){
            sharedData.setInteger("CookBurn", 0);
        }
        if (sharedData.hasKey("RoastingItem")){
            int ctime = sharedData.getInteger("CookTime");
            if (checkForFire(world))ctime+=1;
            //SALog.error(sharedData);
            //SALog.error("Ctime (im cooking): " + ctime);
            if (ctime >= 3000 && ctime < 3900){
                int amt = MathHelper.getRandomIntegerInRange(new Random(), MeatLib.MEAT_LOW_END, MeatLib.MEAT_HIGH_END);
                ItemStack touse = ItemStack.loadItemStackFromNBT(sharedData.getCompoundTag("CookedItem"));
                touse.stackSize = amt;
                if (!(ItemStack.loadItemStackFromNBT(sharedData.getCompoundTag("RoastingItem")).getItem() == touse.getItem())){
                    sharedData.setTag("RoastingItem", touse.writeToNBT(new NBTTagCompound()));
                }

            }
            if (ctime >= 3900){
                int amt = 1;
                ItemStack touse = new ItemStack(Items.bone);
                touse.stackSize = amt;
                if (!(ItemStack.loadItemStackFromNBT(sharedData.getCompoundTag("RoastingItem")).getItem() == Items.bone)){
                    sharedData.setTag("RoastingItem", touse.writeToNBT(new NBTTagCompound()));
                    sharedData.setInteger("CookBurn", 1);
                }
            }
            sharedData.setInteger("CookTime", ctime);
        }

    }
}
