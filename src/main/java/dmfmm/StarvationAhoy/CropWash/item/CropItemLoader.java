package dmfmm.StarvationAhoy.CropWash.item;


import dmfmm.StarvationAhoy.Core.items.GenericItemLoader;


public class CropItemLoader extends GenericItemLoader{

    public CropItemLoader(){
        this.addItem("dirty_item", DirtyItem.class);
    }

}
