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
    private UserInfo currentUserInfo;

    /**
     * Stores UserInfo of all Users in this program. Each key is a userName and the value is the UserInfo
     * of the User with that userName.
     */
    private UserList mapOfUserInfo = new UserList();


//    /**
//     * The constructor of UserManager.
//     *
//     * @param mapOfUserInfo the map of UserInfo that is deserialized from a JSON file.
//     */
//    public UserManager(Map<String, UserInfo> mapOfUserInfo) {
//        this.mapOfUserInfo = mapOfUserInfo;
//    }


    /**
     * Create a UserInfo for a new User and stores it in mapOfUserInfo.
     *
     * @param userName userName of the new User.
     * @param email    email address of the new User.
     * @param password password of the new User.
     * @throws Exception throws an exception if the userName has been taken.
     */
    public void addNewUserInfo(String userName, String email, String password) throws Exception {
        if (!mapOfUserInfo.containsKey(userName)) {
            mapOfUserInfo.add(new UserInfo(userName,email, password));
        } else {
            throw new Exception(Exceptions.USER_NAME_TAKEN);
        }
    }

    /**
     * Return UserInfo on given username.
     *
     * @param userName the name of the user whose UserInfo is to be retrieved
     * @return UserInfo the UserInfo corresponds to the input userName
     * @throws Exception throws an exception if the input userName is not in mapOfUserInfo
     */
    public UserInfo getAUserInfo(String userName) throws Exception {
        if (mapOfUserInfo.containsKey(userName)) {
            return mapOfUserInfo.get(userName);
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
        if (!currentUserInfo.hasGraph(graphName)) {
            currentUserInfo.addGraph(GraphArchitect.setBuilderAndBuildGraph(graphName));
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
        if (!mapOfUserInfo.containsKey(newUsername)) {
            currentUserInfo.setUsername(newUsername);
        } else if (newUsername.equals(currentUserInfo.getName())) {
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
        for (String userName: mapOfUserInfo) {
            UserInfo userInfo = mapOfUserInfo.get(userName);
            if (userInfo.getEmail().equals(newEmail)) {
                throw new Exception(Exceptions.EMAIL_ALREADY_EXIST);
            }
        }
        currentUserInfo.setEmail(newEmail);
    }

    public void setPasswordOfCurrent(String newPassword) {
        currentUserInfo.setPassword(newPassword);
    }

    public UserList getMapOfUserInfo() {
        return mapOfUserInfo;
    }

    public UserInfo getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void setCurrentUserInfoTo(String username) throws Exception {
        if (mapOfUserInfo.containsKey(username)) {
            this.currentUserInfo = mapOfUserInfo.get(username);
        } else {
            throw new Exception(Exceptions.INCORRECT_USERNAME);
        }
    }

    public boolean containsUsername(String username) {
        return mapOfUserInfo.containsKey(username);
    }

    public void incrementTotalLogins() {
        currentUserInfo.incrementTotalLogins();
    }

    public void setMapOfUserInfo(UserList mapOfUserInfo) {
        this.mapOfUserInfo = mapOfUserInfo;
    }

    // we might not need to remove a userinfo. and correctly implementing removing an userinfo
    // is difficult: what if the userinfo i am removing is the currentUserInfo
//    /**
//     //     * Remove the Userinfo of an existing User (who is being removed from this program) by userName.
//     //     *
//     //     * @param userName userName of the User to be removed
//     //     * @throws Exception throws an exception if the inputted userName does not exist.
//     //     */
//    public void removeUserInfo(String userName) throws Exception {
//        if (mapOfUserInfo.containsKey(userName)) {
//            mapOfUserInfo.remove(userName);
//        } else {
//            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
//        }
//    }

    public String displayAchievement() {
        return currentUserInfo.displayAchievement();
    }

    public ArrayList<String> getListOfPostId() {
        return currentUserInfo.getListOfPostId();
    }
}
