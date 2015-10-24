package dmfmm.StarvationAhoy.api.Meat;

import net.minecraft.client.model.ModelBase;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public interface ISpitRoastRender {


    /**
     * Takes the input model, and changes rotation to look good on spit roast
     *
     *
     * @param change old horrible model
     *
     * @return new updated model
     */
    public ModelBase updateExistingModel(ModelBase change);

    /**
     * Gets the desired translation to make model not hover around spit roast
     *
     *
     * @return the translations: xoffset, yoffset, zoffset, center translation (size of model around y axis / 2)
     */
    public float[] getTranslations();

}
