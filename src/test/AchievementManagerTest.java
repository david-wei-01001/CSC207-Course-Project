package test;

import achievementsystem.AchievementManager;
import user.UserInfo;
import constants.Achievements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AchievementManagerTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    UserInfo userInfo;
    AchievementManager achievementManager;

    @BeforeEach
    void setUp() {
        userInfo = new UserInfo(USERNAME, EMAIL, PASSWORD);
        achievementManager = new AchievementManager(userInfo);
    }

    @Test
    void requestAchievement() {
        userInfo.addToListOfPostId("1");
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                userInfo.getListOfPostId().size());
        assertTrue(userInfo.getMapOfAchievement().get(Achievements.FIRST_POST));

        userInfo.addToListOfPostId("2");
        userInfo.addToListOfPostId("3");
        userInfo.addToListOfPostId("4");
        userInfo.addToListOfPostId("5");
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                userInfo.getListOfPostId().size());
        assertTrue(userInfo.getMapOfAchievement().get(Achievements.FIFTH_POST));

        userInfo.addToListOfPostId("6");
        userInfo.addToListOfPostId("7");
        userInfo.addToListOfPostId("8");
        userInfo.addToListOfPostId("9");
        userInfo.addToListOfPostId("10");
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                userInfo.getListOfPostId().size());
        assertTrue(userInfo.getMapOfAchievement().get(Achievements.TENTH_POST));

        userInfo.incrementTotalLogins();
        achievementManager.requestAchievement(Achievements.ARRAY_OF_TOTAL_LOGINS_THRESHOLDS,
                Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_ACHIEVEMENT,
                userInfo.getTotalLogins());
        assertTrue(userInfo.getMapOfAchievement().get(Achievements.FIRST_LOGIN));
    }
}