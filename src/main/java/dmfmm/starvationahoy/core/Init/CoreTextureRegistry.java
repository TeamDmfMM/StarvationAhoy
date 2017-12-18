package dmfmm.starvationahoy.core.Init;


import dmfmm.starvationahoy.core.items.ItemLoad;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class CoreTextureRegistry {

    public static void initTextures(){
        register(ItemLoad.HungerPotion, 0);
        register(ItemLoad.stat_chest, 0);
        register(ItemLoad.stat_helm, 0);
        register(ItemLoad.infoBook, 0);

    }


    private static void register(Item item, int meta){
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
