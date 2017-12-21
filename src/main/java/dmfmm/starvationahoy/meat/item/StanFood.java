package dmfmm.starvationahoy.meat.item;

import dmfmm.starvationahoy.core.SATabs;
import net.minecraft.item.ItemFood;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */

//Copied from Extra Food 1.7.10 (I own the mod)
public class StanFood extends ItemFood {


    public StanFood(int foodBar, float saturation, boolean WolfFood, String name){
        super(foodBar, saturation, WolfFood);
        this.setCreativeTab(SATabs.INSTANCE);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
    public StanFood(int foodBar, float saturation, String name){
        this(foodBar, saturation, false, name);
    }



}