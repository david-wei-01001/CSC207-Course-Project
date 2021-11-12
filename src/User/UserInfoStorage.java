package User;

import constants.Exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that uses a hashmap to store UserInfo of all Users in this program.
 */

public class UserInfoStorage {

    /**
     * mapOfUserInfo Stores UserInfo of all Users in this program.
     */
    private Map<String, UserInfo> mapOfUserInfo;


    /**
     * The constructor of UserInfoStorage
     *
     * @param mapOfUserInfo the map of UserInfo that is deserialized from a JSON file.
     */
    public UserInfoStorage(Map<String, UserInfo> mapOfUserInfo) {
        this.mapOfUserInfo = mapOfUserInfo;
    }

    /**
     * Create a UserInfo for a new User and stores it in mapOfUserInfo.
     *
     * @param userName userName of the new User.
     * @param email email address of the new User.
     * @param password password of the new User.
     * @throws Exception throws an exception if the userName has been used.
     */
    public void addNewUser(String userName, String email, String password) throws Exception {
        if (!mapOfUserInfo.containsKey(userName)) {
            mapOfUserInfo.put(userName, new UserInfo(userName, email, password));
        } else {
            throw new Exception(Exceptions.USER_ALREADY_EXISTS);
        }

    }

    /**
     * Remove the Userinfo of an existing User (who is being removed from this program).
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
}
