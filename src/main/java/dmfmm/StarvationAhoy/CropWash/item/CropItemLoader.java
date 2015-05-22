package dmfmm.StarvationAhoy.CropWash.item;


import dmfmm.StarvationAhoy.GenericItemLoader;

/**
 * Created by matthew on 22/05/15.
 */
public class CropItemLoader extends GenericItemLoader{

    public CropItemLoader(){
        this.addItem("dirty_item", DirtyItem.class);
    }

}
