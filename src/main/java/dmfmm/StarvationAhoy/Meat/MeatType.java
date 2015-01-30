package dmfmm.StarvationAhoy.Meat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class MeatType {

        public int id;

        public ModelBase deadModel;

        public Class<? extends EntityLiving> entity;

        public static class Textures {

            public ResourceLocation dead;
            public ResourceLocation skinned;
            public ResourceLocation rotten;

        }

        public Textures textures;

        public static class Items {

            public Item meat;
            public Item skin;
            public Item dead;

        }

        public Items items;

        public boolean constructed;

        private boolean type;
        private boolean dead;

        public MeatType(int id) {


            this.id = id;

            constructed = false;

            textures = new Textures();
            items = new Items();
        }


        public void doDeadEntity(Class<? extends EntityLiving> base, Item dead, Item skinned) {

            this.entity = base;
            this.items.meat = dead;
            this.items.skin = skinned;

            this.dead = true;

            if (this.dead == true && this.type == true) {
                constructed = true;
            }

        }

        public void doMeatType(ModelBase modelEntity, String normalTexture, String skinnedTexture, String rottenTexture) {
            
            this.deadModel = modelEntity;
            this.textures.dead = new ResourceLocation(normalTexture);
            this.textures.skinned = new ResourceLocation(skinnedTexture);
            this.textures.rotten = new ResourceLocation(rottenTexture);

            this.type = true;

            if (this.dead == true && this.type == true) {
                constructed = true;
            }
        }


    }