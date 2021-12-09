package communitysystem;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rewardsystem.RewardManager;
import user.User;


/**
 * Test the CommunityLibrary class
 */
class CommunityLibraryTest {
    CommunityLibrary communityLibrary;
    User user;

    /**
     * The setup method that setup each test.
     * It creates a communityLibrary.
     */
    @BeforeEach
    public void setUp() {
        user = new User("Tong", "123@mail.com", "123");
        communityLibrary = new CommunityLibrary();
        communityLibrary.setCurrentUser(user);
    }

    /**
     * Test if the correct map of community is set to community library and can also be retrieved.
     */
    @Test
    void testGetSetMapOfCommunity() {
        CommunityList communityList = new CommunityList();
        communityLibrary.setMapOfCommunity(communityList);
        Assertions.assertEquals(communityList, communityLibrary.getMapOfCommunity());
    }

    /**
     * Test if the correct current user is set to community library and can also be retrieved.
     */
    @Test
    void testGetSetCurrentUser() {
        Assertions.assertEquals(user, communityLibrary.getCurrentUser());
    }

    /**
     * Test if the communityLibrary correct checks if the inputted community exists. It should return true if yes
     * and false if not.
     */
    @Test
    void testCheckCommunityExist() {
        Community community = new Community("hi");
        CommunityList communityList = new CommunityList();
        communityList.add(community);
        communityLibrary.setMapOfCommunity(communityList);
        Assertions.assertTrue(communityLibrary.checkCommunityExist("hi"));
        Assertions.assertFalse(communityLibrary.checkCommunityExist("hello"));

    }

    /**
     * Test if the community can be correct added to communityLibrary.
     */
    @Test
    void testAddCommunity() {
        CommunityList communityList = new CommunityList();
        communityLibrary.setMapOfCommunity(communityList);
        communityLibrary.addCommunity("hi");
        Assertions.assertTrue(communityLibrary.getMapOfCommunity().containsKey("hi"));
        Assertions.assertFalse(communityLibrary.getMapOfCommunity().containsKey("hello"));
    }

    /**
     * Test if the new post is created and stored correctly.
     */
    @Test
    void testCreatePost() throws Exception {
        Community community = new Community("hi");
        CommunityList communityList = new CommunityList();
        communityList.add(community);
        communityLibrary.setMapOfCommunity(communityList);
        RewardManager rewardManager = new RewardManager();
        communityLibrary.setCurrentCommunity("hi");
        rewardManager.setCurrentUser(user);
        communityLibrary.createPost("hello", rewardManager);
        Assertions.assertTrue(communityLibrary.getCurrentCommunity().getMapOfPost().containsKey("Post #0"));
        Assertions.assertEquals(user.getRewardPoints(), 5);

    }

    /**
     * Test if the current community is set properly and can also be retrieved.
     */
    @Test
    void testGetSetCurrentCommunity() throws Exception {
        Community community = new Community("hi");
        CommunityList communityList = new CommunityList();
        communityList.add(community);
        communityLibrary.setMapOfCommunity(communityList);
        communityLibrary.setCurrentCommunity("hi");
        Assertions.assertEquals(communityLibrary.getCurrentCommunity(), community);
    }

}