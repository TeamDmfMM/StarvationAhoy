package dmfmm.StarvationAhoy.proxy;

import dmfmm.StarvationAhoy.Core.HUD.OverlaySaturationBar;
import dmfmm.StarvationAhoy.Core.Init.SASoundEvent;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
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
    public ModelBiped getArmorModel(ModelBiped model, EntityEquipmentSlot slot) {
        switch(slot){
            case HEAD:
                return model;
            case CHEST:
                return new HTArmor();
            default:
                return null;
        }
    }

    @Override
    public void initSounds() {
        SASoundEvent.init();
    }

    @Override
    public void preInit() {
        //OBJLoader.INSTANCE.addDomain(ModInfo.MOD_ID);
        MinecraftForge.EVENT_BUS.register(new OverlaySaturationBar(Minecraft.getMinecraft()));
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
		mt.doDeadEntity(EntityCow.class, MItemLoader.deadCow, Items.LEATHER, Items.COOKED_BEEF, MItemLoader.skinlessCow);
		ModuleMeat.registry.addMeatType(mt);
		mt = new MeatType(2);
		mt.doMeatType(new ModelPigSA(), "minecraft:textures/entity/pig/pig.png", "starvationahoy:textures/entity/skinnedPig.png", "starvationahoy:textures/entity/rottenPig.png");
		mt.doDeadEntity(EntityPig.class, MItemLoader.deadPig, null, Items.COOKED_PORKCHOP, MItemLoader.skinlessPig);
		ModuleMeat.registry.addMeatType(mt);
		mt = new MeatType(3);
		mt.doMeatType(new ModelChickenSA(), "minecraft:textures/entity/chicken.png", "starvationahoy:textures/entity/skinnedChicken.png", "starvationahoy:textures/entity/rottenChicken.png");
		mt.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, Items.FEATHER, Items.COOKED_CHICKEN, MItemLoader.skinlessChicken);
		ModuleMeat.registry.addMeatType(mt);
        mt = new MeatType(4);
        mt.doMeatType(new ModelSheepSA.ModelSheepSA2(), "starvationahoy:textures/entity/ModelSheep.png", "minecraft:textures/entity/sheep/sheep.png", "starvationahoy:textures/entity/rottenChicken.png");
        mt.doDeadEntity(EntitySheep.class, MItemLoader.deadSheep, Item.getItemFromBlock(Blocks.WOOL), Items.COOKED_MUTTON, MItemLoader.skinlessSheep);
        ModuleMeat.registry.addMeatType(mt);
        mt = new MeatType(5);
        mt.doMeatType(new ModelRabbitSA(), "minecraft:textures/entity/rabbit/brown.png", "starvationahoy:textures/entity/skinnedRabbit.png", "starvationahoy:textures/entity/rottenChicken.png");
        mt.doDeadEntity(EntityRabbit.class, MItemLoader.deadRabbit, Items.RABBIT_HIDE, Items.COOKED_RABBIT, MItemLoader.skinlessRabbit);
        ModuleMeat.registry.addMeatType(mt);
	}


}
