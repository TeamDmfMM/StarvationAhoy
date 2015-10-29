package dmfmm.StarvationAhoy.FoodStats;

import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.FoodStats.DietaryHistoryManage.DietaryHistory;
import dmfmm.StarvationAhoy.FoodStats.PlayerDiet.Diet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class PlayerInstanceHolder {

    public Map<String, DietaryHistory> dietaryHistoryMap;
    public Map<String, Diet> dietMap;

    public PlayerInstanceHolder(){
        dietaryHistoryMap = new HashMap<>();
        dietMap = new HashMap<>();
    }

    public static PlayerInstanceHolder instance = new PlayerInstanceHolder();

    public void playerEatFood(EntityPlayer player, Item foodtype){
        dietaryHistoryMap.get(player.getGameProfile().getName()).eatFood(foodtype);
        dietMap.get(player.getGameProfile().getName()).eatFood(foodtype);
    }

    public void save(){
        for (DietaryHistory d : dietaryHistoryMap.values()){
            try {
                d.save();
                d.dump();
                //System.out.println("Suc");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Diet d : dietMap.values()){
            try {
                d.save();
                d.dump();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reload() {
        dietaryHistoryMap = new HashMap<>();
        dietMap = new HashMap<>();
        //SALog.info("Reloading PlayerInstanceHolder");
    }

    public void playerJoin(EntityPlayerMP player){

        String playername = player.getGameProfile().getName();
        UUID tingy = player.getGameProfile().getId();

        DietaryHistory diet = new DietaryHistory(playername, tingy);
        Diet d = new Diet(playername, tingy, player.getEntityWorld().rand);

        dietaryHistoryMap.put(playername, diet);
        dietMap.put(playername, d);

        // System.out.println("hello, i have now logged the user " + playername + ", uuid: " + tingy.toString() + " with a new dietaryhistory");

    }

}
