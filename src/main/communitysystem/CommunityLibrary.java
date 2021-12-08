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
     *
     * @param mapOfCommunity the map of community
     */
    public void setMapOfCommunity(CommunityList mapOfCommunity) {
        this.mapOfCommunity = mapOfCommunity;
    }

    /**
     * Setter function for currentUser
     *
     * @param currentUser the current user
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    /**
     * Check if this community exists or not
     *
     *  @param name: The community name
     * @return: This community exists or not
     */
    public boolean checkCommunityExist(String name){
        return mapOfCommunity.containsKey(name);
    }

    /**
     * Create a new community.
     *
     * @param name: The community name
     */
    public void addCommunity(String name){
        if(!checkCommunityExist(name)){
            Community com = new Community(name);
            mapOfCommunity.add(com);
        }
    }

    /**
     * Add a post to a community.
     *
     * @param content the content of the post being added
     * @param rewardManager The rewardManager Use Case that is going to award reward points for creating this Post.
     */
    public void createPost(String content,
                           RewardManager rewardManager) {
        String postId = currentCommunity.addPublishedContent(content, currentUser);
        currentUser.addToListOfPostId(postId);
        rewardManager.addRewardPoint(5);


    }

    /**
     * Set the currentCommunity with the given communityName
     *
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
     *
     * @return mapOfCommunity
     */
    public CommunityList getMapOfCommunity() {
        return mapOfCommunity;
    }

    /**
     * The getter function for currentUser
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * The getter function for currentCommunity
     *
     * @return the current user
     */
    public Community getCurrentCommunity() {
        return currentCommunity;
    }


}

