package jsonreadwriter;

import communitysystem.Community;
import communitysystem.CommunityList;
import user.UserList;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class WholeReadWriter{

    public void saveToFile(String pathForUser, String pathForCommunity, UserList userList, CommunityList communityList) throws IOException {
        UserReadWriter urw = new UserReadWriter();
        CommunityReadWriter crw = new CommunityReadWriter;
        urw.saveToFile(pathForUser, userList);
        crw.saveToFile(pathForCommunity, communityList);
    }


    public List<Object> readFromFile(String pathForUser, String pathForCommunity) throws IOException, ClassNotFoundException {
        UserReadWriter urw = new UserReadWriter();
        CommunityReadWriter crw = new CommunityReadWriter;
        UserList userlist = urw.readFromFile(pathForUser);
        CommunityList communitylist = crw.readFromFile(pathForCommunity);
        List data = Arrays.asList(userlist, communitylist);
    }
}
