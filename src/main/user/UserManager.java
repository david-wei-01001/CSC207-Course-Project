package user;


import constants.Exceptions;
import graphbuilders.GraphArchitect;

import java.util.ArrayList;

/**
 * The use case that control all actions of the User currently using our application.
 */

public class UserManager {
    /**
     * The info of the User currently using our application.
     */
    private User currentUser;

    /**
     * Stores UserInfo of all Users in this program. Each key is a userName and the value is the UserInfo
     * of the User with that userName.
     */
    private UserList mapOfUser = new UserList();
    /**
     * Create a User for a new User and stores it in mapOfUser.
     *
     * @param userName userName of the new User.
     * @param email    email address of the new User.
     * @param password password of the new User.
     * @throws Exception throws an exception if the userName has been taken.
     */
    public void addNewUser(String userName, String email, String password) throws Exception {
        if (!mapOfUser.containsKey(userName)) {
            mapOfUser.add(new User(userName,email, password));
        } else {
            throw new Exception(Exceptions.USER_NAME_TAKEN);
        }
    }

    /**
     * Return User on given username.
     *
     * @param userName the name of the user whose UserInfo is to be retrieved
     * @return User the User corresponds to the input userName
     * @throws Exception throws an exception if the input userName is not in mapOfUser
     */
    public User getAUser(String userName) throws Exception {
        if (mapOfUser.containsKey(userName)) {
            return mapOfUser.get(userName);
        } else {
            throw new Exception(Exceptions.INCORRECT_USERNAME);
        }
    }

    /**
     * add a DirectedGraph to current user.
     *
     * @param graphName the name of the DirectedGraph to be added
     * @throws Exception throws an exception if the user already has the graph.
     */
    public void addGraphToCurrent(String graphName) throws Exception {
        if (!currentUser.hasGraph(graphName)) {
            currentUser.addGraph(GraphArchitect.setBuilderAndBuildGraph(graphName));
        } else {
            throw new Exception(Exceptions.GRAPH_ALREADY_EXISTS);
        }
    }

    /**
     * change the userName of currentUser to newUsername.
     *
     * @param newUsername currentUser's new userName.
     * @throws Exception if newUsername has been taken.
     */
    public void setUserNameOfCurrent(String newUsername) throws Exception {
        if (!mapOfUser.containsKey(newUsername)) {
            currentUser.setUsername(newUsername);
        } else if (newUsername.equals(currentUser.getName())) {
            throw new Exception(Exceptions.SAME_USERNAME_AS_CURRENT);
        } else {
            throw new Exception(Exceptions.USER_NAME_TAKEN);
        }
    }

    public UserList getMapOfUser() {
        return mapOfUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String username) throws Exception {
        if (mapOfUser.containsKey(username)) {
            this.currentUser = mapOfUser.get(username);
        } else {
            throw new Exception(Exceptions.INCORRECT_USERNAME);
        }
    }

    public boolean containsUsername(String username) {
        return mapOfUser.containsKey(username);
    }

    public void incrementTotalLogins() {
        currentUser.incrementTotalLogins();
    }

    public void setMapOfUser(UserList mapOfUser) {
        this.mapOfUser = mapOfUser;
    }

    public String displayAchievement() {
        return currentUser.displayAchievement();
    }

    public ArrayList<String> getListOfPostId() {
        return currentUser.getListOfPostId();
    }
}
