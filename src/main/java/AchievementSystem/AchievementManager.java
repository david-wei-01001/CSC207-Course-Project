package AchievementSystem;

import User.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class AchievementManager {

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
