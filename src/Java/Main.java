package Java;

import Graph.DirectedGraph;
import Graph.GraphManager;
import Graph.Vertex;

import java.util.Scanner;

import static Java.TempMainWalkThroughHelper.*;

public class Main {
    /**
     * Action by user is required
     */
    public static void init(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please take an action(\"Sign up\" or \"Quit\")");
        String action = scanner.nextLine();
        while (!action.equals("Sign up") && !action.equals("Quit")) {
            System.out.println("Please enter the correct command");
            action = scanner.nextLine();
        }
        if(action.equals("Quit")){
            System.exit(0);
        }
    }


    public static void main(String[] args){
        // Create the graph
        GraphManager graphManager = new GraphManager();
        // Create the user
        init();

        UserManager manager = new UserManager();

        User user = newUser();

        //commenting the following line to resolve an error. the error arises
        //from the change in parameter in User's constructor.

//        manager.addNewUser(user);

        DirectedGraph graph = getGraph(graphManager);

        graph.availableVertex();

        user.addGraph(graph);

        DirectedGraph userGraph = (DirectedGraph) user.getListOfGraph().get(0);

        Vertex node = gotVertex(userGraph);

        completeVertex(node, userGraph);

        Vertex node2 = gotVertex(userGraph);

        completeVertex(node2, userGraph);

        }

    }



