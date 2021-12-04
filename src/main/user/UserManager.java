package user;


import graphbuilders.GraphArchitect;
import constants.Exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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


//    /**
//     * The constructor of UserManager.
//     *
//     * @param mapOfUserInfo the map of User that is deserialized from a JSON file.
//     */
//    public UserManager(Map<String, User> mapOfUser) {
//        this.mapOfUser = mapOfUser;
//    }


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

    /**
     * Set the email address of the current user to be the input email address, given it is not already taken.
     * @param newEmail an email address to be set to the current user.
     * @throws Exception if newEmail has been taken.
     */
    public void setEmailOfCurrent(String newEmail) throws Exception {
        for (String userName: mapOfUser) {
            User user = mapOfUser.get(userName);
            if (user.getEmail().equals(newEmail)) {
                throw new Exception(Exceptions.EMAIL_ALREADY_EXIST);
            }
        }
        currentUser.setEmail(newEmail);
    }

    public void setPasswordOfCurrent(String newPassword) {
        currentUser.setPassword(newPassword);
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

    // we might not need to remove a user. and correctly implementing removing an user
    // is difficult: what if the user i am removing is the currentUser
//    /**
//     //     * Remove the User of an existing User (who is being removed from this program) by userName.
//     //     *
//     //     * @param userName userName of the User to be removed
//     //     * @throws Exception throws an exception if the inputted userName does not exist.
//     //     */
//    public void removeUser(String userName) throws Exception {
//        if (mapOfUser.containsKey(userName)) {
//            mapOfUser.remove(userName);
//        } else {
//            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
//        }
//    }

    public String displayAchievement() {
        return currentUser.displayAchievement();
    }

    public ArrayList<String> getListOfPostId() {
        return currentUser.getListOfPostId();
    }
}
