package RewardSystem;

import User.UserInfo;

public class RewardManager {

    private UserInfo currentUserInfo;

    /**
     * TODO: need to fix this:
     */

    private int pointsRewardedPerLike = 1;
    private int pointRewardedDailyLogin = 5;

    public RewardManager(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    public int getPointRewardedDailyLogin() {
        return pointRewardedDailyLogin;
    }

    public int getPointsRewardedPerLike() {
        return pointsRewardedPerLike;
    }

//    /**
//     * Adds reward points to the user that should receive them.
//     * @param user the user that should be rewarded points.
//     * @param point the amount of reward points that should be given.
//     */
//    public void addRewardPoint(int point){
//        currentUserInfo.setRewardPoints(point);
//    }



    public void addRewardPoint(int points) {
        currentUserInfo.addRewardPoints(points);
    }


}
