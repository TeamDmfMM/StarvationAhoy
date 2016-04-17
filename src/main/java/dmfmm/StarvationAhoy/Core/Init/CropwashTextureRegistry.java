package dmfmm.StarvationAhoy.Core.Init;


import dmfmm.StarvationAhoy.Core.lib.WashLib;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CropwashTextureRegistry {

    public static ModelResourceLocation dirty_item_model = new ModelResourceLocation("starvationahoy:dirty_item_model_token", "inventory");

    public static void initTextures(){
        registerBlock(WashLib.washBarrelName, 0);
        doDirtyItem();
    }



    private static void doDirtyItem() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(ModuleCropWash.cropItemLoader.getItem("dirty_item"),0,  CropwashTextureRegistry.dirty_item_model);
    }

    private static void registerBlock(String blockName, int meta){
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        Item itemB = Item.itemRegistry.getObject(new ResourceLocation("starvationahoy", blockName));
        renderItem.getItemModelMesher().register(itemB, meta, new ModelResourceLocation("starvationahoy:"+ blockName, "inventory"));
    }

    private static void register(Item item, int meta){
        ItemModelMesher modelRegistry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        modelRegistry.register(item, 0, new ModelResourceLocation((ResourceLocation) Item.itemRegistry.getNameForObject(item), "inventory"));
    }
}
