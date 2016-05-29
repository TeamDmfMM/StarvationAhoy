package dmfmm.StarvationAhoy.proxy;

import dmfmm.StarvationAhoy.Client.Renderer.ModelRabbitSA;
import dmfmm.StarvationAhoy.Client.Renderer.ModelSheepSA;
import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

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
        meatType.doDeadEntity(EntityCow.class, MItemLoader.deadCow, Items.LEATHER, Items.COOKED_BEEF, MItemLoader.skinlessCow);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);


        meatType = new MeatType(2);
        meatType.doDeadEntity(EntityPig.class, MItemLoader.deadPig, null, Items.COOKED_PORKCHOP, MItemLoader.skinlessPig);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(3);
        meatType.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, Items.FEATHER, Items.COOKED_CHICKEN,  MItemLoader.skinlessChicken);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(4);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        meatType.doDeadEntity(EntitySheep.class, MItemLoader.deadSheep, Item.getItemFromBlock(Blocks.WOOL), Items.COOKED_MUTTON, MItemLoader.skinlessSheep);
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(5);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        meatType.doDeadEntity(EntityRabbit.class, MItemLoader.deadRabbit, Items.RABBIT_HIDE, Items.COOKED_RABBIT, MItemLoader.skinlessRabbit);
        ModuleMeat.registry.addMeatType(meatType);
    }


    public ModelBiped getArmorModel(ModelBiped modelBiped, EntityEquipmentSlot slot) {
        return null;
    }

    @Override
    public void initSounds() {

    }

    @Override
    public void preInit() {

    }


}
