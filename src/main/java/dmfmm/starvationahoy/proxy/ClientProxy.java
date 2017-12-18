package dmfmm.starvationahoy.proxy;

import dmfmm.starvationahoy.client.Renderer.*;
import dmfmm.starvationahoy.core.HUD.OverlaySaturationBar;
import dmfmm.starvationahoy.core.Init.CoreTextureRegistry;
import dmfmm.starvationahoy.core.Init.SASoundEvent;
import dmfmm.starvationahoy.crops.Block.tilentity.TileEntityCropWasher;
import dmfmm.starvationahoy.meat.MeatType;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.meat.block.tileentity.HoldingStickTileEntity;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerTileEntity;
import dmfmm.starvationahoy.meat.item.MItemLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{

    private HTArmor armorModel = new HTArmor();

    @Override
    public ModelBiped getArmorModel(ModelBiped model, EntityEquipmentSlot slot) {
        switch(slot){
            case HEAD:
                return model;
            case CHEST:
                return armorModel;
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
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Override
	public void registerKeyBindings() {

		debugKey = new KeyBinding("starvationahoy.key.dietdebug.desc", Keyboard.KEY_F4, "starvationahoy.key.category");
		ClientRegistry.registerKeyBinding(debugKey);

	}
	public void registerRenderers() {
        //blocks
        ClientRegistry.bindTileEntitySpecialRenderer(MeatHangerTileEntity.class, new MeatHangerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(HoldingStickTileEntity.class, new HoldingStickRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCropWasher.class, new WashBarrelRenderer());
    }


    public void registerMeatTypes(){

		MeatType mt = new MeatType(1);
		mt.doMeatType(new ModelCowSA(), "minecraft:textures/entity/cow/cow.png", "starvationahoy:textures/entity/skinned_cow.png", "starvationahoy:textures/entity/rottenCow.png");
		mt.doDeadEntity(EntityCow.class, MItemLoader.deadCow, Items.LEATHER, Items.COOKED_BEEF, MItemLoader.skinlessCow);
		ModuleMeat.registry.addMeatType(mt);
		mt = new MeatType(2);
		mt.doMeatType(new ModelPigSA(), "minecraft:textures/entity/pig/pig.png", "starvationahoy:textures/entity/skinned_pig.png", "starvationahoy:textures/entity/rottenPig.png");
		mt.doDeadEntity(EntityPig.class, MItemLoader.deadPig, null, Items.COOKED_PORKCHOP, MItemLoader.skinlessPig);
		ModuleMeat.registry.addMeatType(mt);
		mt = new MeatType(3);
		mt.doMeatType(new ModelChickenSA(), "minecraft:textures/entity/chicken.png", "starvationahoy:textures/entity/skinned_chicken.png", "starvationahoy:textures/entity/rottenChicken.png");
		mt.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, Items.FEATHER, Items.COOKED_CHICKEN, MItemLoader.skinlessChicken);
		ModuleMeat.registry.addMeatType(mt);
        mt = new MeatType(4);
        mt.doMeatType(new ModelSheepSA.ModelSheepSA2(), "starvationahoy:textures/entity/model_sheep.png", "minecraft:textures/entity/sheep/sheep.png", "starvationahoy:textures/entity/rottenChicken.png");
        mt.doDeadEntity(EntitySheep.class, MItemLoader.deadSheep, Item.getItemFromBlock(Blocks.WOOL), Items.COOKED_MUTTON, MItemLoader.skinlessSheep);
        ModuleMeat.registry.addMeatType(mt);
        mt = new MeatType(5);
        mt.doMeatType(new ModelRabbitSA(), "minecraft:textures/entity/rabbit/brown.png", "starvationahoy:textures/entity/skinned_rabbit.png", "starvationahoy:textures/entity/rottenChicken.png");
        mt.doDeadEntity(EntityRabbit.class, MItemLoader.deadRabbit, Items.RABBIT_HIDE, Items.COOKED_RABBIT, MItemLoader.skinlessRabbit);
        ModuleMeat.registry.addMeatType(mt);
	}


	@SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        CoreTextureRegistry.initTextures();
    }
}
