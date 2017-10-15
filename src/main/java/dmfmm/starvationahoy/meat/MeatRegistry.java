package dmfmm.starvationahoy.meat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class MeatRegistry {




    public Map<Integer, MeatType> meatTypeMap = new HashMap<>();

    public MeatType getMeatTypeForId(int id) {
        return meatTypeMap.get(id);
    }

    public boolean meatTypeExistsAndEditable(int id) {

        if (meatTypeMap.containsKey(id)) {
            if (!getMeatTypeForId(id).constructed) {
                return true;
            }
        }
        return false;
    }

    public boolean constructedMeatTypeExists(int id) {
        if (meatTypeExists(id)) {
            if (getMeatTypeForId(id).constructed) {
                return true;
            }
        }
        return false;
    }

    public boolean meatTypeExists(int id) {
        return meatTypeMap.containsKey(id);
    }

    public void newMeatType(int id) {
        meatTypeMap.put(id, new MeatType(id));
    }

    public void addMeatType(MeatType t){
        meatTypeMap.put(t.id, t);
    }
    
    @SideOnly(Side.CLIENT)
    public void onMeatType(int id, ModelBase modelEntity, String normalTexture, String skinnedTexture, String rottenTexture) {

        if (meatTypeExistsAndEditable(id)) {
            getMeatTypeForId(id).doMeatType(modelEntity, normalTexture, skinnedTexture, rottenTexture);
        } else if (meatTypeExists(id)) {
            throw new IllegalArgumentException("The meat type id has already been constructed, therefore it cannot be edited");
        } else {
            newMeatType(id);
            getMeatTypeForId(id).doMeatType(modelEntity, normalTexture, skinnedTexture, rottenTexture);
        }


    }

    public void onDeadEntity(int id, Class<? extends EntityLiving> entity, Item dead, Item skinned, Item meat, Item skin) {
        if (meatTypeExistsAndEditable(id) == true) {
            getMeatTypeForId(id).doDeadEntity(entity, dead, skinned, meat, skin);
        } else if (meatTypeExists(id) == true) {
            throw new IllegalArgumentException("The meat type id has already been constructed, therefore it cannot be edited");
        } else {
            newMeatType(id);
            getMeatTypeForId(id).doDeadEntity(entity, dead, skinned, meat, skin);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public ModelBase getModel(int id) {
        if (constructedMeatTypeExists(id)) {
            return getMeatTypeForId(id).deadModel;
        }
        return null;
    }

    public Collection<Integer> meatIds() {
        Collection<Integer> ids = new ArrayList<>();
        for (MeatType meatType : this.meatTypeMap.values()) {
            ids.add(meatType.id);
        }
        return ids;
    }

    public Class<? extends EntityLiving> getEntity(int id) {
        if (constructedMeatTypeExists(id) == true) {
            return getMeatTypeForId(id).entity;
        }
        return null;
    }

    public MeatReturn overrideFoodDropsFor(EntityLiving entity) {
        for (int id : meatIds()) {
            if (getEntity(id) != null) {
                if (entity.getClass().equals(getEntity(id))){
                    return new MeatReturn(true, getMeatTypeForId(id), id);
                }
            }
        }
        return new MeatReturn(false, null, 0);
    }

    public MeatReturn isMeatItem(ItemStack is){
        for (int id : meatIds()) {
            if (getMeatTypeForId(id) != null) {
                if (Objects.equals(is.getItem().getUnlocalizedName(), getMeatTypeForId(id).items.dead.getUnlocalizedName())){
                    return new MeatReturn(true, getMeatTypeForId(id), id);
                }
            }
        }
        return new MeatReturn(false, null, 0);
    }

    public MeatReturn isSkinnedItem(ItemStack is){
        if (is == null){return new MeatReturn(false, null, 0);}
        for (int id : meatIds()) {
            if (getMeatTypeForId(id) != null) {
                if (getMeatTypeForId(id).items.skinned != null) {
                    if (is.getItem().getUnlocalizedName().equals(getMeatTypeForId(id).items.skinned.getUnlocalizedName())) {
                        return new MeatReturn(true, getMeatTypeForId(id), id);
                    }
                }
            }
        }
        return new MeatReturn(false, null, 0);
    }

    public MeatReturn isCookedItem(ItemStack is){
        if (is == null){return new MeatReturn(false, null, 0);}
        for (int id : meatIds()) {
            if (getMeatTypeForId(id) != null) {
                if (getMeatTypeForId(id).items.meat != null) {
                    if (is.getItem().getUnlocalizedName().equals(getMeatTypeForId(id).items.meat.getUnlocalizedName())) {
                        return new MeatReturn(true, getMeatTypeForId(id), id);
                    }
                }
            }
        }
        return new MeatReturn(false, null, 0);
    }

    public static class MeatReturn {

        public boolean value;
        public MeatType meat;
        public int meatID;

        public MeatReturn(boolean value, MeatType meat, int meatID) {


            this.value = value;
            this.meat = meat;
            this.meatID = meatID;
        }



    }


}