package user;

import constants.Achievements;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    @Test
    void add() {
        UserList userlist = new UserList();
        UserInfo userinfo = new UserInfo("ash", "OAO@email.com", "123");
        userlist.add(userinfo);
        HashMap<String, UserInfo> target = new HashMap<>();
        target.put("ash", userinfo);
        assertTrue(userlist.getUsersList().equals(target));
    }

    @Test
    void get() {
        UserList userlist = new UserList();
        UserInfo userinfo = new UserInfo("ash", "OAO@email.com", "123");
        userlist.add(userinfo);
        assertTrue(userlist.get("ash").equals(userinfo));
    }

    @Test
    void containsKey() {
        UserList userlist = new UserList();
        UserInfo userinfo = new UserInfo("ash", "OAO@email.com", "123");
        userlist.add(userinfo);
        assertTrue(userlist.containsKey("ash"));
        assertFalse(userlist.containsKey("alfred"));

    }
}