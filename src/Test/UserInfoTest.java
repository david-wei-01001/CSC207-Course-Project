package Test;

import Graph.DirectedGraph;
import User.UserInfo;
import constants.Achievements;
import constants.BuiltInGraphs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: implement this test
 */
public class UserInfoTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    UserInfo userInfo;

    @BeforeEach
    void setUp() {

        userInfo = new UserInfo(USERNAME, EMAIL, PASSWORD);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * test if the mapOfAchievement contains all achievement and all set as false, after initialization.
     */
    @Test
    void initializeMapOfAchievement() {
        userInfo.getMapOfAchievement().replace(Achievements.FIFTH_POST, false, true);
        assertTrue(userInfo.getMapOfAchievement().get(Achievements.FIFTH_POST));
        userInfo.initializeMapOfAchievement();
        for (String achievement : userInfo.getMapOfAchievement().keySet()) {
            assertFalse(userInfo.getMapOfAchievement().get(achievement));
        }
    }

    /**
     * test if the userinfo has been instantiated properly.
     */
    @Test
    void testToString() {
        ArrayList<String> emptyArray = new ArrayList<>();
        Map<String, DirectedGraph> emptyMap = new HashMap<>();

        assertEquals("UserInfo{" +
                "userName='" + USERNAME + '\'' +
                ", email='" + EMAIL + '\'' +
                ", password='" + PASSWORD + '\'' +
                ", rewardPoints=" + 0 +
                ", lastLogin=" + LocalDate.now() +
                ", totalLogins=" + 0 +
                ", listOfPostId=" + emptyArray +
                ", listOfGraph=" + emptyMap +
                ", listOfResource=" + emptyArray +
                ", mapOfAchievement=" + userInfo.getMapOfAchievement() +
                '}', userInfo.toString());
    }

    @Test
    void incrementTotalLogins() {
        userInfo.incrementTotalLogins();
        userInfo.incrementTotalLogins();
        userInfo.incrementTotalLogins();
        assertEquals(3, userInfo.getTotalLogins());
    }

    ///only for seeing what displayAchievements actually returns
    @Test
    void testDisplayAchievements(){
        assertEquals("", userInfo.displayAchievement());
    }
}
