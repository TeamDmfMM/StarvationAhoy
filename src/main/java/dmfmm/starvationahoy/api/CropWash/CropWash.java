package dmfmm.starvationahoy.api.CropWash;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class CropWash {

    public static final String REPLACE_ACTION = "cropwash-add-replace";

    /**
     *
     * To register a washable item make sure the item drops from a block (eg. seeds, potatos, carrots).
     * Add the block that you drop the item from to an NBTTagList
     * Add the item that you want to become washable
     * use FML to send the NBTTagList to Starvation Ahoy
     *
     * Example:
     *              NBTTagCompound tag = new NBTTagCompound();                                              //Create a NBT Tag
     *              NBTTagList nbtlist = new NBTTagList();                                                  //Create a NBT List
     *              nbtlist.appendTag(new ItemStack(Blocks.tallgrass).writeToNBT(new NBTTagCompound()));    //Send block that drops item
     *              nbtlist.appendTag(new ItemStack(Items.wheat_seeds).writeToNBT(tag));                    //Send item to make Washable
     *              tag.setTag("add-replace", nbtlist);                                                     //Set the Tag (name it "add-replace" or it won't work)
     *              FMLInterModComms.sendMessage("StarvationAhoy", CropWash.REPLACE_ACTION, tag);           //Send Over FML IMC
     *
     *
     *
     */
}
