package user;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    @Test
    void testAdd() {
        UserList userlist = new UserList();
        User user = new User("ash", "OAO@email.com", "123");
        userlist.add(user);
        HashMap<String, User> target = new HashMap<>();
        target.put("ash", user);
        assertEquals(userlist.getUsersList(), target);
    }

    @Test
    void testGet() {
        UserList userlist = new UserList();
        User user = new User("ash", "OAO@email.com", "123");
        userlist.add(user);
        assertEquals(userlist.get("ash"), user);
    }

    @Test
    void testContainsKey() {
        UserList userlist = new UserList();
        User user = new User("ash", "OAO@email.com", "123");
        userlist.add(user);
        assertTrue(userlist.containsKey("ash"));
        assertFalse(userlist.containsKey("alfred"));

    }
}