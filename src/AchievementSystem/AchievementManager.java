package AchievementSystem;


import User.UserInfo;
import constants.Achievements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * The use case that controls a user's interaction with the achievement system.
 */
public class AchievementManager {

    private UserInfo currentUserInfo;

    public AchievementManager(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    /**
     * Check if the user is eligible for any achievement that he/she has not received. If there is any, award that
     * achievement to user.
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
                currentUserInfo.getMapOfAchievement().replace(thresholdsToAchievement.get(property), false, true);

                return true;
            }
        }
        return false;
    }



}
