package AchievementSystem;

import User.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * The use case that controls a user's interaction with the achievement system.
 */
public class AchievementManager {

    /**
     * Check if the user is eligible for any achievement that he/she has not received. If there is any, award that
     * achievement to user.
     *
     * @param user the user who is requesting for achievements
     * @param thresholds the requirements of the type of achievements.
     * @param thresholdsToAchievement the Map of requirements to their particular achievement.
     * @param property the property of user that can be awarded with achievement.
     *                 For example, the number of post created.
     * @return true if any achievement is awarded.
     */
    public boolean requestAchievement(User user, int[] thresholds, Map<Integer, String> thresholdsToAchievement, int property) {
        for (int threshold : thresholds) {
            if (property == threshold) {
                user.getUserInfo().getMapOfAchievement().replace(thresholdsToAchievement.get(property), false, true);
                return true;
            }
        }
        return false;
    }



}
