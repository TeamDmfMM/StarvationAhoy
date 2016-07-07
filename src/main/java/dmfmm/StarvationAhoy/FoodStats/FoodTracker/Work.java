package dmfmm.StarvationAhoy.FoodStats.FoodTracker;

import dmfmm.StarvationAhoy.FoodStats.PlayerDiet.Diet;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsManagerServer;
import net.minecraft.util.math.MathHelper;

/**
 * Created by TeamDMFMM on 6/20/2016. Code originally written
 * for Starvatione Ahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class Work {

    private Diet referenceDiet;
    public float deltaDistance = 0;
    public int currentJumps = 0;
    public float lastDistance = -1;
    public int lastJumps = -1;

    private float currentExertion = 0;

    public Work(Diet referenceDiet) {

        this.referenceDiet = referenceDiet;
    }

    public void calculateCurrentExertion() {
        currentExertion = 0;
        currentExertion += calculateBasalMetabolicRate();
        currentExertion += calculateCaloriesFromWalkingDistanceDelta(deltaDistance);
        currentExertion += calculateCaloriesFromTotalJumps(currentJumps);
    }

    public float calculateBasalMetabolicRate() {
        return this.referenceDiet.weight * 11 / (24000 / 5); // Backed by science!! (then again, this is the lazy estimate...)
    }

    public float calculateCaloriesFromWalkingDistanceDelta(float deltaDistance) {
        return (this.referenceDiet.weight / 5 / 5 / 60) * ((deltaDistance / 100) / 5); // More science!!
    }

    public float calculateCaloriesFromTotalJumps(int totalJumps) {
        return (float) (totalJumps * (0.010 * this.referenceDiet.weight));
    }

    public void calculateDeltas(EntityPlayerMP player) {
        StatisticsManagerServer manager = player.getStatFile();
        int currentDistance = manager.readStat(StatList.WALK_ONE_CM) + manager.readStat(StatList.SPRINT_ONE_CM) * 9;
        int currentJumps = manager.readStat(StatList.JUMP);
        if (this.lastDistance == -1) {
            this.lastDistance = currentDistance;
        }
        if (this.lastJumps == -1) {
            this.lastJumps = currentJumps;
        }
        this.currentJumps = currentJumps - this.lastJumps;
        this.deltaDistance = currentDistance - this.lastDistance;
        this.lastJumps = currentJumps;
        this.lastDistance = currentDistance;
    }

    public void applyExertions() {
        float calories = this.currentExertion;
        float fat = 0.0f;
        if (calories > .5){
            fat = calories - .5f;
            calories = .5f;
        }
        this.referenceDiet.saturation -= 1;
        this.referenceDiet.saturation = MathHelper.clamp_int(this.referenceDiet.saturation, 0, 5000);

        calories /= (this.referenceDiet.saturation / 2) + 1;

        this.referenceDiet.calories -= calories / 4;
        this.referenceDiet.fat -= fat / 32;
        this.referenceDiet.fat = Math.max(0, this.referenceDiet.fat);

        float bmr = this.currentExertion / 12; // This seems like a lot, but this just balances the whole system out.
        bmr /= (this.referenceDiet.saturation / 2) + 1;
        this.referenceDiet.nutrient1 -= bmr; this.referenceDiet.nutrient2 -= bmr;

        this.referenceDiet.calculateNutrient();
        this.referenceDiet.calculateWeight();

        // TODO: change hunger bar and add hungriness metrics
    }
}
