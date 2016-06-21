package dmfmm.StarvationAhoy.FoodStats.FoodTracker;

import dmfmm.StarvationAhoy.FoodStats.PlayerDiet.Diet;

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

    private int currentExertion = 0;

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
        return this.referenceDiet.weight * 11; // Backed by science!! (then again, this is the lazy estimate...)
    }

    public float calculateCaloriesFromWalkingDistanceDelta(float deltaDistance) {
        return (this.referenceDiet.weight / 5 / 5 / 60) * (deltaDistance / 5); // More science!!
    }

    public float calculateCaloriesFromTotalJumps(int totalJumps) {
        return (float) (totalJumps * (0.010 * this.referenceDiet.weight));
    }

    public void resetDeltas() {
        this.deltaDistance = 0;
        this.currentJumps = 0;
    }

}
