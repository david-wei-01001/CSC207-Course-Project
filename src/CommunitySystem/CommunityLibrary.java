package CommunitySystem;

import User.User;
import constants.Exceptions;

import java.util.HashMap;

public class CommunityLibrary {
    private HashMap<String, Community> mapOfCommunity = new HashMap<>();

    /**
     * Check if this community exists or not
     * @param name: The community name
     * @return: This community exists or not
     */
    public boolean checkCommunityExist(String name){
        return mapOfCommunity.containsKey(name);
    }


    /**
     * Create a new community.
     * @param name: The community name
     */
    public void addCommunity(String name){
        if(!checkCommunityExist(name)){
            Community com = new Community(name);
            mapOfCommunity.put(name, com);
        }
    }

    /**
     * TODO: implement this method
     */
    public String addPost(User user, String communityName, String content) throws Exception {
        if (mapOfCommunity.containsKey(communityName)) {
            return mapOfCommunity.get(communityName).add(content, user);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_COMMUNITY);
        }
    }

    /**
     * Delete an existing community
     * @param name: The name of Community
     */
    public void deleteCommunity(String name){
        mapOfCommunity.remove(name);
    }

    /**
     * Return the community with the given name.
     * @param name: The name of the community
     * @return Community that with the name
     */
    public Community getCommunity(String name){
        return mapOfCommunity.get(name);
    }


    public HashMap<String, Community> getMapOfCommunity() {
        return mapOfCommunity;
    }

    public void setCurrentCommunity(String communityName) throws Exception {
        if (checkCommunityExist(communityName)) {
            this.currentCommunity = mapOfCommunity.get(communityName);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_COMMUNITY);
        }

    }
}
//
//public class CommunityLibrary {
//    private static HashMap<String, Community> mapOfCommunity = new HashMap<>();
//    private Community currentCommunity;
//
//    /**
//     * Check if this community exists or not
//     * @param name: The community name
//     * @return: This community exists or not
//     */
//    public static boolean checkCommunityExist(String name){
//        return mapOfCommunity.containsKey(name);
//    }
//
//    /**
//     * Create a new community.
//     * @param name: The community name
//     */
//    public static void addCommunity(String name){
//        if(!checkCommunityExist(name)){
//            Community com = new Community(name);
//            mapOfCommunity.put(name, com);
//        }
//    }
//
//    /**
//     * TODO: implement this method
//     */
////    public void addPost();
//
//    /**
//     * Delete an existing community
//     * @param name: The name of Community
//     */
//    public static void deleteCommunity(String name){
//        mapOfCommunity.remove(name);
//    }
//
//    /**
//     * Return the community with the given name.
//     * @param name: The name of the community
//     * @return Community that with the name
//     */
//    public static Community getCommunity(String name){
//        return mapOfCommunity.get(name);
//    }
//
//
//
//    public Community getCurrentCommunity() {
//        return currentCommunity;
//    }
//
//    public void setCurrentCommunity(String communityName) throws Exception {
//        if (checkCommunityExist(communityName)) {
//            this.currentCommunity = mapOfCommunity.get(communityName);
//        } else {
//            throw new Exception(Exceptions.CANNOT_RECOGNIZE_COMMUNITY);
//        }
//
//    }
//}
