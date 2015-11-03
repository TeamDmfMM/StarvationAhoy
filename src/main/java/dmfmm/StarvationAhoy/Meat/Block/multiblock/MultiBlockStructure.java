package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Matthew on 2/6/2015.
 */
public abstract class MultiBlockStructure {

    public MultiBlockStructure(){

    }

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

    public void updateStructure(World world){
        if (bPos != 0){
            //SALog.error("Err.. Whoopsies! Im outa here! " + world.isRemote + bPos);
            return;
        }
        NBTTagCompound oldSha = sharedData;
        if (true) {onUpdate(world);}
        //if (sharedData != oldSha){
            //SALog.error("im making lots o lag!");
            syncData(this, this.bPos, this.x, this.y, this.z, world);


    }

    public abstract int[] getPosForBlock(int bPos, int sBPos, int x, int y, int z, World world);

    public abstract int bPosMax();

    public void syncData(MultiBlockStructure structToSync, int bPos, int x, int y, int z, World world){

        for (int blockPos = 0; blockPos < bPosMax(); blockPos++) {
            int[] bPosFor = getPosForBlock(blockPos, bPos, x, y, z, world);

            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(bPosFor[0], bPosFor[1], bPosFor[2]);
            if (te == null){return;}
            MultiBlockStructure struct = te.multiBlockStructure;
            struct.sharedData = structToSync.sharedData;
            te.onSync();
        }

    }
    public void destroy(World world){
        for (int blockPos = 0; blockPos < bPosMax(); blockPos++) {
            int[] bPosFor = getPosForBlock(blockPos, bPos, x, y, z, world);

            world.setBlockToAir(bPosFor[0], bPosFor[1], bPosFor[2]);
        }
    }

}
