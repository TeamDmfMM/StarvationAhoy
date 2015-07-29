package dmfmm.StarvationAhoy.proxy;

import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;

public class ServerProxy extends CommonProxy{

	@Override
	public void registerKeyBindings() {
		// NOOP
		
	}

	@Override
	public void registerRenderers() {
		// NO-OP

	}


    public void registerMeatTypes(){
        MeatType meatType = new MeatType(1);
        meatType.doDeadEntity(EntityCow.class, MItemLoader.deadCow, Items.leather, Items.cooked_beef, MItemLoader.skinlessCow);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);


        meatType = new MeatType(2);
        meatType.doDeadEntity(EntityPig.class, MItemLoader.deadPig, null, Items.cooked_porkchop, MItemLoader.skinlessPig);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(3);
        meatType.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, Items.feather, Items.cooked_chicken,  MItemLoader.skinlessChicken);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);
    }


    public ModelBiped getArmorModel(int type) {
        return null;
    }


}
