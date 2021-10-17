package Java;

import Posts.Community;
import Posts.Post;

import java.util.HashMap;

public class CommunityLibrary {
    private static HashMap<String, Community> mapOfCommunity = new HashMap<>();

    /**
     * Check if this community exists or not
     * @param name: The community name
     * @return: This community exists or not
     */
    public static boolean checkCommunityExist(String name){
        return mapOfCommunity.containsKey(name);
    }

    /**
     * Create a new community.
     * @param name: The community name
     */
    public static void addCommunity(String name){
        if(!checkCommunityExist(name)){
            Community com = new Community(name);
            mapOfCommunity.put(name, com);
        }
    }

    /**
     * Delete an existing community
     * @param name: The name of Community
     */
    public static void deleteCommunity(String name){
        mapOfCommunity.remove(name);
    }

    /**
     * Return the community with the given name.
     * @param name: The name of the community
     * @return Community that with the name
     */
    public static Community getCommunity(String name){
        return mapOfCommunity.get(name);
        }
}
