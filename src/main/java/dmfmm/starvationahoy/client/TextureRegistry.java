package dmfmm.starvationahoy.client;


import dmfmm.starvationahoy.client.modelbake.DirtyItemSmartModel;
import dmfmm.starvationahoy.core.init.CoreItemRegistry;
import dmfmm.starvationahoy.core.lib.ModInfo;
import dmfmm.starvationahoy.crops.init.CropBlockRegistry;
import dmfmm.starvationahoy.crops.init.CropItemRegistry;
import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.init.MeatItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class TextureRegistry {

    public static ModelResourceLocation dirty_item_model = new ModelResourceLocation(ModInfo.MOD_ID + ":dirty_item", "inventory");

    public static void initTextures(){
        register(CoreItemRegistry.HUNGER_POTION);
        register(CoreItemRegistry.STAT_CHEST);
        register(CoreItemRegistry.STAT_HELM);
        register(CoreItemRegistry.INFO_BOOK);

        ModelLoaderRegistry.registerLoader(DirtyItemSmartModel.Loader.instance);
        ModelLoader.setCustomModelResourceLocation(CropItemRegistry.DIRTY_ITEM, 0, dirty_item_model);
        register(CropBlockRegistry.CROP_WASHER);

        ModelLoaderRegistry.registerLoader(dmfmm.starvationahoy.client.OBJLoaderz.INSTANCE);
        registerItemModel(MeatItemRegistry.DEAD_PIG, 0, "Pig", "type=pig");
        registerItemModel(MeatItemRegistry.SKINLESS_PIG, 0, "Pig", "type=skinned_pig");
        registerItemModel(MeatItemRegistry.DEAD_COW, 0, "Cow", "type=cow");
        registerItemModel(MeatItemRegistry.SKINLESS_COW, 0, "Cow", "type=skinned_cow");
        registerItemModel(MeatItemRegistry.DEAD_CHICKEN, 0, "Chicken", "type=chicken");
        registerItemModel(MeatItemRegistry.SKINLESS_CHICKEN, 0, "Chicken", "type=skinned_chicken");
        registerItemModel(MeatItemRegistry.DEAD_SHEEP, 0, "Sheep", "type=sheep");
        registerItemModel(MeatItemRegistry.SKINLESS_SHEEP, 0, "Sheep", "type=skinned_sheep");
        registerItemModel(MeatItemRegistry.DEAD_RABBIT, 0, "Rabbit", "type=rabbit");
        registerItemModel(MeatItemRegistry.SKINLESS_RABBIT, 0, "Rabbit", "type=skinned_rabbit");

        register(MeatBlockRegistry.MEAT_HANGER);
        register(MeatBlockRegistry.HOLDING_STICK);
        register(MeatItemRegistry.BUTCHERS_KNIFE);
        register(MeatItemRegistry.COOKED_PIG_LEG);
        register(MeatItemRegistry.FILET_KNIFE);
        register(MeatItemRegistry.PIG_LEG);
    }


    private static void register(Item item){
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    private static void register(Block block) {
        register(Item.getItemFromBlock(block));
    }

    public static void registerItemModel(final Item item, int meta, final String jsonName, final String variantName)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MOD_ID + ":" + jsonName, variantName));
    }
}
