package Graph;

import java.util.HashMap;

public class GraphManager {
    private HashMap<String, DirectedGraph> mapOfGraph = new HashMap<>();
    private int numGraph;

    public GraphManager() {

        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex JavaIntro = new Vertex("Introductory Java");
        Vertex CIntro = new Vertex("Introductory C++");
        Vertex Intro236 = new Vertex("CSC236");
        Vertex Intro209 = new Vertex("CSC209");
        Vertex Intro263 = new Vertex("CSC263");
        Vertex[] Starter = {pythonIntro};
        DirectedGraph graph = new DirectedGraph(Starter, "CS Introduction Series");
        Vertex[] v1 = {pythonIntro, JavaIntro};
        Vertex[] v2 = {pythonIntro, CIntro};
        Vertex[] v3 = {Intro165, Intro236};
        Vertex[] v4 = {Intro236, Intro263};
        Vertex[] v5 = {JavaIntro, Intro209};
        Vertex[] v6 = {pythonIntro, Intro165};
        graph.addEdge(v1);
        graph.addEdge(v2);
        graph.addEdge(v3);
        graph.addEdge(v4);
        graph.addEdge(v5);
        graph.addEdge(v6);
        addGraph(graph);

    }

    public HashMap<String, DirectedGraph> getAllGraphs(){
        return mapOfGraph;
    }

    public void addGraph(DirectedGraph graph) {
        numGraph += 1;
        mapOfGraph.put(Integer.toString(numGraph), graph);

    }


}
