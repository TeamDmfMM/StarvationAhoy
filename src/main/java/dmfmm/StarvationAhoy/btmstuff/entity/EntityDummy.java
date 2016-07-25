package dmfmm.StarvationAhoy.btmstuff.entity;


import dmfmm.StarvationAhoy.CropWash.item.CropItemLoader;
import dmfmm.StarvationAhoy.CropWash.item.DirtyItem;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityDummy extends EntityMob {

    private int type;

    public EntityDummy(World world) {
        super(world);
        setSize(1.0F, 1.5F);
    }
/*
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }


    @Override
    protected void entityInit() {

    }*/

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        type = compound.getInteger("typus");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("typus", type);

    }

    public int getType(){
        return type;
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




    class GroupData implements IEntityLivingData
    {
        public int type;

        private GroupData(int type)
        {
            this.type = type;
        }
    }
}
