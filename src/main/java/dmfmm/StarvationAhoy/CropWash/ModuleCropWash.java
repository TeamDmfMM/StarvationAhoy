package dmfmm.StarvationAhoy.CropWash;

import cpw.mods.fml.relauncher.Side;
import dmfmm.StarvationAhoy.CropWash.item.CropItemLoader;

/**
 * Made by mincrmatt12. Do not copy or you will have to face
 * our legal team (dmf444)
 */
public class ModuleCropWash {

    public static CropItemLoader cropItemLoader;


    public void preinit(){
        cropItemLoader = new CropItemLoader();
    }

    public void init(Side side) {
        cropItemLoader.load();
    }

    public void postinit(){

    }

}
