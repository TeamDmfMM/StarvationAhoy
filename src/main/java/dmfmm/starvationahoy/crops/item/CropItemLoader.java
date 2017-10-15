package dmfmm.starvationahoy.crops.item;


import dmfmm.starvationahoy.core.items.GenericItemLoader;


public class CropItemLoader extends GenericItemLoader{

    public CropItemLoader(){
        this.addItem("dirty_item", DirtyItem.class);
    }

}
