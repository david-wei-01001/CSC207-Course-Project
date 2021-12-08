package achievementsystem;
import user.User;
import java.util.Map;

/**
 * The use case that controls a main. user's interaction with the achievement system.
 */
public class AchievementManager {

    private User currentUser;

    /**
     * set the instance variable currentUserInfo to be the user who is going to interact with the achievement system.
     *
     * @param currentUser A user who is going to interact with the achievement system.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Check if the user is eligible for any achievement that he/she has not received. If there is any, award that
     * achievement to main.user.
     *
     * @param thresholds the requirements of the type of achievements.
     * @param thresholdsToAchievement the Map of requirements to their particular achievement.
     * @param property the property of user that can be awarded with achievement.
     *                 For example, the number of post created.
     * @return true if any achievement is awarded.
     */
    public boolean requestAchievement(int[] thresholds, Map<Integer, String> thresholdsToAchievement, int property) {
        for (int threshold : thresholds) {
            if (property == threshold) {
                currentUser.getMapOfAchievement().put(thresholdsToAchievement.get(property),
                        true);
                return true;
            }
        }
        return false;
    }
}
