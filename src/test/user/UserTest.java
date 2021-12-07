package user;

import constants.Achievements;
import graph.DirectedGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the User class
 */
@SuppressWarnings("ALL")
public class UserTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    User user;

    /**
     * The setup method that setup each test.
     */
    @BeforeEach
    void setUp() {

        user = new User(USERNAME, EMAIL, PASSWORD);
    }

    /**
     * test if the mapOfAchievement contains all achievement and all set as false, after initialization.
     */
    @Test
    void initializeMapOfAchievement() {
        user.getMapOfAchievement().replace(Achievements.FIFTH_POST, false, true);
        assertTrue(user.getMapOfAchievement().get(Achievements.FIFTH_POST));
        user.initializeMapOfAchievement();
        for (String achievement : user.getMapOfAchievement().keySet()) {
            assertFalse(user.getMapOfAchievement().get(achievement));
        }
    }

    /**
     * test if the userinfo has been instantiated properly.
     */
    @Test
    void testToString() {
        ArrayList<String> emptyArray = new ArrayList<>();
        Map<String, DirectedGraph> emptyMap = new HashMap<>();

        Assertions.assertEquals("User{" +
                "userName='" + USERNAME + '\'' +
                ", email='" + EMAIL + '\'' +
                ", password='" + PASSWORD + '\'' +
                ", rewardPoints=" + 0 +
                ", lastLogin=" + LocalDate.now() +
                ", totalLogins=" + 0 +
                ", listOfPostId=" + emptyArray +
                ", listOfGraph=" + emptyMap +
                ", mapOfResource=" + emptyMap +
                ", mapOfAchievement=" + user.getMapOfAchievement() +
                '}', user.toString());
    }

    /**
     * Test if the incrementTotalLogins method correctly increase the number of logins
     */
    @Test
    void incrementTotalLogins() {
        user.incrementTotalLogins();
        user.incrementTotalLogins();
        user.incrementTotalLogins();
        Assertions.assertEquals(3, user.getTotalLogins());
    }

    /**
     * test if the achievements are displayed properly with no requirement to the order of display
     */
    @Test
    void testDisplayAchievements(){
        user.initializeMapOfAchievement();
        Object[] achievementNames = user.getMapOfAchievement().keySet().toArray();
        Assertions.assertEquals( achievementNames[0]
                        + ": "
                        + "not acquired"
                        + '\n'
                        + achievementNames[1]
                        + ": "
                        + "not acquired"
                        + '\n'
                        + achievementNames[2]
                        + ": "
                        + "not acquired"
                        + '\n'
                        + achievementNames[3]
                        + ": "
                        + "not acquired"
                        + '\n'

                ,user.displayAchievement());
    }
}
