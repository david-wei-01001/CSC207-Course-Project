package user;

import constants.HasName;
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
    private String username;
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
     * @param username the username of a user
     * @param email the email of a user
     * @param password the password of a user
     */
    public User(String username, String email, String password) {
        this.username = username;
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

    /**
     * @return the name of this HasName Object.
     */
    @Override
    public String getName() {
        return username;
    }

    /**
     * set the username to be the given name
     *
     * @param newName a new username
     */
    public void setUsername(String newName) {
        this.username = newName;
    }

    /**
     * set the email to be the given email
     *
     * @param newEmail a new email
     */
    public void setEmail(String newEmail) {
        email = newEmail;
    }

    /**
     * @return the reward points of this user
     */
    public int getRewardPoints() {
        return this.rewardPoints;
    }

    /**
     * set the reward points to be the given reward points
     *
     * @param rewardPoints a new amount of reward points
     */
    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    /**
     * add the reward points to this user
     *
     * @param rewardPoints amount of reward points to be incremented
     */
    public void addRewardPoints(int rewardPoints) {
        this.rewardPoints += rewardPoints;
    }

    /**
     * add the DirectedGraph to this user
     *
     * @param graph a DirectedGraph to be added
     */
    public void addGraph(DirectedGraph graph){
        mapOfGraph.put(graph.getName(), graph);
    }

    /**
     * @return a map of all DirectedGraphs of this user
     */
    public Map<String, DirectedGraph> getMapOfGraph(){
        return this.mapOfGraph;
    }

    /**
     * add the resource to this user
     *
     * @param resource a resource to be added
     */
    public void addResource(Resource resource){
        mapOfResource.put(resource.getId(), resource);
    }

    /**
     * @return a map of all resources of this user
     */
    public Map<String, Resource> getMapOfResource(){
        return this.mapOfResource;
    }


    /**
     * @return a string representation of this user.
     */
    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
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

    /**
     * @return a list of the IDs of all posts of this user
     */
    public ArrayList<String> getListOfPostId() {
        return listOfPostId;
    }

    /**
     * @return the total number of logins of this user
     */
    public int getTotalLogins() {
        return totalLogins;
    }

    /**
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return a map of all achievements of this user
     */
    public Map<String, Boolean> getMapOfAchievement() {
        return mapOfAchievement;
    }

    /**
     * Check if the given graph is in the user's mapOfGraph
     *
     *  @param graphName the name of a graph to be checked
     * @return whether the given graph is in the user's mapOfGraph
     */
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
