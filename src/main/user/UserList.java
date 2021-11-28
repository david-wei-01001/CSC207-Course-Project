package user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of username —> User object.
 */

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
