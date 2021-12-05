package rewardsystem;

import user.User;

/**
 * The use case that controls a user's interaction with the reward system.
 */
public class RewardManager {


    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public void addRewardPoint(int points) {
        currentUser.addRewardPoints(points);
    }


}
