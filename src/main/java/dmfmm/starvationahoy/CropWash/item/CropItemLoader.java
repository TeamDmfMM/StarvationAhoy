package dmfmm.starvationahoy.CropWash.item;


import dmfmm.starvationahoy.Core.items.GenericItemLoader;


public class CropItemLoader extends GenericItemLoader{

    public CropItemLoader(){
        this.addItem("dirty_item", DirtyItem.class);
    }

}
