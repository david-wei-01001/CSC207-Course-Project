package communitysystem;

import achievementsystem.AchievementManager;
import constants.Achievements;
import rewardsystem.RewardManager;
import user.User;
import constants.Exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * The use case that controls a user's interaction with the achievement system.
 */
public class CommunityLibrary {
    private static final CommunityList mapOfCommunity = new CommunityList();
    private Community currentCommunity;
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


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
            mapOfCommunity.add(com);
        }
    }

    /**
     //     * Add a post to a community.
     //     * @param user the main.user adding this post
     //     * @param communityName the name of the community this post is being added to
     //     * @param content the content of the post being added
     //     * @return the id of the post being added.
     //     * @throws Exception throws an exception if the community with the communityName is not found.
     //     */


    public void createPost(String content, AchievementManager achievementManager,
                           RewardManager rewardManager) throws Exception {
        String postId = currentCommunity.addPublishedContent(content, currentUser);
        currentUser.addToListOfPostId(postId);
        rewardManager.addRewardPoint(5);
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



    public Community getCurrentCommunity() {
        return currentCommunity;
    }

    public void setCurrentCommunity(String communityName) throws Exception {
        if (checkCommunityExist(communityName)) {
            this.currentCommunity = mapOfCommunity.get(communityName);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_COMMUNITY);
        }

    }

    public CommunityList getMapOfCommunity() {
        return mapOfCommunity;
    }
}
//public class CommunityLibrary {
//    private Map<String, Community> mapOfCommunity = new HashMap<>();
//
//    /**
//     * Check if this community exists or not
//     * @param name: The community name
//     * @return: This community exists or not
//     */
//    public boolean checkCommunityExist(String name){
//        return mapOfCommunity.containsKey(name);
//    }
//
//
//    /**
//     * Create a new community.
//     * @param name: The community name
//     */
//    public void addCommunity(String name){
//        if(!checkCommunityExist(name)){
//            Community com = new Community(name);
//            mapOfCommunity.put(name, com);
//        }
//    }
//
//    /**
//     * Add a post to a community.
//     * @param main.user the main.user adding this post
//     * @param communityName the name of the community this post is being added to
//     * @param content the content of the post being added
//     * @return the id of the post being added.
//     * @throws Exception throws an exception if the community with the communityName is not found.
//     */
//    public String addPost(User main.user, String communityName, String content) throws Exception {
//        if (mapOfCommunity.containsKey(communityName)) {
//            return mapOfCommunity.get(communityName).addPublishedContent(content, main.user);
//        } else {
//            throw new Exception(Exceptions.CANNOT_RECOGNIZE_COMMUNITY);
//        }
//    }
//
//    /**
//     * Delete an existing community
//     * @param name: The name of Community
//     */
//    public void deleteCommunity(String name){
//        mapOfCommunity.remove(name);
//    }
//
//    /**
//     * Return the community with the given name.
//     * @param name: The name of the community
//     * @return Community that with the name
//     */
//    public Community getCommunity(String name){
//        return mapOfCommunity.get(name);
//    }
//}


