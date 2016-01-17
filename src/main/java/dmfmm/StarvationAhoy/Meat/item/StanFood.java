package dmfmm.StarvationAhoy.Meat.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import dmfmm.StarvationAhoy.Core.SATabs;
import net.minecraft.item.ItemFood;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */

//Copied from Extra Food 1.7.10 (I own the mod)
public class StanFood extends ItemFood {


    public StanFood(int foodBar, float saturation, boolean WolfFood){
        super(foodBar, saturation, WolfFood);
        this.setCreativeTab(SATabs.INSTANCE);
    }
    public StanFood(int foodBar, float saturation){
        super(foodBar, saturation, false);
        this.setCreativeTab(SATabs.INSTANCE);
    }


    //TODO ADD IN TEXTURE HANDLING
    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("starvationahoy:" + this.getUnlocalizedName().substring(5));
    }*/




}