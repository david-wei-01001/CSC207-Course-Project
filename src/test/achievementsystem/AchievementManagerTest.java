package achievementsystem;

import user.User;
import constants.Achievements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AchievementManagerTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    User user;
    AchievementManager achievementManager;

    @BeforeEach
    void setUp() {
        User user = new User(USERNAME, EMAIL, PASSWORD);
        achievementManager = new AchievementManager();
        achievementManager.setCurrentUser(user);
    }

    @Test
    void requestAchievement() {
        user.addToListOfPostId("1");
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                user.getListOfPostId().size());
        assertTrue(user.getMapOfAchievement().get(Achievements.FIRST_POST));

        user.addToListOfPostId("2");
        user.addToListOfPostId("3");
        user.addToListOfPostId("4");
        user.addToListOfPostId("5");
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                user.getListOfPostId().size());
        assertTrue(user.getMapOfAchievement().get(Achievements.FIFTH_POST));

        user.addToListOfPostId("6");
        user.addToListOfPostId("7");
        user.addToListOfPostId("8");
        user.addToListOfPostId("9");
        user.addToListOfPostId("10");
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                user.getListOfPostId().size());
        assertTrue(user.getMapOfAchievement().get(Achievements.TENTH_POST));

        user.incrementTotalLogins();
        achievementManager.requestAchievement(Achievements.ARRAY_OF_TOTAL_LOGINS_THRESHOLDS,
                Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_ACHIEVEMENT,
                user.getTotalLogins());
        assertTrue(user.getMapOfAchievement().get(Achievements.FIRST_LOGIN));
    }
}