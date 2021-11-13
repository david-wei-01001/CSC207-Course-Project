package UseCase;

import AchievementSystem.AchievementManager;
import Graph.DirectedGraph;
import Graph.GraphManager;
import Java.CommunityLibrary;
import Posts.PublishedContents;
import Resource.ResourceManager;
import RewardSystem.RewardManager;
import User.User;
import User.UserManager;

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

    public void addGraphToCurrentUser(DirectedGraph graph) {
        userManager.addGraphTo(currentUser, graph);
    }

    public void setUserNameOfCurrentUser(String newUserName) throws Exception {
        userManager.setUserNameOf(currentUser, newUserName);
    }

    public void setEmailOfCurrentUser(String newEmail) {
        userManager.setEmailOf(currentUser, newEmail);
    }

    public void setPasswordOfCurrentUser(String newPassword) {
        userManager.setPasswordOf(currentUser, newPassword);
    }

    public void like(PublishedContents publishedContents){
        publishedContents.like();
        rewardManager.addRewardPoint(publishedContents.getCreator(), rewardManager.getPointsRewardedPerLike());
    }

}
