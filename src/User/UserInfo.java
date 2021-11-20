package User;

import Graph.DirectedGraph;
import Posts.Post;
import Resource.Resource;
import constants.Achievements;

import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    private String userName;
    private String email;
    private String password;
    private int rewardPoints;
    private LocalDate lastLogin;
    private int totalLogins;

    public ArrayList<Post> getListOfPost() {
        return listOfPost;
    }

    private ArrayList<Post> listOfPost = new ArrayList<>();
    private ArrayList<DirectedGraph> listOfGraph = new ArrayList<>();
    private ArrayList<Resource> listOfResource = new ArrayList<>();

    public Map<String, Boolean> getMapOfAchievement() {
        return mapOfAchievement;
    }

    private Map<String, Boolean> mapOfAchievement = new HashMap<>();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);


    /**
     * Instantiates UserInfo with userName, email, and password of user.
     * @param userName the user's username.
     * @param email the user's email.
     * @param password the user's password.
     */
    public UserInfo(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.rewardPoints = 0;
        this.lastLogin = LocalDate.now();
        this.totalLogins = 0;
    }

    /**
     * Adds a Post to the user's listOfPosts.
     * @param post the post that the user created.
     */
    public void addPost(Post post){
        listOfPost.add(post);
        boolean trigger = RequestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS, Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT, listOfPost.size());
        if (trigger){
            this.setRewardPoints(Achievements.MAP_POST_THRESHOLDS_TO_REWARD.get(listOfPost.size()));
        } //put it in facade when we write post()
    }

    /**
     * Increases the totalLogins by 1.
     */
    public void incrementTotalLogins() {
        totalLogins += 1;
        boolean trigger = RequestAchievement(Achievements.ARRAY_OF_TOTAL_LOGINS_THRESHOLDS, Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_ACHIEVEMENT, totalLogins);
        if(trigger){
            this.setRewardPoints(Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_REWARD.get(totalLogins));
        }
    }



    public void initializeMapOfAchievement() {
        for (String achievement : Achievements.ARRAY_OF_ALL_ACHIEVEMENTS) {
            mapOfAchievement.put(achievement, false);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String newName) {
        this.userName = newName;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public int getRewardPoints() {
        return this.rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void addGraph(DirectedGraph graph){
        listOfGraph.add(graph);
    }

    public ArrayList<DirectedGraph> getListOfGraph(){
        return this.listOfGraph;
    }

    public void addResource(Resource resource){
        listOfResource.add(resource);
    }

    public ArrayList<Resource> getListOfResource(){
        return this.listOfResource;
    }

    @Override
    public String toString() {
        return "User: " + this.userName;
    }



//    Deprecated:

//    private void checkAndRequestAchievement(int[] thresholds, int property, String achievementName) {
//        for (int threshold : thresholds) {
//            if (property == threshold) {
//                observable.firePropertyChange(achievementName, property - 1, property);
//            }
//        }
//    }

}
