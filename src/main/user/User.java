package user;

import java.io.Serializable;

/**
 *
 */

public class User implements Serializable {

    private final UserInfo userInfo;

    public User(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }


}
