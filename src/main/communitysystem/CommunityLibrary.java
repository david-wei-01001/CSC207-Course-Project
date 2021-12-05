package communitysystem;

import constants.Exceptions;
import rewardsystem.RewardManager;
import user.User;

/**
 * The use case that controls a user's interaction with the achievement system.
 */
public class CommunityLibrary {
    private CommunityList mapOfCommunity = new CommunityList();
    private Community currentCommunity;
    private User currentUser;

    /**
     * Setter function for mapOfCommunity
     * @param mapOfCommunity the map of community
     */
    public void setMapOfCommunity(CommunityList mapOfCommunity) {
        this.mapOfCommunity = mapOfCommunity;
    }

    /**
     * Setter function for currentUser
     * @param currentUser the current user
     */
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
     //     * @param user the main. user adding this post
     //     * @param communityName the name of the community this post is being added to
     //     * @param content the content of the post being added
     //     * @return the id of the post being added.
     //     * @throws Exception throws an exception if the community with the communityName is not found.
     //     */
    public void createPost(String content,
                           RewardManager rewardManager) {
        String postId = currentCommunity.addPublishedContent(content, currentUser);
        currentUser.addToListOfPostId(postId);
        rewardManager.addRewardPoint(5);


    }

    /**
     * Set the currentCommunity with the given communityName
     * @param communityName: The name of the community to set for currentCommunity
     */
    public void setCurrentCommunity(String communityName) throws Exception {
        if (checkCommunityExist(communityName)) {
            this.currentCommunity = mapOfCommunity.get(communityName);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_COMMUNITY);
        }

    }

    /**
     * The getter function for mapOfCommunity
     * @return mapOfCommunity
     */
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


