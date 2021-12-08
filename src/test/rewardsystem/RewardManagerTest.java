package rewardsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

/**
 * Test the RewardManager class
 */
class RewardManagerTest {
    RewardManager manager;
    User user;

    /**
     * The setup method that setup each test.
     * It creates a RewardManager and a user.
     */
    @BeforeEach
    void setUp() {
        user = new User("Tong", "123@mail.com", "123");
        manager = new RewardManager();
        manager.setCurrentUser(user);
    }

    /**
     * Test if addRewardPoint method adds the correct amount of reward point to user.
     */
    @Test
    void testAddRewardPoint() {
        manager.addRewardPoint(5);
        Assertions.assertEquals(5, user.getRewardPoints());
    }
}