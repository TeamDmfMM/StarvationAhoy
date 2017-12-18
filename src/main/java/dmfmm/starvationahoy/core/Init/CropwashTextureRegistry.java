package dmfmm.starvationahoy.core.Init;


import dmfmm.starvationahoy.core.lib.ModInfo;
import dmfmm.starvationahoy.core.lib.WashLib;
import dmfmm.starvationahoy.core.util.SALog;
import dmfmm.starvationahoy.crops.ModuleCropWash;
import dmfmm.starvationahoy.crops.modelbake.DirtyItemSmartModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class CropwashTextureRegistry {

    public static ModelResourceLocation dirty_item_model = new ModelResourceLocation(ModInfo.MOD_ID + ":dirty_item", "inventory");

    public static void initTextures(){
        registerBlock(WashLib.washBarrelName, 0);
        //doDirtyItem();
    }

    public static void preInitTexture(){
        ModelLoaderRegistry.registerLoader(DirtyItemSmartModel.Loader.instance);
    }



    public static void doDirtyItem() {
        SALog.error("HI");
        ModelLoader.setCustomModelResourceLocation(ModuleCropWash.cropItemLoader.getItem("dirty_item"), 0, dirty_item_model);
    }

    private static void registerBlock(String blockName, int meta){
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        Item itemB = Item.REGISTRY.getObject(new ResourceLocation("starvationahoy", blockName));
        renderItem.getItemModelMesher().register(itemB, meta, new ModelResourceLocation("starvationahoy:"+ blockName, "inventory"));
    }

}
