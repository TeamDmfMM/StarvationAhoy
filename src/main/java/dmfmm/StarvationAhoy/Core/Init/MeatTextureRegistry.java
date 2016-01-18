package dmfmm.StarvationAhoy.Core.Init;


import dmfmm.StarvationAhoy.Core.lib.MeatLib;
import dmfmm.StarvationAhoy.Core.lib.ModInfo;
import dmfmm.StarvationAhoy.Core.lib.WashLib;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
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

    public static void preinitTextures(){
        registerItemModel(MItemLoader.deadPig, 0, "Pig", "type=pig");
        registerItemModel(MItemLoader.skinlessPig, 0, "Pig", "type=skinned_pig");
        registerItemModel(MItemLoader.deadCow, 0, "Cow", "type=cow");
        registerItemModel(MItemLoader.skinlessCow, 0, "Cow", "type=skinned_cow");
        registerItemModel(MItemLoader.deadChicken, 0, "Chicken", "type=chicken");
        registerItemModel(MItemLoader.skinlessChicken, 0, "Chicken", "type=skinned_chicken");
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

    public static void registerItemModel(final Item item, int meta, final String itemName)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MOD_ID + ":" + itemName, "inventory"));
    }

    public static void registerItemModel(final Item item, int meta, final String jsonName, final String variantName)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MOD_ID + ":" + jsonName, variantName));
    }
}
