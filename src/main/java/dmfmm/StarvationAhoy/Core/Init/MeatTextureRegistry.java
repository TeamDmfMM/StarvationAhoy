package dmfmm.StarvationAhoy.Core.Init;


import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import dmfmm.StarvationAhoy.Core.lib.WashLib;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MeatTextureRegistry {


    public static void initTextures(){
        registerBlock(MeatLib.bMeatHanger, 0);
        registerBlock(MeatLib.Hold_Stick, 0);
        register(MItemLoader.ButcherKnife, 0);
        register(MItemLoader.cookedpigleg, 0);
        register(MItemLoader.filetKnife, 0);
        register(MItemLoader.pigleg, 0);


    }



    private static void registerBlock(String blockName, int meta){
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        Item itemB = GameRegistry.findItem("starvationahoy", blockName);
        renderItem.getItemModelMesher().register(itemB, meta, new ModelResourceLocation("starvationahoy:"+ blockName, "inventory"));
    }

    private static void register(Item item, int meta){
        ItemModelMesher modelRegistry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        modelRegistry.register(item, 0, new ModelResourceLocation(Item.itemRegistry.getNameForObject(item), "inventory"));
    }
}
