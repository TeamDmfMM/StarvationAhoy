package dmfmm.StarvationAhoy.FoodStats.FoodTracker;

import com.mojang.authlib.GameProfile;
import dmfmm.StarvationAhoy.FoodStats.PlayerDiet.Diet;
import dmfmm.StarvationAhoy.FoodStats.PlayerInstanceHolder;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.UUID;

/**
 * Created by TeamDMFMM on 6/22/2016. Code originally written
 * for Starvatione Ahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class WorkEvents {

    int tickTimer = 0;

    @SubscribeEvent
    public void doTick(TickEvent.ServerTickEvent tickEvent) {
        tickTimer += 1;
        tickTimer %= 5;
        if (tickTimer != 0) {
            return;
        }
        PlayerInstanceHolder holder = PlayerInstanceHolder.instance;

        for (Diet d : holder.dietMap.values()) {
            WorldServer worldServer = DimensionManager.getWorld(0); // default world
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "FakePlayer");
            FakePlayer fakePlayer = new FakePlayer(worldServer, gameProfile);
            MinecraftServer minecraftServer = fakePlayer.mcServer;
            EntityPlayerMP mp = minecraftServer.getPlayerList().getPlayerByUUID(d.playeruuid);
            d.work.calculateDeltas(mp);
            d.work.calculateCurrentExertion();
            d.work.applyExertions();

            int hungerValue = MathHelper.clamp_int((int) ((d.nutrient / 5) + 0.5), 0, 20);
            mp.getFoodStats().setFoodLevel(hungerValue);
            mp.getFoodStats().setFoodSaturationLevel(hungerValue);
        }

    }

}
