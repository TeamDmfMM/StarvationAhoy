package dmfmm.starvationahoy.crops.crossmod;


import net.minecraftforge.fml.common.Loader;

public class CrossMod {



    public static void init(){
        //TODO: REENABLE THESE CROSSMOD THINGS!

        //CrossArgicraft.load();
    }

    public static void postinit(){

        if ( Loader.isModLoaded("harvestcraft")) {
            CrossHarvestcraft.load();
        }
    }

}
