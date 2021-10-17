package Java;

import Graph.DirectedGraph;

import java.util.ArrayList;

/** TODO: add javadoc
 *
 */

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<DirectedGraph> listOfGraph = new ArrayList<>();

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

    public void addListOfGraph(DirectedGraph graph){
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
