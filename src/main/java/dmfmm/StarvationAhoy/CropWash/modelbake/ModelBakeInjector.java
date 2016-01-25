package dmfmm.StarvationAhoy.CropWash.modelbake;

import dmfmm.StarvationAhoy.proxy.ClientProxy;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
@SideOnly(Side.CLIENT)
public class ModelBakeInjector {

    public static final ModelBakeInjector instance = new ModelBakeInjector();

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {

            Object getter = event.modelRegistry.getObject(ClientProxy.dirty_item_model);

            //if (getter instanceof IBakedModel) {
                IBakedModel existing = (IBakedModel) getter;
                DirtyItemSmartModel custom = new DirtyItemSmartModel();
                event.modelRegistry.putObject(ClientProxy.dirty_item_model, custom);
            //}



    }

}
