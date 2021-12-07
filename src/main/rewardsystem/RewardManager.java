package rewardsystem;

import user.User;

/**
 * The use case that controls a user's interaction with the reward system.
 */
public class RewardManager {

    private User currentUser;

    /**
     * Set the currentUser instance variable to be the User specified
     *
     * @param currentUser the currentUser that is interacting with this Use Case
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Add the corresponding reward points to the currentUser
     *
     * @param points number of reward points to be added
     */
    public void addRewardPoint(int points) {
        currentUser.addRewardPoints(points);
    }


}
