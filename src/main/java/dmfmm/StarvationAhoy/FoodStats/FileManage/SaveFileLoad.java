package dmfmm.StarvationAhoy.FoodStats.FileManage;

import net.minecraftforge.common.DimensionManager;

import java.io.File;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class SaveFileLoad {


    public static File getFileFrom(String fname){

        File base = new File(DimensionManager.getCurrentSaveRootDirectory(), "starvationahoy/" + fname);
        return base;

    }





}
