package dmfmm.StarvationAhoy.CropWash.Crossmod;


import net.minecraftforge.fml.common.Loader;
import dmfmm.StarvationAhoy.CropWash.DirtyBlocks;
import dmfmm.StarvationAhoy.CropWash.item.DirtyItem;


import java.util.ArrayList;

public class CrossArgicraft {


    public static void load(){
        if (isLoaded()) {
            AddIntergration();//
        }
    }

    public static boolean isLoaded(){
        return Loader.isModLoaded("AgriCraft");//
    }


    private static void AddIntergration() {
        ArrayList<com.InfinityRaider.AgriCraft.blocks.BlockModPlant> croppy = com.InfinityRaider.AgriCraft.init.Crops.crops;

        for(com.InfinityRaider.AgriCraft.blocks.BlockModPlant crops: croppy){
            switch (crops.getAllFruits().size()){
                case 1:
                    DirtyBlocks.addReplace(crops.getBlock(), crops.getAllFruits().get(0).getItem());
                    break;
                case 2:
                    DirtyBlocks.addReplace(crops.getBlock(), crops.getAllFruits().get(0).getItem(), crops.getAllFruits().get(1).getItem());
                    break;
                case 3:
                    DirtyBlocks.addReplace(crops.getBlock(), crops.getAllFruits().get(0).getItem(), crops.getAllFruits().get(1).getItem(), crops.getAllFruits().get(2).getItem());
                    break;
                case 0:
                    break;
                default:
                    DirtyBlocks.addReplace(crops.getBlock(), crops.getAllFruits().get(0).getItem());
                    break;
            }

        }


        for(int z = 0; z< com.InfinityRaider.AgriCraft.init.Crops.crops.size(); z++){
            com.InfinityRaider.AgriCraft.blocks.BlockModPlant plant = com.InfinityRaider.AgriCraft.init.Crops.crops.get(z);
            for(int l =0; l<plant.getAllFruits().size();l++){
                if(!(plant.getAllFruits().get(l).getItem() instanceof DirtyItem)) {
                    plant.products.addProduce(DirtyItem.createDirtyItem(plant.getAllFruits().get(l)));
                    plant.products.removeProduce(plant.getAllFruits().get(l));
                }
            }
        }

    }

}
