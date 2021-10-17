package Java;


import java.util.ArrayList;
import java.util.List;

/** TODO: add javadoc
 *
 */

public class UserManager {

    /** TODO: add explanation for the DS of listOfUsers
     *
     */

    private ArrayList<User> listOfUsers = new ArrayList<>();

    public void addUser(User user) {
        listOfUsers.add(user);
    }

}
