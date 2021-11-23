package test;

import graph.DirectedGraph;
import graph.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static constants.Exceptions.*;
import static org.junit.Assert.*;

public class DirectedGraphTest {
    DirectedGraph graph;

    /**
     * The setup method that setup each test.
     * It creates a DirectedGraph.
     */
    @Before
    public void setUp() {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex[] pythonIntroArray = {pythonIntro};
        graph = new DirectedGraph(pythonIntroArray, "CS Introduction Series");
    }

    /**
     * Test if getVertex method correctly fetch a vertex from the graph.
     *
     * @throws Exception if the vertex to be gotten does not exist
     */
    @Test
    public void testGetVertex() throws Exception {
        Vertex python = graph.getVertex("Introductory Python");
        assertEquals("Introductory Python", python.getName());
    }

    /**
     * Test if addVertex method correctly fetch a vertex from the graph.
     *
     * @throws Exception if the vertex to be gotten does not exist
     */
    @Test
    public void testAddVertex() throws Exception {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        ArrayList<Vertex> end = new ArrayList<>();
        assertTrue(graph.getVertices().containsKey("Introductory Java"));
        assertTrue(graph.getVertices().containsKey("Introductory Python"));
        assertEquals(graph.getVertexArray("Introductory Python").getStart(), python);
        assertEquals(graph.getVertexArray("Introductory Java").getStart(), javaIntro);
        assertEquals(graph.getVertexArray("Introductory Python").getEnds(), end);
        assertEquals(graph.getVertexArray("Introductory Java").getEnds(), end);
    }

    @Test
    public void testAddEdge() throws Exception {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        Vertex[] edge = {python, javaIntro};
        graph.addEdge(edge);
        ArrayList<Vertex> test = new ArrayList<>();
        test.add(javaIntro);
        ArrayList<Vertex> result = graph.getVertexArray("Introductory Python").getEnds();
        assertEquals(test, result);
    }

    @Test
    public void testDeleteEdge() throws Exception {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        Vertex[] edge = {python, javaIntro};
        graph.addEdge(edge);
        graph.deleteEdge(edge);
        ArrayList<Vertex> test = new ArrayList<>();
        ArrayList<Vertex> result = graph.getVertexArray("Introductory Python").getEnds();
        assertEquals(test, result);
    }

    @Test
    public void testDeleteVertex() throws Exception {
        Vertex javaIntro = new Vertex("Introductory Java");
        Vertex python = graph.getVertex("Introductory Python");
        graph.addVertex(javaIntro);
        Vertex[] edge = {python, javaIntro};
        graph.addEdge(edge);
        graph.deleteVertex("Introductory Java");
        ArrayList<Vertex> end = new ArrayList<>();
        assertTrue(graph.getVertices().containsKey("Introductory Python"));
        assertEquals(graph.getVertexArray("Introductory Python").getStart(), python);
        assertEquals(graph.getVertexArray("Introductory Python").getEnds(), end);
    }

    @Test
    public void testAvailableVertex() throws Exception {
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
        Map<String, Vertex> testMap = new HashMap<>();
        testMap.put("0", javaIntro);
        testMap.put("1", Intro165);
        assertEquals(graph.availableVertex(), testMap);
    }

    @Test
    public void testCaseWithTwoPreqForAdd() throws Exception {
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

    @Test
    public void testCaseWithTwoPreqForComplete() throws Exception {
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

    @Test
    public void testCaseWithTwoPreqForDelete() throws Exception {
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

    @Test
    public void testDeleteEdgeThrowExceptionFirstNotExist() throws Exception {
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
        Vertex PhilIntro = new Vertex("Introductory Philosophy");
        Vertex[] v7 = {PhilIntro, Intro165};
       try{
           graph.addEdge(v7);
           fail();
       } catch (Exception e) {
           assertEquals(e.getMessage(), Vertex_NOT_FOUND);
       }

    }

    @Test
    public void testDeleteEdgeThrowExceptionSecondNotExist() throws Exception {
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
        Vertex PhilIntro = new Vertex("Introductory Philosophy");
        Vertex[] v7 = {Intro165, PhilIntro};
        try{
            graph.addEdge(v7);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), Vertex_NOT_FOUND);
        }

    }
}
