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
import User.InterfaceAction;

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

    public boolean checkUserLogin(){
        return currentUser.getUserInfo() != null;
    }

    public void addGraphToCurrentUser(DirectedGraph graph) throws Exception {
        if(checkUserLogin()){
        userManager.addGraphTo(currentUser, graph);}
        else{
            InterfaceAction.loginOrSignup();
        }
    }

    public void setUserNameOfCurrentUser(String newUserName) throws Exception {
        if(checkUserLogin()){
            userManager.setUserNameOf(currentUser, newUserName);}
        else{
            InterfaceAction.loginOrSignup();
        }
    }

    public void setEmailOfCurrentUser(String newEmail) throws Exception {
        if(checkUserLogin()){
            userManager.setEmailOf(currentUser, newEmail);}
        else{
            InterfaceAction.loginOrSignup();
        }
    }

    public void setPasswordOfCurrentUser(String newPassword) throws Exception {
        if(checkUserLogin()){
            userManager.setPasswordOf(currentUser, newPassword);}
        else{
            InterfaceAction.loginOrSignup();
        }
    }


}
