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

        meatType = new MeatType(4);
        meatType.doMeatType(new ModelSheepSA(), "starvationahoy:textures/entity/ModelSheep.png", "minecraft:textures/entity/sheep/sheep.png", "starvationahoy:textures/entity/rottenChicken.png");
        meatType.doDeadEntity(EntitySheep.class, MItemLoader.deadSheep, Item.getItemFromBlock(Blocks.wool), Items.cooked_mutton, MItemLoader.skinlessSheep);
        ModuleMeat.registry.addMeatType(meatType);

        meatType = new MeatType(5);
        meatType.doMeatType(new ModelRabbitSA(), "minecraft:textures/entity/rabbit/brown.png", "starvationahoy:textures/entity/skinnedChicken.png", "starvationahoy:textures/entity/rottenChicken.png");
        meatType.doDeadEntity(EntityRabbit.class, MItemLoader.deadRabbit, Items.rabbit_hide, Items.cooked_rabbit, MItemLoader.skinlessRabbit);
        ModuleMeat.registry.addMeatType(meatType);
    }


    public ModelBiped getArmorModel(int type) {
        return null;
    }


}
