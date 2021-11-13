package User;


import Graph.DirectedGraph;
import constants.Exceptions;

import java.util.Map;

/**
 * The use case that control all actions of the User currently using our application.
 */

public class UserManager {

    /**
     * The User currently using our application.
     */
//    private User currentUser;

    /**
     * Stores UserInfo of all Users in this program. Each key is a userName and the value is the UserInfo
     * of the User with that userName.
     */
    private Map<String, UserInfo> mapOfUserInfo;
//    private UserInfoStorage userInfoStorage;

    /**
     * The constructor of UserManager.
     *
     * @param mapOfUserInfo the map of UserInfo that is deserialized from a JSON file.
     */
    public UserManager(Map<String, UserInfo> mapOfUserInfo) {
        this.mapOfUserInfo = mapOfUserInfo;
    }


    /**
     * Create a UserInfo for a new User and stores it in mapOfUserInfo.
     *
     * @param userName userName of the new User.
     * @param email email address of the new User.
     * @param password password of the new User.
     * @throws Exception throws an exception if the userName has been taken.
     */
    public void addNewUser(String userName, String email, String password) throws Exception {
        if (!mapOfUserInfo.containsKey(userName)) {
            mapOfUserInfo.put(userName, new UserInfo(userName, email, password));
        } else {
            throw new Exception(Exceptions.USER_NAME_TAKEN);
        }

    }

    /**
     * Remove the Userinfo of an existing User (who is being removed from this program) by userName.
     *
     * @param userName userName of the User to be removed
     * @throws Exception throws an exception if the inputted userName does not exist.
     */
    public void removeUser(String userName) throws Exception {
        if (mapOfUserInfo.containsKey(userName)) {
            mapOfUserInfo.remove(userName);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
        }
    }

    public UserInfo getAUserInfo(String userName) throws Exception {
        if (mapOfUserInfo.containsKey(userName)) {
            return mapOfUserInfo.get(userName);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
        }
    }

    /**
     * add a graph to current user
     */
    public void addGraphTo(User user, DirectedGraph graph) {
        user.getUserInfo().addGraph(graph);
    }


    /**
     * change the userName of currentUser to newUserName.
     * @param newUserName currentUser's new userName.
     * @throws Exception if newUserName has been taken.
     */
    public void setUserNameOf(User user, String newUserName) throws Exception {
        if (!mapOfUserInfo.containsKey(newUserName)) {
            user.getUserInfo().setUserName(newUserName);
        } else {
            throw new Exception(Exceptions.USER_NAME_TAKEN);
        }
    }

    /**
     * TODO: upgrade this method so that so duplicated email address is allowed.
     * @param user
     * @param newEmail
     */
    public void setEmailOf(User user, String newEmail) {
        user.getUserInfo().setEmail(newEmail);
    }

    public void setPasswordOf(User user, String newPassword) {
        user.getUserInfo().setPassword(newPassword);
    }

    public Map<String, UserInfo> getMapOfUserInfo() {
        return mapOfUserInfo;
    }
}
