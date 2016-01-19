package dmfmm.StarvationAhoy.proxy;

import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import dmfmm.StarvationAhoy.Client.Renderer.*;
import dmfmm.StarvationAhoy.CropWash.Block.tilentity.TileEntityCropWasher;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.HoldingStickTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.tileentity.MeatHangerTileEntity;
import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import org.lwjgl.input.Keyboard;


public class ClientProxy extends CommonProxy{

    @Override
    public ModelBiped getArmorModel(int type) {
        switch(type){
            case 0:
                return null;
            case 1:
                return new HTArmor();
            default:
                return null;
        }
    }


	@Override
	public void registerKeyBindings() {

		debugKey = new KeyBinding("starvationahoy.key.dietdebug.desc", Keyboard.KEY_F4, "starvationahoy.key.category");
		ClientRegistry.registerKeyBinding(debugKey);

	}
	public void registerRenderers() {
        //Blocks
        ClientRegistry.bindTileEntitySpecialRenderer(MeatHangerTileEntity.class, new MeatHangerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(HoldingStickTileEntity.class, new HoldingStickRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCropWasher.class, new WashBarrelRenderer());
        /*
        //Cow Items
        MinecraftForgeClient.registerItemRenderer(MItemLoader.deadPig, new QuadrupedItemRenderer(new ModelPigSA(), "minecraft:textures/entity/pig/pig.png"));
        MinecraftForgeClient.registerItemRenderer(MItemLoader.skinlessPig, new QuadrupedItemRenderer(new ModelPigSA(), "starvationahoy:textures/entity/skinnedPig.png"));
        //Pig Items
        MinecraftForgeClient.registerItemRenderer(MItemLoader.deadCow, new QuadrupedItemRenderer(new ModelCowSA(), "minecraft:textures/entity/cow/cow.png"));
        MinecraftForgeClient.registerItemRenderer(MItemLoader.skinlessCow, new QuadrupedItemRenderer(new ModelCowSA(), "starvationahoy:textures/entity/skinnedCow.png"));
        //Chicken Items
        MinecraftForgeClient.registerItemRenderer(MItemLoader.deadChicken, new BipedItemRenderer(new ModelChickenSA(), "minecraft:textures/entity/chicken.png"));
        MinecraftForgeClient.registerItemRenderer(MItemLoader.skinlessChicken, new BipedItemRenderer(new ModelChickenSA(), "starvationahoy:textures/entity/skinnedChicken.png"));
        */
    }


    public void registerMeatTypes(){

		MeatType mt = new MeatType(1);
		mt.doMeatType(new ModelCowSA(), "minecraft:textures/entity/cow/cow.png", "starvationahoy:textures/entity/skinnedCow.png", "starvationahoy:textures/entity/rottenCow.png");
		mt.doDeadEntity(EntityCow.class, MItemLoader.deadCow, Items.leather, Items.cooked_beef, MItemLoader.skinlessCow);
		ModuleMeat.registry.addMeatType(mt);
		mt = new MeatType(2);
		mt.doMeatType(new ModelPigSA(), "minecraft:textures/entity/pig/pig.png", "starvationahoy:textures/entity/skinnedPig.png", "starvationahoy:textures/entity/rottenPig.png");
		mt.doDeadEntity(EntityPig.class, MItemLoader.deadPig, null, Items.cooked_porkchop, MItemLoader.skinlessPig);
		ModuleMeat.registry.addMeatType(mt);
		mt = new MeatType(3);
		mt.doMeatType(new ModelChickenSA(), "minecraft:textures/entity/chicken.png", "starvationahoy:textures/entity/skinnedChicken.png", "starvationahoy:textures/entity/rottenChicken.png");
		mt.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, Items.feather, Items.cooked_chicken, MItemLoader.skinlessChicken);
		ModuleMeat.registry.addMeatType(mt);
        mt = new MeatType(4);
        mt.doMeatType(new ModelSheepSA(), "starvationahoy:textures/entity/ModelSheep.png", "minecraft:textures/entity/sheep/sheep.png", "starvationahoy:textures/entity/rottenChicken.png");
        mt.doDeadEntity(EntitySheep.class, MItemLoader.deadSheep, Item.getItemFromBlock(Blocks.wool), Items.cooked_mutton, MItemLoader.skinlessSheep);
        ModuleMeat.registry.addMeatType(mt);
        mt = new MeatType(5);
        mt.doMeatType(new ModelRabbitSA(), "minecraft:textures/entity/rabbit/brown.png", "starvationahoy:textures/entity/skinnedChicken.png", "starvationahoy:textures/entity/rottenChicken.png");
        mt.doDeadEntity(EntityRabbit.class, MItemLoader.deadRabbit, Items.rabbit_hide, Items.cooked_rabbit, MItemLoader.skinlessRabbit);
        ModuleMeat.registry.addMeatType(mt);
	}


}
