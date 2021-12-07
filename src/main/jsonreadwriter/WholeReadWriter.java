package jsonreadwriter;

import communitysystem.CommunityList;
import user.UserList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A class responsible for directing CommunityReadWriter and UserReadWriter to perform saving task
 */
public class WholeReadWriter{

    /**
     * Save data to a file
     *
     * @param pathForUser path of the file which stores all user information
     * @param pathForCommunity path of the file which stores all community information
     * @param userList a UserList storing all users
     * @param communityList a CommunityList storing all communities
     * @throws IOException if the saving causes an exception
     */
    public static void saveToFile(String pathForUser, String pathForCommunity,
                                  UserList userList, CommunityList communityList) throws IOException {
        UserReadWriter urw = new UserReadWriter();
        CommunityReadWriter crw = new CommunityReadWriter();
        urw.saveToFile(pathForUser, userList);
        crw.saveToFile(pathForCommunity, communityList);
    }

    /**
     * Retrieve data to a file
     *
     * @param pathForUser path of the file which stores all user information
     * @param pathForCommunity path of the file which stores all community information
     * @return all the information retrieved from storage
     * @throws IOException if the retrieving causes an exception
     * @throws ClassNotFoundException if the data is not found
     */
    public static List<Object> readFromFile(String pathForUser, String pathForCommunity)
            throws IOException, ClassNotFoundException {
        UserReadWriter urw = new UserReadWriter();
        CommunityReadWriter crw = new CommunityReadWriter();
        UserList userlist = urw.readFromFile(pathForUser);
        CommunityList communitylist = crw.readFromFile(pathForCommunity);
        return Arrays.asList(userlist, communitylist);
    }
}
