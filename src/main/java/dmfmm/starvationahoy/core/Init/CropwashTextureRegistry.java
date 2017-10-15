package dmfmm.starvationahoy.core.Init;


import dmfmm.starvationahoy.core.lib.ModInfo;
import dmfmm.starvationahoy.core.lib.WashLib;
import dmfmm.starvationahoy.crops.ModuleCropWash;
import dmfmm.starvationahoy.crops.modelbake.DirtyItemSmartModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class CropwashTextureRegistry {

    public static ModelResourceLocation dirty_item_model = new ModelResourceLocation("starvationahoy:dirty_item_model_token", "inventory");

    public static void initTextures(){
        registerBlock(WashLib.washBarrelName, 0);
        //doDirtyItem();
    }

    public static void preInitTexture(){
        ModelLoaderRegistry.registerLoader(DirtyItemSmartModel.Loader.instance);
    }



    public static void doDirtyItem() {
        ModelLoader.setCustomModelResourceLocation(ModuleCropWash.cropItemLoader.getItem("dirty_item"), 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + "dirty_item", "inventory"));
    }

    private static void registerBlock(String blockName, int meta){
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        Item itemB = Item.REGISTRY.getObject(new ResourceLocation("starvationahoy", blockName));
        renderItem.getItemModelMesher().register(itemB, meta, new ModelResourceLocation("starvationahoy:"+ blockName, "inventory"));
    }

    private static void register(Item item, int meta){
        ItemModelMesher modelRegistry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        modelRegistry.register(item, 0, new ModelResourceLocation((ResourceLocation) Item.REGISTRY.getNameForObject(item), "inventory"));
    }
}
