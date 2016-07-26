package dmfmm.StarvationAhoy.btmstuff.entity;


import dmfmm.StarvationAhoy.CropWash.item.CropItemLoader;
import dmfmm.StarvationAhoy.CropWash.item.DirtyItem;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityDummy extends EntityMob {

    private static final DataParameter<Integer> DUMMY_VARIANT = EntityDataManager.<Integer>createKey(EntityDummy.class, DataSerializers.VARINT);

    public EntityDummy(World world) {
        super(world);
        setSize(1.0F, 2F);
        this.setEntityInvulnerable(true);
        this.setNoAI(true);
    }
/*
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }
*/

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DUMMY_VARIANT, 0);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setType(compound.getInteger("typus"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("typus", this.getType());

    }



    protected boolean canEquipItem(ItemStack stack)
    {
        return true;
    }

    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        if (livingdata == null)
        {
            livingdata = new EntityDummy.GroupData(this.worldObj.rand.nextInt(3 - 0) + 0);
        }
        if(livingdata instanceof EntityDummy.GroupData){
            GroupData LTData = (EntityDummy.GroupData) livingdata;
            this.setType(LTData.type);
            if(LTData.type == 0){
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(MItemLoader.filetKnife));
            }else if(LTData.type == 1){
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(MItemLoader.ButcherKnife));
            }else if(LTData.type == 2){
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, DirtyItem.createDirtyItem(new ItemStack(Items.CARROT)));
            }
        }
        return livingdata;
    }

    public void setType(int varient)
    {
        this.dataManager.set(DUMMY_VARIANT, varient);
    }

    public int getType(){
        return ((Integer)this.dataManager.get(DUMMY_VARIANT)).intValue();
    }


    class GroupData implements IEntityLivingData
    {
        public int type;

        private GroupData(int type)
        {
            this.type = type;
        }
    }
}