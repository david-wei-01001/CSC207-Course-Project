package User;

import AchievementSystem.AchievementManager;
import Graph.DirectedGraph;
import Graph.GraphManager;
import CommunitySystem.CommunityLibrary;
import Posts.PublishedContents;
import Resource.ResourceManager;
import RewardSystem.RewardManager;
import User.User;
import User.UserManager;
import constants.Achievements;

/**
 * The Facade class that unifies all actions of the user currently using our program.
 */
public class UserActionFacade {

    /**
     * Each of the following six use cases contain the concrete implementation of
     * the methods that execute current user's action.
     */
    private UserManager userManager;
    private RewardManager rewardManager;
    private AchievementManager achievementManager;
    private GraphManager graphManager;
    private CommunityLibrary communityLibrary;
    private ResourceManager resourceManager;

    /**
     * the user currently using our program.
     */
    private User currentUser;


    public UserActionFacade(UserManager userManager,
                            RewardManager rewardManager,
                            AchievementManager achievementManager,
                            GraphManager graphManager,
                            CommunityLibrary communityLibrary,
                            ResourceManager resourceManager) {

        this.userManager = userManager;
        this.rewardManager = rewardManager;
        this.achievementManager = achievementManager;
        this.graphManager = graphManager;
        this.communityLibrary = communityLibrary;
        this.resourceManager = resourceManager;
        /**
         * initialize currentUser with no UserInfo.
         */
        this.currentUser = new User(null);
    }

    /**
     * Set currentUser to be the User with the inputted userName.
     * @param userName userName of the User to be set as currentUser.
     * @throws Exception if there is no User with the inputted userName.
     */
    public void setCurrentUserTo(String userName) throws Exception {
        currentUser = new User(userManager.getAUserInfo(userName));
    }

    /**
     * Add a graph to currentUser.
     * @param graph the graph being added
     */
    public void addGraphToCurrentUser(DirectedGraph graph) {
        userManager.addGraphTo(currentUser, graph);
    }

    /**
     * Set a new username for currentUser.
     * @param newUserName new username of currentUser.
     * @throws Exception throws exception if newUserName has been taken
     */
    public void setUserNameOfCurrentUser(String newUserName) throws Exception {
        userManager.setUserNameOf(currentUser, newUserName);
    }

    /**
     * Set a new email for currentUser.
     * @param newEmail the new email of currentUser.
     */
    public void setEmailOfCurrentUser(String newEmail) {
        userManager.setEmailOf(currentUser, newEmail);
    }

    /**
     * Set a new password for currentUser.
     * @param newPassword the new password of currentUser
     */
    public void setPasswordOfCurrentUser(String newPassword) {
        userManager.setPasswordOf(currentUser, newPassword);
    }

    /**
     * download a resource for currentUser.
     * @param resourceId the id of the resource currentUser is downloading.
     */
    public void downloadResource(String resourceId) {
        resourceManager.downloadResource(currentUser, resourceId);
    }

    /**
     * create a post with currentUser as author, to a community.
     * @param communityName the name of the community this post is going to
     * @param content the content of this post
     * @throws Exception
     */
    public void createPost(String communityName, String content) throws Exception {
        String postId = communityLibrary.addPost(currentUser, communityName, content);
        currentUser.getUserInfo().addToListOfPostId(postId);
        boolean achievementAwarded = achievementManager.requestAchievement(currentUser,
                Achievements.ARRAY_OF_POST_THRESHOLDS,
                Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT,
                currentUser.getUserInfo().getListOfPostId().size());
        if (achievementAwarded) {
            rewardManager.addRewardPoint(currentUser,
                    Achievements.MAP_POST_THRESHOLDS_TO_REWARD.get(currentUser.getUserInfo().getListOfPostId().size()));
        }

    }

    /**
     * increase the total number of logins of currentUser by 1.
     */
    public void incrementTotalLogins() {
        currentUser.getUserInfo().incrementTotalLogins();
        boolean achievementAwarded = achievementManager.requestAchievement(currentUser,
                Achievements.ARRAY_OF_TOTAL_LOGINS_THRESHOLDS,
                Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_ACHIEVEMENT,
                currentUser.getUserInfo().getTotalLogins());
        if (achievementAwarded) {
            rewardManager.addRewardPoint(currentUser,
                    Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_REWARD.get(currentUser.getUserInfo().getTotalLogins()));
        }
    }

    /**
     * like a publishedContent. This publishedContent can be a Post or a Comment.
     * @param publishedContents the publishedContent being liked
     */
    public void like(PublishedContents publishedContents){
        publishedContents.like();
        rewardManager.addRewardPoint(publishedContents.getCreator(), rewardManager.getPointsRewardedPerLike());
    }

    /**
     * Get the instance variable, communityManager.
     * @return communityManager
     */
    public CommunityLibrary getCommunityLibrary() {
        return communityLibrary;
    }

    /**
     * Get the instance variable, resourceManager.
     * @return resourceManager
     */
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    /**
     * Get the instance variable, currentUser.
     * @return currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Get the instance variable, achievementManager.
     * @return achievementManager
     */
    public AchievementManager getAchievementManager() {
        return achievementManager;
    }

    /**
     * Get the instance variable, userManager.
     * @return userManager
     */
    public UserManager getUserManager() {
        return userManager;
    }
}
