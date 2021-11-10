package User;

import Graph.DirectedGraph;
import Resource.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private int rewardPoints;
    private ArrayList<DirectedGraph> listOfGraph = new ArrayList<>();
    private ArrayList<Resource> listOfResource = new ArrayList<>();
    private Map<String, Boolean> mapOfAchievement = new HashMap<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
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

    public void setRewardPoints(int newRewardPoints) {
        this.rewardPoints = newRewardPoints;
    }

    /**
     * TODO: change name of method to addGraph?
     * @param graph
     */

    /**
     *
     * @param graph
     */
    // name of this method has been changed
    public void addGraph(DirectedGraph graph){
        listOfGraph.add(graph);
    }

    public ArrayList getListOfGraph(){
        return this.listOfGraph;
    }

    public void addResource(Resource resource){
        listOfResource.add(resource);
    }

    public ArrayList getListOfResource(){
        return this.listOfResource;
    }

    @Override
    public String toString() {
        return "User: " + this.name;
    }

}
