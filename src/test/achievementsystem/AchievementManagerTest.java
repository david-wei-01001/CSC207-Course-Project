package achievementsystem;

import user.User;
import constants.Achievements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the AchievementManager class
 */
class AchievementManagerTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    User user;
    AchievementManager achievementManager;

    /**
     * Set up the test
     */
    @BeforeEach
    void setUp() {
        user = new User(USERNAME, EMAIL, PASSWORD);
        achievementManager = new AchievementManager();
        achievementManager.setCurrentUser(user);
    }

    /**
     * test if the AchievementManager class behave as intended.
     */
    @Test
    void requestAchievement() {
        assertEquals(user.displayAchievement(), "First Login: not acquired\nTenth Post: not acquired\n" +
                "First Post: not acquired\nFifth Post: not acquired\n" );

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

        assertEquals(user.displayAchievement(), user.getMapOfAchievement().toString());
    }
}