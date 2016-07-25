package dmfmm.StarvationAhoy.btmstuff.te;

import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.api.Event.MeatCutEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by TeamDMFMM on 7/23/2016. Code originally written
 * for StarvationAhoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class AutoRoasterTE extends TileEntity implements ITickable{

    MeatType type;
    ItemStack stack;

    @Override
    public void update() {
        if(worldObj.getTileEntity(pos.up()) != null && stack != null){
            if(worldObj.getTileEntity(pos.up()) instanceof TileEntityMultiBlock){
                TileEntityMultiBlock te = (TileEntityMultiBlock) worldObj.getTileEntity(pos.up());

                if (te.multiBlockStructure.sharedData.hasKey("CookTime")) {
                    int ctime = te.multiBlockStructure.sharedData.getInteger("CookTime");
                    if (ctime >= 3500) {
                        //Clear Roasting Item
                        if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")) {
                            te.multiBlockStructure.sharedData = new NBTTagCompound();
                            te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos, new BlockPos(te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z), worldObj);
                        }
                        //Adds in new Roaster
                        if (!te.multiBlockStructure.sharedData.hasKey("RoastingItem")){
                        te.multiBlockStructure.sharedData.setTag("RoastingItem", stack.writeToNBT(new NBTTagCompound()));
                        te.multiBlockStructure.sharedData.setTag("CookedItem", new ItemStack(type.items.meat, 1).writeToNBT(new NBTTagCompound()));
                        te.multiBlockStructure.sharedData.setInteger("EntityID", type.id);
                        te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos, new BlockPos(te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z), worldObj);
                        }
                    }
                }

            }
        }
    }

    public void setEntity(ItemStack stack){
        type = ModuleMeat.registry.isSkinnedItem(stack).meat;
        this.stack = stack;
    }
}
