package user;

import constants.HasName;
import constants.IterableMap;
import graph.DirectedGraph;
import resource.Resource;
import constants.Achievements;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The class that stores all information of a main.user.
 */
public class User implements HasName, Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private String userName;
    private String email;
    private String password;
    private int rewardPoints;
    private final LocalDate lastLogin;
    private int totalLogins;
    private final ArrayList<String> listOfPostId = new ArrayList<>();
    private final Map<String, DirectedGraph> mapOfGraph = new HashMap<>();
    private final Map<String, Resource> mapOfResource = new HashMap<>();
    private final Map<String, Boolean> mapOfAchievement = new HashMap<>();


    /**
     * The constructor called when a user is created.
     *
     * @param userName the username of a user
     * @param email the email of a user
     * @param password the password of a user
     */
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.rewardPoints = 0;
        this.lastLogin = LocalDate.now();
        this.totalLogins = 0;
        initializeMapOfAchievement();
    }


    /**
     * add a post id.
     *
     * @param id the id of the post being added.
     */
    public void addToListOfPostId(String id) {
        this.listOfPostId.add(id);
    }

    /**
     * increase the total number of logins by 1.
     */
    public void incrementTotalLogins() {
        totalLogins += 1;
    }

    /**
     * Initialize mapOfAchievement, with all achievement not received.
     */
    public void initializeMapOfAchievement() {
        for (String achievement : Achievements.ARRAY_OF_ALL_ACHIEVEMENTS) {
            mapOfAchievement.put(achievement, false);
        }
    }

    @Override
    public String getName() {
        return userName;
    }

    public void setUsername(String newName) {
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

    public void addRewardPoints(int rewardPoints) {
        this.rewardPoints += rewardPoints;
    }

    public void addGraph(DirectedGraph graph){
        mapOfGraph.put(graph.getName(), graph);
    }

    public Map<String, DirectedGraph> getMapOfGraph(){
        return this.mapOfGraph;
    }

    public void addResource(Resource resource){
        mapOfResource.put(resource.getId(), resource);
    }

    public Map<String, Resource> getMapOfResource(){
        return this.mapOfResource;
    }



    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rewardPoints=" + rewardPoints +
                ", lastLogin=" + lastLogin +
                ", totalLogins=" + totalLogins +
                ", listOfPostId=" + listOfPostId +
                ", listOfGraph=" + mapOfGraph +
                ", mapOfResource=" + mapOfResource +
                ", mapOfAchievement=" + mapOfAchievement +
                '}';
    }

    public ArrayList<String> getListOfPostId() {
        return listOfPostId;
    }

    public int getTotalLogins() {
        return totalLogins;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Boolean> getMapOfAchievement() {
        return mapOfAchievement;
    }

    public boolean hasGraph(String graphName) {
        return mapOfGraph.containsKey(graphName);
    }

    /**
     * @return a string containing all Achievements achieved by this user
     */
    public String displayAchievement() {
        StringBuilder achievements = new StringBuilder();
        for(String name: mapOfAchievement.keySet()){
            Boolean status = mapOfAchievement.get(name);
            if(status) {
                achievements.append(name).append(": acquired").append("\n");
            } else {
                achievements.append(name).append(": not acquired").append("\n");
            }
        }
        return achievements.toString();
    }
}
