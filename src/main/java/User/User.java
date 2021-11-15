package User;

import java.io.Serializable;

/**
 *
 */

public class User implements Serializable {

    private UserInfo userInfo;

    public User(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }


}
