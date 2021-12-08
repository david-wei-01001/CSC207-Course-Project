package user;

import achievementsystem.AchievementManager;
import constants.Achievements;
import constants.BuiltInGraphs;
import graph.DirectedGraph;
import graphbuilders.GraphArchitect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the UserManager class
 */
class UserManagerTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    final String ANOTHER_USERNAME = "anotherUsername";
    final String ANOTHER_EMAIL = "anotherEmail@.com";
    final String ANOTHER_PASSWORD = "anotherPassword";
    final String GRAPH = BuiltInGraphs.INTRODUCTORY_CS_SERIES;
    User user = new User(USERNAME, EMAIL, PASSWORD);

    UserManager userManager;
    UserList mapOfUser;

    DirectedGraph graph1;
    DirectedGraph graph2;
    DirectedGraph graph3;

    /**
     * The setup method that setup each test.
     */
    @BeforeEach
    void setUp() throws Exception {

        mapOfUser = new UserList();
        mapOfUser.add(user);
        userManager = new UserManager();
        userManager.setMapOfUser(mapOfUser);
        userManager.setCurrentUser(USERNAME);

        graph1 = GraphArchitect.setBuilderAndBuildGraph("Introductory CS Series");
        graph2 = GraphArchitect.setBuilderAndBuildGraph("Introductory Makeup Steps");
        graph3 = GraphArchitect.setBuilderAndBuildGraph("Mathematics: Foundation to Frontier");
    }

    /**
     * Test if the addNewUser correctly add a user
     *
     * @throws Exception an exception to be thrown
     */
    @Test
    void testAddNewUser() throws Exception {
        userManager.addNewUser(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);

        User userToCompare = new User(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);
        assertEquals(userManager.getMapOfUser().get(ANOTHER_USERNAME).toString(), userToCompare.toString());
    }

    /**
     * test if the userName has been taken
     */
    @Test
    void testAddNewUserUnsuccessful() {

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.addNewUser("userName", "antherEmail@", "anotherPassword"));
        Assertions.assertEquals("This username has already been taken.", thrown.getMessage());

        User userToCompare = new User(USERNAME, EMAIL, PASSWORD);
        assertEquals(userManager.getMapOfUser().get(USERNAME).toString(), userToCompare.toString());


    }

    /**
     * Test if the getAUser correctly retrieve a user
     *
     * @throws Exception an exception to be thrown
     */
    @Test
    void testGetAUser() throws Exception {
        User anotherUser = new User(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);
        userManager.addNewUser(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);

        User userActual = userManager.getAUser(USERNAME);
        assertEquals(user, userActual);
        assertEquals(anotherUser.toString(), userManager.getAUser(ANOTHER_USERNAME).toString());
    }

    /**
     * Test if the getAUser correctly throw an exception
     */
    @Test
    void testGetAUserUnsuccessful() {

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.getAUser("anotherUserName"));
        Assertions.assertEquals("Invalid username", thrown.getMessage());
    }

    /**
     * Test if the addGraphToCurrent method correctly add a graph to the user
     *
     * @throws Exception an exception to be thrown
     */
    @Test
    void testAddGraphToCurrent() throws Exception {
        userManager.addGraphToCurrent(GRAPH);

        Map<String, DirectedGraph> mapToCompare = new HashMap<>();
        mapToCompare.put(GRAPH, graph1);

        assertEquals(mapToCompare.toString(), userManager.getCurrentUser().getMapOfGraph().toString());
        assertEquals(GRAPH, userManager.getCurrentUser().getMapOfGraph().get(GRAPH).getName());
        assertEquals(1, userManager.getCurrentUser().getMapOfGraph().size());
    }

    /**
     * Test if the addGraphToCurrent method correctly throws an exception
     */
    @Test
    void testAddGraphToCurrentUnsuccessful() {
        try {
            userManager.addGraphToCurrent("Mathematics: Foundation to Frontier");
            userManager.addGraphToCurrent("Mathematics: Foundation to Frontier");
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("This main.graph already exists in this user.", e.getMessage());
        }
    }

    /**
     * Test if the setUserNameOfCurrent method correctly set a username
     *
     * @throws Exception an exception to be thrown
     */
    @Test
    void testSetUserNameOfCurrent() throws Exception {
        userManager.setUserNameOfCurrent("newUserName");
        assertEquals("newUserName", userManager.getCurrentUser().getName());
    }

    /**
     * Test if the setUserNameOfCurrent method correctly throws an exception
     */
    @Test
    void testSetUserNameOfCurrentUnsuccessfulWithSameName() {

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.setUserNameOfCurrent("userName"));
        Assertions.assertEquals("Same username as your current one.", thrown.getMessage());

    }

    /**
     * Test if the setUserNameOfCurrent method correctly set the user to a different set of credentials
     *
     * @throws Exception an exception to be thrown
     */
    @Test
    void testSetUserNameOfCurrentUnsuccessfulWithDifferentName() throws Exception {

        userManager.addNewUser("anotherUsername", "anotherEmail", "anotherPassword");
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.setUserNameOfCurrent("anotherUsername"));
        Assertions.assertEquals("This username has already been taken.", thrown.getMessage());
    }

    /**
     * Test if the getCurrentUser method correctly retrieve the current user
     *
     * @throws Exception an exception to be thrown
     */
    @Test
    void testSetCurrentUser() throws Exception {
        userManager.addNewUser(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);
        assertEquals(user, userManager.getCurrentUser());
        userManager.setCurrentUser(ANOTHER_USERNAME);
        assertEquals(ANOTHER_USERNAME, userManager.getCurrentUser().getName());
    }

    /**
     * Test if the getCurrentUser method correctly throws an exception
     */
    @Test
    void testSetCurrentUserUnsuccessful() {
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.setCurrentUser(ANOTHER_USERNAME));
        Assertions.assertEquals("Invalid username", thrown.getMessage());
    }

    /**
     * Test if the displayAchievement method correctly displays all the user's achievements
     */
    @Test
    void testDisplayAchievement() {
        user.addToListOfPostId("1");
        AchievementManager achievementManager = new AchievementManager();
        achievementManager.setCurrentUser(user);
        achievementManager.requestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                user.getListOfPostId().size());
        assertTrue(user.getMapOfAchievement().get(Achievements.FIRST_POST));
        assertEquals(userManager.displayAchievement(), "First Login: not acquired\n" +
                "Tenth Post: not acquired\n" +
                "First Post: acquired\n" +
                "Fifth Post: not acquired\n");
    }
}