package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import dmfmm.StarvationAhoy.Meat.Block.HoldingStick;
import dmfmm.StarvationAhoy.Meat.Block.MBlockLoader;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import net.minecraft.world.World;

/**
 * Created by MM12 on 2/7/2015.
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
            w.setBlock(x, y, z, MBlockLoader.Cooker);
        }


    }

    public static boolean checkCookerStructure(World w, int x, int y, int z){

        BC bpos0, bpos1, bpos2, bpos3;

        if (w.getBlock(x, y, z+3) == MBlockLoader.HoldingStick){
            bpos0 = new BC(x, y, z);
            bpos3 = new BC(x, y, z+3);
            bpos2 = new BC(x, y, z+2);
            bpos1 = new BC(x, y, z+1);

        }
        else if (w.getBlock(x, y, z-3) == MBlockLoader.HoldingStick){
            bpos0 = new BC(x, y, z);
            bpos3 = new BC(x, y, z-3);
            bpos2 = new BC(x, y, z-2);
            bpos1 = new BC(x, y, z-1);
        }
        else if (w.getBlock(x+3, y, z) == MBlockLoader.HoldingStick){
            bpos0 = new BC(x, y, z);
            bpos3 = new BC(x+3, y, z);
            bpos2 = new BC(x+2, y, z);
            bpos1 = new BC(x+1, y, z);

        }else if (w.getBlock(x-3, y, z) == MBlockLoader.HoldingStick){
            bpos0 = new BC(x, y, z);
            bpos3 = new BC(x-3, y, z);
            bpos2 = new BC(x-2, y, z);
            bpos1 = new BC(x-1, y, z);
        }
        else {
            return false;
        }

        bpos1.crtn_a(w);
        bpos2.crtn_a(w);

        CookerMultiBlock n = new CookerMultiBlock();
        n.x = bpos0.x;
        n.y = bpos0.y;
        n.z = bpos0.z;
        n.bPos = 0;
        ((HoldingStickTileEntity) w.getTileEntity(bpos0.x, bpos0.y, bpos0.z)).multiBlockStructure = n;

        n = new CookerMultiBlock();
        n.x = bpos1.x;
        n.y = bpos1.y;
        n.z = bpos1.z;
        n.bPos = 1;
        ((CookerTileEntity) w.getTileEntity(bpos1.x, bpos1.y, bpos1.z)).multiBlockStructure = n;

        n = new CookerMultiBlock();
        n.x = bpos2.x;
        n.y = bpos2.y;
        n.z = bpos2.z;
        n.bPos = 2;
        ((CookerTileEntity) w.getTileEntity(bpos2.x, bpos2.y, bpos2.z)).multiBlockStructure = n;

        n = new CookerMultiBlock();
        n.x = bpos3.x;
        n.y = bpos3.y;
        n.z = bpos3.z;
        n.bPos = 3;
        ((HoldingStickTileEntity) w.getTileEntity(bpos3.x, bpos3.y, bpos3.z)).multiBlockStructure = n;

        return true;

    }

}
