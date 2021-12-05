package user;

import constants.IterableMap;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * A map of username —> User object.
 */

public class UserList implements Serializable, Iterable<String>{

    private final IterableMap<String, User> usersList = new IterableMap<>();

    /**
     * Return the userLists.
     * @return the userList in this UserList class
     */
    public Map<String, User> getUsersList() {
        return usersList;
    }

    /**
     * Add user to this user list.
     * @param user the userinfo to add
     */
    public void add(User user) {
        usersList.put(user.getName(), user);
    }

    /**
     * Return the User associated with username.
     * @param username the username of the user to get.
     */
    public User get(String username) {
        return usersList.get(username);
    }

    /**
     *
     * @param username the username od the user
     * @return if the user with given username in current userList.
     */
    public boolean containsKey(String username){ return usersList.containsKey(username); }

    @Override
    public Iterator<String> iterator() {
        return usersList.iterator();
    }
}
