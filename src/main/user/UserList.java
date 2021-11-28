package user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of username —> User object.
 */
// What do we gain by defining this class and storing an instance of it
// in the LogInUseCase class, as opposed to just
// storing a Map<String, User> in the LogInUseCase class?
public class UserList implements Serializable {
    private final Map<String, UserInfo> usersList = new HashMap<>();

    /**
     * Add user to this user list.
     * @param userinfo the userinfo to add
     */
    public void add(UserInfo userinfo) {
        usersList.put(userinfo.getName(), userinfo);
    }

    /**
     * Return the User associated with username.
     * @param username the username of the user to get.
     */
    public UserInfo get(String username) {
        return usersList.get(username);
    }

    public boolean containsKey(String username){ return usersList.containsKey(username); }

}
