package Java;

import Graph.DirectedGraph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** TODO: add javadoc
 *
 */

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private ArrayList<DirectedGraph> listOfGraph = new ArrayList<>();
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

    @Override
    public String toString() {
        return "User: " + this.name;
    }
}
