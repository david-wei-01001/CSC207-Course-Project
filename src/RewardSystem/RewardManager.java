package RewardSystem;

import User.User;

public class RewardManager {

    private static int pointsRewardedPerLike = 1;
    private static int pointRewardedDailyLogin = 5;


    public static int getPointRewardedDailyLogin() {
        return pointRewardedDailyLogin;
    }

    public static int getPointsRewardedPerLike() {
        return pointsRewardedPerLike;
    }

    /**
     * Adds reward points to the user that should receive them.
     * @param user the user that should be rewarded points.
     * @param point the amount of reward points that should be given.
     */
    public static void addRewardPoint(User user, int point){
        user.getUserInfo().setRewardPoints(point);
    }


}
