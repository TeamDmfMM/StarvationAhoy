package dmfmm.starvationahoy.proxy;

import dmfmm.starvationahoy.meat.MeatType;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
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
        meatType.doDeadEntity(EntityCow.class, MeatItemRegistry.DEAD_COW, Items.LEATHER, Items.COOKED_BEEF, MeatItemRegistry.SKINLESS_COW);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);


        meatType = new MeatType(2);
        meatType.doDeadEntity(EntityPig.class, MeatItemRegistry.DEAD_PIG, null, Items.COOKED_PORKCHOP, MeatItemRegistry.SKINLESS_PIG);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(3);
        meatType.doDeadEntity(EntityChicken.class, MeatItemRegistry.DEAD_CHICKEN, Items.FEATHER, Items.COOKED_CHICKEN,  MeatItemRegistry.SKINLESS_CHICKEN);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(4);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        meatType.doDeadEntity(EntitySheep.class, MeatItemRegistry.DEAD_SHEEP, Item.getItemFromBlock(Blocks.WOOL), Items.COOKED_MUTTON, MeatItemRegistry.SKINLESS_SHEEP);
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(5);
        meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
        meatType.doDeadEntity(EntityRabbit.class, MeatItemRegistry.DEAD_RABBIT, Items.RABBIT_HIDE, Items.COOKED_RABBIT, MeatItemRegistry.SKINLESS_RABBIT);
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
