package dmfmm.StarvationAhoy.FoodStats.DietaryHistoryManage;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodStats.FileFormats.ItemListFormat;
import dmfmm.StarvationAhoy.FoodStats.FileManage.SaveFileLoad;
import net.minecraft.item.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DietaryHistory {

    public ArrayList<Item> recentEaten = new ArrayList<>();

    public String playerName = "";
    public UUID playerUUID = null;
    ItemListFormat t;

    public DietaryHistory(String name, UUID uuid){

        this.playerName = name;
        this.playerUUID = uuid;

        recentEaten = new ArrayList<>();

        t = new ItemListFormat(SaveFileLoad.getFileFrom("diet/" + playerUUID.toString()));

        try {
            this.load();
        } catch (IOException e) {
            try {
                this.save();
            } catch (IOException e1) {
                SALog.fatal("Failed to create a user's dietary history. THIS IS BAD!!!");
            }
        }

    }

    public void eatFood(Item food){

        this.recentEaten.add(food);

    }

    public void dump() {
        t.dump();
    }

    public void save() throws IOException {

        t.items = (ArrayList<Item>) recentEaten.clone();
        t.save();
    }

    public void load() throws IOException {

        t.load();
        recentEaten = t.items;
    }






}
