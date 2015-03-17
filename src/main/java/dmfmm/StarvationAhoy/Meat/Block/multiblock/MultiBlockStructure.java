package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Matthew on 2/6/2015.
 */
public abstract class MultiBlockStructure {

    public int x;
    public int y;
    public int z;

    public int bPos;

    public int orient;

    public NBTTagCompound sharedData = new NBTTagCompound();

        public void onUpdate(World w){
            if (bPos != 0) {
                return;

        }
    }

    public void checkAndDoUpdate(World w){if (checkForChanges(w)) updateStructure(w);}

    public boolean checkForChanges(World w){return false;}

    public void updateStructure(World world){}

    public abstract int[] getPosForBlock(int bPos, int sBPos, int x, int y, int z, World world);

    public abstract int bPosMax();

    public void syncData(MultiBlockStructure structToSync, int bPos, int x, int y, int z, World world){

        for (int blockPos = 0; blockPos < bPosMax(); blockPos++) {
            int[] bPosFor = getPosForBlock(blockPos, bPos, x, y, z, world);

            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(bPosFor[0], bPosFor[1], bPosFor[2]);

            MultiBlockStructure struct = te.multiBlockStructure;
            struct.sharedData =structToSync.sharedData;
            te.onSync();
        }

    }

}
