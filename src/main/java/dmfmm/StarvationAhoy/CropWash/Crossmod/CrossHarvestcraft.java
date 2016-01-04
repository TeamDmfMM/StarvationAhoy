package dmfmm.StarvationAhoy.CropWash.Crossmod;

import com.pam.harvestcraft.BlockRegistry;
import com.pam.harvestcraft.ItemRegistry;
import cpw.mods.fml.common.Loader;
import dmfmm.StarvationAhoy.CropWash.DirtyBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;


public class CrossHarvestcraft {


    public static void load(){
        if (isLoaded()) {
            AddIntergration();//
        }
    }

    public static boolean isLoaded(){
        return Loader.isModLoaded("harvestcraft");//
    }


    private static void AddIntergration(){
        if(!BlockRegistry.enablecropspecialplanting) {
            replace(BlockRegistry.pamcranberryCrop, ItemRegistry.cranberryseedItem);
            replace(BlockRegistry.pamriceCrop, ItemRegistry.riceseedItem);
            replace(BlockRegistry.pamseaweedCrop, ItemRegistry.seaweedseedItem);//
        } else {
            replace(BlockRegistry.pamcranberryCrop, ItemRegistry.cranberryItem);
            replace(BlockRegistry.pamriceCrop, ItemRegistry.riceItem);
            replace(BlockRegistry.pamseaweedCrop, ItemRegistry.seaweedItem);
            replace(BlockRegistry.pamwaterchestnutCrop, ItemRegistry.waterchestnutItem);//
        }
        if(!BlockRegistry.cropsdropSeeds) {
            replace(BlockRegistry.pamblackberryCrop, ItemRegistry.blackberryItem);
            replace(BlockRegistry.pamblueberryCrop, ItemRegistry.blueberryItem);
            replace(BlockRegistry.pamcandleberryCrop, ItemRegistry.candleberryItem);
            replace(BlockRegistry.pamraspberryCrop, ItemRegistry.raspberryItem);
            replace(BlockRegistry.pamstrawberryCrop, ItemRegistry.strawberryItem);
            replace(BlockRegistry.pamgrapeCrop, ItemRegistry.grapeItem);
            replace(BlockRegistry.pamcactusfruitCrop, ItemRegistry.cactusfruitItem);
            replace(BlockRegistry.pamasparagusCrop, ItemRegistry.asparagusItem);
            replace(BlockRegistry.pambarleyCrop, ItemRegistry.barleyItem);
            replace(BlockRegistry.pamoatsCrop, ItemRegistry.oatsItem);
            replace(BlockRegistry.pamryeCrop, ItemRegistry.ryeItem);
            replace(BlockRegistry.pamcornCrop, ItemRegistry.cornItem);
            replace(BlockRegistry.pambambooshootCrop, ItemRegistry.bambooshootItem);
            replace(BlockRegistry.pamcantaloupeCrop, ItemRegistry.cantaloupeItem);
            replace(BlockRegistry.pamcucumberCrop, ItemRegistry.cucumberItem);
            replace(BlockRegistry.pamwintersquashCrop, ItemRegistry.wintersquashItem);
            replace(BlockRegistry.pamzucchiniCrop, ItemRegistry.zucchiniItem);
            replace(BlockRegistry.pambeetCrop, ItemRegistry.beetItem);
            replace(BlockRegistry.pamonionCrop, ItemRegistry.onionItem);
            replace(BlockRegistry.pamparsnipCrop, ItemRegistry.parsnipItem);
            replace(BlockRegistry.pampeanutCrop, ItemRegistry.peanutItem);
            replace(BlockRegistry.pamradishCrop, ItemRegistry.radishItem);
            replace(BlockRegistry.pamrutabagaCrop, ItemRegistry.rutabagaItem);
            replace(BlockRegistry.pamsweetpotatoCrop, ItemRegistry.sweetpotatoItem);
            replace(BlockRegistry.pamturnipCrop, ItemRegistry.turnipItem);
            replace(BlockRegistry.pamrhubarbCrop, ItemRegistry.rhubarbItem);
            replace(BlockRegistry.pamceleryCrop, ItemRegistry.celeryItem);
            replace(BlockRegistry.pamgarlicCrop, ItemRegistry.garlicItem);
            replace(BlockRegistry.pamgingerCrop, ItemRegistry.gingerItem);
            replace(BlockRegistry.pamspiceleafCrop, ItemRegistry.spiceleafItem);
            replace(BlockRegistry.pamtealeafCrop, ItemRegistry.tealeafItem);
            replace(BlockRegistry.pamcoffeebeanCrop, ItemRegistry.coffeebeanItem);
            replace(BlockRegistry.pammustardseedsCrop, ItemRegistry.mustardseedsItem);
            replace(BlockRegistry.pambroccoliCrop, ItemRegistry.broccoliItem);
            replace(BlockRegistry.pamcauliflowerCrop, ItemRegistry.cauliflowerItem);
            replace(BlockRegistry.pamleekCrop, ItemRegistry.leekItem);
            replace(BlockRegistry.pamlettuceCrop, ItemRegistry.lettuceItem);
            replace(BlockRegistry.pamscallionCrop, ItemRegistry.scallionItem);
            replace(BlockRegistry.pamartichokeCrop, ItemRegistry.artichokeItem);
            replace(BlockRegistry.pambrusselsproutCrop, ItemRegistry.brusselsproutItem);
            replace(BlockRegistry.pamcabbageCrop, ItemRegistry.cabbageItem);
            replace(BlockRegistry.pamspinachCrop, ItemRegistry.spinachItem);
            replace(BlockRegistry.pamwhitemushroomCrop, ItemRegistry.whitemushroomItem);
            replace(BlockRegistry.pambeanCrop, ItemRegistry.beanItem);
            replace(BlockRegistry.pamsoybeanCrop, ItemRegistry.soybeanItem);
            replace(BlockRegistry.pambellpepperCrop, ItemRegistry.bellpepperItem);
            replace(BlockRegistry.pamchilipepperCrop, ItemRegistry.chilipepperItem);
            replace(BlockRegistry.pameggplantCrop, ItemRegistry.eggplantItem);
            replace(BlockRegistry.pamokraCrop, ItemRegistry.okraItem);
            replace(BlockRegistry.pampeasCrop, ItemRegistry.peasItem);
            replace(BlockRegistry.pamtomatoCrop, ItemRegistry.tomatoItem);
            replace(BlockRegistry.pamcottonCrop, ItemRegistry.cottonItem);
            replace(BlockRegistry.pampineappleCrop, ItemRegistry.pineappleItem);
            replace(BlockRegistry.pamkiwiCrop, ItemRegistry.kiwiItem);
            replace(BlockRegistry.pamcurryleafCrop, ItemRegistry.curryleafItem);
            replace(BlockRegistry.pamsesameseedsCrop, ItemRegistry.sesameseedsItem);
            replace(BlockRegistry.pamcranberryCrop, ItemRegistry.cranberryItem);
            replace(BlockRegistry.pamriceCrop, ItemRegistry.riceItem);
            replace(BlockRegistry.pamseaweedCrop, ItemRegistry.seaweedItem);
            replace(BlockRegistry.pamwaterchestnutCrop, ItemRegistry.waterchestnutItem);
        }
    }

    private static void replace(Block block, Item... items){
        DirtyBlocks.addReplace(block, items);//
    }

}
