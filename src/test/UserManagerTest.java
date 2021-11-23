package test;

import graph.DirectedGraph;
import graphbuilders.GraphArchitect;
import user.UserInfo;
import user.UserManager;
import constants.BuiltInGraphs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * implement this test
 */
class UserManagerTest {
    final String USERNAME = "userName";
    final String EMAIL = "email@.com";
    final String PASSWORD = "password";
    final String ANOTHER_USERNAME = "anotherUsername";
    final String ANOTHER_EMAIL = "anotherEmail@.com";
    final String ANOTHER_PASSWORD = "anotherPassword";
    final String GRAPH = BuiltInGraphs.INTRODUCTORY_CS_SERIES;
    UserInfo userinfo = new UserInfo(USERNAME, EMAIL, PASSWORD);

    UserManager userManager;
    Map<String, UserInfo> mapOfUserInfo;

    DirectedGraph graph1;
    DirectedGraph graph2;
    DirectedGraph graph3;

    @BeforeEach
    void setUp() throws Exception {

        mapOfUserInfo = new HashMap<>();
        mapOfUserInfo.put(USERNAME, userinfo);
        userManager = new UserManager();
        userManager.setCurrentUserInfoTo(USERNAME);

        GraphArchitect graphArchitect = new GraphArchitect();
        graph1 = graphArchitect.setBuilderAndBuildGraph("Introductory CS Series");
        graph2 = graphArchitect.setBuilderAndBuildGraph("Introductory CS Series");
        graph3 = graphArchitect.setBuilderAndBuildGraph("Introductory CS Series");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addNewUserInfo() throws Exception {
        userManager.addNewUserInfo(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);

        UserInfo userInfoToCompare = new UserInfo(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);
        assertEquals(userManager.getMapOfUserInfo().get(ANOTHER_USERNAME).toString(), userInfoToCompare.toString());
    }

    /**
     * test if the userName has been taken
     */
    @Test
    void addNewUserUnsuccessful() throws Exception {

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.addNewUserInfo("userName", "antherEmail@", "anotherPassword"));
        Assertions.assertEquals("This username has already been taken", thrown.getMessage());

        UserInfo userInfoToCompare = new UserInfo(USERNAME, EMAIL, PASSWORD);
        assertEquals(userManager.getMapOfUserInfo().get(USERNAME).toString(), userInfoToCompare.toString());


    }


    @Test
    void getAUserInfo() throws Exception {
        UserInfo anotherUserInfo = new UserInfo(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);
        userManager.addNewUserInfo(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);

        UserInfo userInfoActual = userManager.getAUserInfo(USERNAME);
        assertEquals(userinfo, userInfoActual);
        assertEquals(anotherUserInfo.toString(), userManager.getAUserInfo(ANOTHER_USERNAME).toString());
    }

    @Test
    void getAUserInfoUnsuccessful() throws Exception {

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.getAUserInfo("anotherUserName"));
        Assertions.assertEquals("Cannot recognize this user", thrown.getMessage());
    }

    @Test
    void addGraphToCurrent() throws Exception {
        userManager.addGraphToCurrent(GRAPH);

        Map<String, DirectedGraph> mapToCompare = new HashMap<>();
        mapToCompare.put(GRAPH, graph1);

        assertEquals(mapToCompare.toString(), userManager.getCurrentUserInfo().getMapOfGraph().toString());
        assertEquals(GRAPH, userManager.getCurrentUserInfo().getMapOfGraph().get(GRAPH).toString());
        assertEquals(1, userManager.getCurrentUserInfo().getMapOfGraph().size());
    }

    @Test
    void addGraphToCurrentUnsuccessful() throws Exception {
        userManager.addGraphToCurrent(GRAPH);

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.addGraphToCurrent(GRAPH));
        Assertions.assertEquals("This graph already exists in this userinfo", thrown.getMessage());
    }

    @Test
    void setUserNameOfCurrent() throws Exception {
        userManager.setUserNameOfCurrent("newUserName");
        assertEquals("newUserName", userManager.getCurrentUserInfo().getUsername());
    }

    @Test
    void setUserNameOfCurrentUnsuccessfulWithSameName() throws Exception {

        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.setUserNameOfCurrent("userName"));
        Assertions.assertEquals("Same username as your current one", thrown.getMessage());

    }

    @Test
    void setUserNameOfCurrentUnsuccessfulWithDifferentName() throws Exception {

        userManager.addNewUserInfo("anotherUsername", "anotherEmail", "anotherPassword");
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.setUserNameOfCurrent("anotherUsername"));
        Assertions.assertEquals("This username has already been taken", thrown.getMessage());
    }

    @Test
    void setCurrentUserInfoTo() throws Exception {
        userManager.addNewUserInfo(ANOTHER_USERNAME, ANOTHER_EMAIL, ANOTHER_PASSWORD);
        assertEquals(userinfo, userManager.getCurrentUserInfo());
        userManager.setCurrentUserInfoTo(ANOTHER_USERNAME);
        assertEquals(ANOTHER_USERNAME, userManager.getCurrentUserInfo().getUsername());
    }

    @Test
    void setCurrentUserInfoToUnsuccessful() throws Exception {
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
                userManager.setCurrentUserInfoTo(ANOTHER_USERNAME));
        Assertions.assertEquals("Cannot recognize this user", thrown.getMessage());
    }
}