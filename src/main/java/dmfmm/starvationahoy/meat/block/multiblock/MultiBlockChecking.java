package dmfmm.starvationahoy.meat.block.multiblock;

import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.block.tileentity.HoldingStickTileEntity;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Matthew on 2/7/2015.
 */
public class MultiBlockChecking {

    private static class BC  {

        public int x;
        public int y;
        public int z;
        public BC(int x, int y, int z){

            this.x = x;
            this.y = y;
            this.z = z;
        }

        public void crtn_a(World w){
            w.setBlockState(new BlockPos(x, y, z), MeatBlockRegistry.COOKER.getDefaultState());
        }
        public void crtn_b(World w){
            w.setBlockState(new BlockPos(x, y, z), MeatBlockRegistry.HOLDING_STICK.getDefaultState());
        }

    }

    public static boolean checkCookerStructure(World w, int x, int y, int z){

        BC bpos0, bpos1, bpos2, bpos3;
        int or = 0;

        if (w.getBlockState(new BlockPos(x, y, z + 3)).getBlock() == MeatBlockRegistry.HOLDING_STICK){
            bpos0 = new BC(x, y, z);
            bpos3 = new BC(x, y, z+3);
            bpos2 = new BC(x, y, z+2);
            bpos1 = new BC(x, y, z+1);
            or = 1;
        }
        else if (getBlock(w, x, y, z - 3) == MeatBlockRegistry.HOLDING_STICK){
            bpos3 = new BC(x, y, z);
            bpos0 = new BC(x, y, z-3);
            bpos1 = new BC(x, y, z-2);
            bpos2 = new BC(x, y, z-1);
            or = 1;
        }
        else if (getBlock(w, x + 3, y, z) == MeatBlockRegistry.HOLDING_STICK){
            bpos0 = new BC(x, y, z);
            bpos3 = new BC(x+3, y, z);
            bpos2 = new BC(x+2, y, z);
            bpos1 = new BC(x+1, y, z);

        }else if (getBlock(w, x - 3, y, z) == MeatBlockRegistry.HOLDING_STICK){
            bpos3 = new BC(x, y, z);
            bpos0 = new BC(x-3, y, z);
            bpos1 = new BC(x-2, y, z);
            bpos2 = new BC(x-1, y, z);
        }
        else {
            return false;
        }

        bpos0.crtn_b(w);
        bpos1.crtn_a(w);
        bpos2.crtn_a(w);
        bpos3.crtn_b(w);

        CookerMultiBlock n = new CookerMultiBlock();
        n.x = bpos0.x;
        n.y = bpos0.y;
        n.z = bpos0.z;
        n.bPos = 0;
        n.orient = or;
        ((HoldingStickTileEntity) w.getTileEntity(new BlockPos(bpos0.x, bpos0.y, bpos0.z))).multiBlockStructure = n;

        n = new CookerMultiBlock();
        n.x = bpos1.x;
        n.y = bpos1.y;
        n.z = bpos1.z;
        n.bPos = 1;
        n.orient = or;
        ((CookerTileEntity) w.getTileEntity(new BlockPos(bpos1.x, bpos1.y, bpos1.z))).multiBlockStructure = n;

        n = new CookerMultiBlock();
        n.x = bpos2.x;
        n.y = bpos2.y;
        n.z = bpos2.z;
        n.bPos = 2;
        n.orient = or;
        ((CookerTileEntity) w.getTileEntity(new BlockPos(bpos2.x, bpos2.y, bpos2.z))).multiBlockStructure = n;

        n = new CookerMultiBlock();
        n.x = bpos3.x;
        n.y = bpos3.y;
        n.z = bpos3.z;
        n.bPos = 3;
        n.orient = or;
        ((HoldingStickTileEntity) w.getTileEntity(new BlockPos(bpos3.x, bpos3.y, bpos3.z))).multiBlockStructure = n;

        return true;

    }

    private static Block getBlock(World w, int x, int y, int z){
        return w.getBlockState(new BlockPos(x,y,z)).getBlock();
    }

}
