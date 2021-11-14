package UseCase;

import AchievementSystem.AchievementManager;
import Graph.DirectedGraph;
import Graph.GraphManager;
import Java.CommunityLibrary;
import Posts.Post;
import Posts.PublishedContents;
import Resource.ResourceManager;
import RewardSystem.RewardManager;
import User.User;
import User.UserManager;
import constants.Achievements;

public class Facade {

    private UserManager userManager;
    private RewardManager rewardManager;
    private AchievementManager achievementManager;
    private GraphManager graphManager;
    private CommunityLibrary communityLibrary;
    private ResourceManager resourceManager;
    private User currentUser;


    public Facade(UserManager userManager,
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
     *
     * @param graph
     */
    public void addGraphToCurrentUser(DirectedGraph graph) {
        userManager.addGraphTo(currentUser, graph);
    }

    /**
     *
     * @param newUserName
     * @throws Exception
     */
    public void setUserNameOfCurrentUser(String newUserName) throws Exception {
        userManager.setUserNameOf(currentUser, newUserName);
    }

    /**
     *
     * @param newEmail
     */
    public void setEmailOfCurrentUser(String newEmail) {
        userManager.setEmailOf(currentUser, newEmail);
    }

    /**
     *
     * @param newPassword
     *
     *
     *
     */
    public void setPasswordOfCurrentUser(String newPassword) {
        userManager.setPasswordOf(currentUser, newPassword);
    }


    public void downloadResource(String resourceId) {
        resourceManager.downloadResource(currentUser, resourceId);
    }

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

    public void like(PublishedContents publishedContents){
        publishedContents.like();
        rewardManager.addRewardPoint(publishedContents.getCreator(), rewardManager.getPointsRewardedPerLike());
    }


}
