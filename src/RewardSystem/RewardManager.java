package RewardSystem;

import User.UserInfo;

/**
 * The use case that controls a user's interaction with the reward system.
 */
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


    /**
     * Get the point rewarded when login
     * @return: The rewarded points for login every day, which is 1
     */
    public int getPointRewardedDailyLogin() {
        return pointRewardedDailyLogin;
    }

    /**
     * Get the point rewarded when liked by others
     * @return: The rewarded points for likes by others every day, which is 5
     */
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
