package Test;

import Graph.DirectedGraph;
import Graph.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DirectedGraphTest {
    DirectedGraph graph;

    @Before
    public void setUp() {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex[] pythonIntroArray = {pythonIntro};
        graph = new DirectedGraph(pythonIntroArray, "CS Introduction Series");
    }

    @Test(timeout = 50)
    public void testGetVertex() throws DirectedGraph.VertexNotInGraphException {
        Vertex python = graph.getVertex("Introductory Python");
        assertEquals("Introductory Python", python.getName());
    }

    @Test(timeout = 50)
    public void testAddVertex() throws DirectedGraph.VertexNotInGraphException {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        ArrayList<Vertex> end = new ArrayList<>();
        assertTrue(graph.getVertices().containsKey("Introductory Java"));
        assertTrue(graph.getVertices().containsKey("Introductory Python"));
        assertEquals(graph.getVertices().get("Introductory Python")[0], python);
        assertEquals(graph.getVertices().get("Introductory Java")[0], javaIntro);
        assertEquals(graph.getVertices().get("Introductory Python")[1], end);
        assertEquals(graph.getVertices().get("Introductory Java")[1], end);
    }

    @Test(timeout = 50)
    public void testAddEdge() throws DirectedGraph.VertexNotInGraphException {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        Vertex[] edge = {python, javaIntro};
        graph.addEdge(edge);
        ArrayList<Vertex> test = new ArrayList<>();
        test.add(javaIntro);
        ArrayList<Vertex> result = ((ArrayList<Vertex>) graph.getVertices().get("Introductory Python")[1]);
        assertEquals(test, result);
    }

    @Test(timeout = 50)
    public void testdeleteEdge() throws DirectedGraph.VertexNotInGraphException {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        Vertex[] edge = {python, javaIntro};
        graph.addEdge(edge);
        graph.deleteEdge(edge);
        ArrayList<Vertex> test = new ArrayList<>();
        ArrayList<Vertex> result = ((ArrayList<Vertex>) graph.getVertices().get("Introductory Python")[1]);
        assertEquals(test, result);
    }

    @Test(timeout = 50)
    public void testdeleteVertex() throws DirectedGraph.VertexNotInGraphException {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        Vertex[] edge = {python, javaIntro};
        graph.addEdge(edge);
        graph.deleteVertex("Introductory Java");
        ArrayList<Vertex> end = new ArrayList<>();
        assertTrue(graph.getVertices().containsKey("Introductory Python"));
        assertEquals(graph.getVertices().get("Introductory Python")[0], python);
        assertEquals(graph.getVertices().get("Introductory Python")[1], end);
    }

    @Test(timeout = 50)
    public void testAvailableVertex() throws DirectedGraph.VertexNotInGraphException {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(Intro165);
        graph.addVertex(javaIntro);
        Vertex[] edge1 = {python, javaIntro};
        graph.addEdge(edge1);
        Vertex[] edge2 = {python, Intro165};
        graph.addEdge(edge2);
        graph.complete("Introductory Python");
        HashMap<String, Vertex> testMap = new HashMap<>();
        testMap.put("0", javaIntro);
        testMap.put("1", Intro165);
        assertEquals(graph.availableVertex(), testMap);
    }

    @Test(timeout = 50)
    public void testCaseWithTwoPreqForAdd() throws DirectedGraph.VertexNotInGraphException {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex compIntro = new Vertex("Introductory Combbb");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex JavaIntro = new Vertex("Introductory Java");
        Vertex CIntro = new Vertex("Introductory C++");
        Vertex Intro236 = new Vertex("CSC236");
        Vertex Intro209 = new Vertex("CSC209");
        Vertex Intro263 = new Vertex("CSC263");
        Vertex[] Starter = {pythonIntro};
        DirectedGraph graph = new DirectedGraph(Starter, "CS Introduction Series");
        Vertex[] v1 = {pythonIntro, JavaIntro};
        Vertex[] vx = {compIntro, JavaIntro};
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
        graph.addEdge(vx);
        assertEquals(JavaIntro.getInLevel(), 2);
    }

    @Test(timeout = 50)
    public void testCaseWithTwoPreqForComplete() throws DirectedGraph.VertexNotInGraphException {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex compIntro = new Vertex("Introductory Combbb");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex JavaIntro = new Vertex("Introductory Java");
        Vertex CIntro = new Vertex("Introductory C++");
        Vertex Intro236 = new Vertex("CSC236");
        Vertex Intro209 = new Vertex("CSC209");
        Vertex Intro263 = new Vertex("CSC263");
        Vertex[] Starter = {pythonIntro};
        DirectedGraph graph = new DirectedGraph(Starter, "CS Introduction Series");
        Vertex[] v1 = {pythonIntro, JavaIntro};
        Vertex[] vx = {compIntro, JavaIntro};
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
        graph.addEdge(vx);
        graph.complete("Introductory Python");
        assertEquals(graph.availableVertex().toString(), "{0=Introductory C++, 1=CSC165}");
        assertEquals(JavaIntro.getInLevel(), 1);
    }
    @Test(timeout = 50)
    public void testCaseWithTwoPreqForDelete() throws DirectedGraph.VertexNotInGraphException {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex compIntro = new Vertex("Introductory Combbb");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex JavaIntro = new Vertex("Introductory Java");
        Vertex CIntro = new Vertex("Introductory C++");
        Vertex Intro236 = new Vertex("CSC236");
        Vertex Intro209 = new Vertex("CSC209");
        Vertex Intro263 = new Vertex("CSC263");
        Vertex[] Starter = {pythonIntro};
        DirectedGraph graph = new DirectedGraph(Starter, "CS Introduction Series");
        Vertex[] v1 = {pythonIntro, JavaIntro};
        Vertex[] vx = {compIntro, JavaIntro};
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
        graph.addEdge(vx);
        graph.deleteEdge(vx);
//        assertEquals(graph.availableVertex(), 0);
        assertEquals(JavaIntro.getInLevel(), 1);
    }
}