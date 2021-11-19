package RewardSystem;

import User.User;

public class RewardManager {

    private final int pointsRewardedPerLike = 1;
    private final int pointRewardedDailyLogin = 5;


    public int getPointRewardedDailyLogin() {
        return pointRewardedDailyLogin;
    }

    public int getPointsRewardedPerLike() {
        return pointsRewardedPerLike;
    }

    /**
     * Adds reward points to the user that should receive them.
     * @param user the user that should be rewarded points.
     * @param point the amount of reward points that should be given.
     */
    public void addRewardPoint(User user, int point){
        user.getUserInfo().setRewardPoints(point);
    }


}
