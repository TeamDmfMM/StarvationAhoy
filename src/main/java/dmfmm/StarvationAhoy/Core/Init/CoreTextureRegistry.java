package dmfmm.StarvationAhoy.Core.Init;


import dmfmm.StarvationAhoy.Core.items.ItemLoad;
import dmfmm.StarvationAhoy.Core.lib.WashLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoreTextureRegistry {

    public static void initTextures(){
        register(ItemLoad.HungerPotion, 0);
        register(ItemLoad.stat_chest, 0);
        register(ItemLoad.stat_helm, 0);
        register(ItemLoad.infoBook, 0);

    }





    private static void registerBlock(String blockName, int meta){
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        Item itemB = GameRegistry.findItem("starvationahoy", blockName);
        renderItem.getItemModelMesher().register(itemB, meta, new ModelResourceLocation("astarvationahoy:"+ blockName, "inventory"));
    }

    private static void register(Item item, int meta){
        ItemModelMesher modelRegistry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        modelRegistry.register(item, 0, new ModelResourceLocation((ResourceLocation) Item.itemRegistry.getNameForObject(item), "inventory"));
    }
}
