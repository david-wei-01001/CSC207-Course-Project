//package User;
//
//import main.constants.Exceptions;
//
//import java.util.Map;
//
///**
// * A class that uses a map to store UserInfo of all Users in this program.
// */
//
//public class UserInfoStorage {
//
//    /**
//     * Stores UserInfo of all Users in this program. Each key is a userName and the value is the UserInfo
//     * of the User with that userName.
//     */
//    private Map<String, UserInfo> mapOfUserInfo;
//
//
//    /**
//     * The constructor of UserInfoStorage
//     *
//     * @param mapOfUserInfo the map of UserInfo that is deserialized from a JSON file.
//     */
//    public UserInfoStorage(Map<String, UserInfo> mapOfUserInfo) {
//        this.mapOfUserInfo = mapOfUserInfo;
//    }
//
//    /**
//     * Create a UserInfo for a new User and stores it in mapOfUserInfo.
//     *
//     * @param userName userName of the new User.
//     * @param email email address of the new User.
//     * @param password password of the new User.
//     * @throws Exception throws an exception if the userName has been used.
//     */
//    public void addNewUser(String userName, String email, String password) throws Exception {
//        if (!mapOfUserInfo.containsKey(userName)) {
//            mapOfUserInfo.put(userName, new UserInfo(userName, email, password));
//        } else {
//            throw new Exception(Exceptions.USER_NAME_TAKEN);
//        }
//
//    }
//
//    /**
//     * Remove the Userinfo of an existing User (who is being removed from this program).
//     *
//     * @param userName userName of the User to be removed
//     * @throws Exception throws an exception if the inputted userName does not exist.
//     */
//    public void removeUser(String userName) throws Exception {
//        if (mapOfUserInfo.containsKey(userName)) {
//            mapOfUserInfo.remove(userName);
//        } else {
//            throw new Exception(Exceptions.CANNOT_RECOGNIZE_USER);
//        }
//    }
//
//    /**
//     * Check if mapOfUserInfo contains the UserInfo the User with the inputted userName.
//     *
//     * @param userName userName of the User being checked.
//     * @return true if mapOfUserInfo contains the UserInfo for the particular userName, false otherwise.
//     */
//    public boolean contains(String userName) {
//        return mapOfUserInfo.containsKey(userName);
//    }
//}
