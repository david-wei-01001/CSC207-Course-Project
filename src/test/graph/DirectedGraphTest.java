package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static constants.Exceptions.*;
import static org.junit.Assert.*;

/**
 * Test the DirectedGraph class
 */
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
     * Test if getVertex method correctly fetch a vertex from the main.graph.
     */
    @Test
    public void testGetVertex() {
        try {
            Vertex python = graph.getVertex("Introductory Python");
            assertEquals("Introductory Python", python.getName());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if addVertex method correctly fetch a vertex from the main.graph.
     */
    @Test
    public void testAddVertex() {
        try {
            Vertex javaIntro = new Vertex("Introductory Java");
            Vertex python = graph.getVertex("Introductory Python");
            graph.addVertex(javaIntro);
            ArrayList<Vertex> end = new ArrayList<>();
            assertTrue(graph.getVertices().containsKey("Introductory Java"));
            assertTrue(graph.getVertices().containsKey("Introductory Python"));
            assertEquals(graph.getVertexArray("Introductory Python").getStart(), python);
            assertEquals(graph.getVertexArray("Introductory Java").getStart(), javaIntro);
            assertTrue(graph.getVertexArray("Introductory Python").endEqual(end));
            assertTrue(graph.getVertexArray("Introductory Java").endEqual(end));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if addEdge method correctly add a DirectedEdge to the main.graph.
     */
    @Test
    public void testAddEdge() {
        try {
            Vertex javaIntro = new Vertex("Introductory Java");
            Vertex python = graph.getVertex("Introductory Python");
            graph.addVertex(javaIntro);
            Vertex[] edge = {python, javaIntro};
            graph.addEdge(edge);
            ArrayList<Vertex> test = new ArrayList<>();
            test.add(javaIntro);
            assertTrue(graph.getVertexArray("Introductory Python").endEqual(test));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if deleteEdge method correctly delete a DirectedEdge from the main.graph.
     */
    @Test
    public void testDeleteEdge() {
        try {
            Vertex javaIntro = new Vertex("Introductory Java");
            Vertex python = graph.getVertex("Introductory Python");
            graph.addVertex(javaIntro);
            Vertex[] edge = {python, javaIntro};
            graph.addEdge(edge);
            graph.deleteEdge(edge);
            ArrayList<Vertex> test = new ArrayList<>();
            assertTrue(graph.getVertexArray("Introductory Python").endEqual(test));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if deleteVertex method correctly delete a vertex from the main.graph.
     */
    @Test
    public void testDeleteVertex() {
        try {
            Vertex javaIntro = new Vertex("Introductory Java");
            Vertex python = graph.getVertex("Introductory Python");
            graph.addVertex(javaIntro);
            Vertex[] edge = {python, javaIntro};
            graph.addEdge(edge);
            graph.deleteVertex("Introductory Java");
            ArrayList<Vertex> end = new ArrayList<>();
            assertTrue(graph.getVertices().containsKey("Introductory Python"));
            assertEquals(graph.getVertexArray("Introductory Python").getStart(), python);
            assertTrue(graph.getVertexArray("Introductory Python").endEqual(end));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if availableVertex method function as intended.
     */
    @Test
    public void testAvailableVertex() {
        try {
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
            assertTrue(graph.availableVertex().containsValue(javaIntro));
            assertTrue(graph.availableVertex().containsValue(Intro165));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the class update inLevel correctly if a vertex has two level of prerequisites.
     */
    @Test
    public void testCaseWithTwoPreForAdd() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * test if the complete method update inLevel correctly if a vertex has two level of prerequisites.
     */
    @Test
    public void testCaseWithTwoPreForComplete() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
            assertTrue(graph.availableVertex().containsValue(CIntro));
            assertTrue(graph.availableVertex().containsValue(Intro165));
            assertEquals(JavaIntro.getInLevel(), 1);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the class update inLevel correctly if a vertex has two level of prerequisites and one is deleted.
     */
    @Test
    public void testCaseWithTwoPreForDelete() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
//        assertEquals(main.graph.availableVertex(), 0);
            assertEquals(JavaIntro.getInLevel(), 1);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the deleteEdge method correctly throws an exception if the starting vertex is not found
     */
    @Test
    public void testDeleteEdgeThrowExceptionFirstNotExist() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
                graph.deleteEdge(v7);
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), VERTEX_NOT_FOUND);
            }
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the deleteEdge method correctly throws an exception if the ending vertex is not found
     */
    @Test
    public void testDeleteEdgeThrowExceptionSecondNotExist() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
                graph.deleteEdge(v7);
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), EDGE_NOT_FOUND);
            }
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the deleteVertex method correctly throws an exception
     */
    @Test
    public void testDeleteVertexThrowException() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
            try{
                graph.deleteVertex("Introductory Philosophy");
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), VERTEX_NOT_FOUND);
            }
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the string version of getVertexArray method correctly throws an exception
     */
    @Test
    public void testGetVertexArrayStringThrowException() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
            try{
                graph.getVertexArray("Introductory Philosophy");
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), VERTEX_NOT_FOUND);
            }
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the vertex version of getVertexArray method correctly throws an exception
     */
    @Test
    public void testGetVertexArrayVertexThrowException() throws Exception {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex compIntro = new Vertex("Introductory Cobb");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex JavaIntro = new Vertex("Introductory Java");
        Vertex CIntro = new Vertex("Introductory C++");
        Vertex Intro236 = new Vertex("CSC236");
        Vertex Intro209 = new Vertex("CSC209");
        Vertex Intro263 = new Vertex("CSC263");
        Vertex[] starter = {pythonIntro};
        DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
        try{
            graph.getVertexArray(PhilIntro);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), VERTEX_NOT_FOUND);
        }
    }

    /**
     * Test if the getVertex method correctly throws an exception
     */
    @Test
    public void testGetVertexThrowException() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
            try{
                graph.getVertex("Introductory Philosophy");
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), VERTEX_NOT_FOUND);
            }
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the complete method correctly throws an exception if the vertex does not exist
     */
    @Test
    public void testCompleteNotExist() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
            try{
                graph.complete("Introductory Philosophy");
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), VERTEX_NOT_FOUND);
            }
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the complete method correctly throws an exception if the vertex has already been completed
     */
    @Test
    public void testCompleteAlreadyComplete() throws Exception {
        Vertex pythonIntro = new Vertex("Introductory Python");
        Vertex compIntro = new Vertex("Introductory Cobb");
        Vertex Intro165 = new Vertex("CSC165");
        Vertex JavaIntro = new Vertex("Introductory Java");
        Vertex CIntro = new Vertex("Introductory C++");
        Vertex Intro236 = new Vertex("CSC236");
        Vertex Intro209 = new Vertex("CSC209");
        Vertex Intro263 = new Vertex("CSC263");
        Vertex[] starter = {pythonIntro};
        DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
        try{
            graph.complete("Introductory Python");
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), VERTEX_ALREADY_COMPLETED);
        }
    }

    /**
     * Test if the complete method correctly throws an exception if the vertex is locked
     */
    @Test
    public void testCompleteLocked() {
        try {
            Vertex pythonIntro = new Vertex("Introductory Python");
            Vertex compIntro = new Vertex("Introductory Cobb");
            Vertex Intro165 = new Vertex("CSC165");
            Vertex JavaIntro = new Vertex("Introductory Java");
            Vertex CIntro = new Vertex("Introductory C++");
            Vertex Intro236 = new Vertex("CSC236");
            Vertex Intro209 = new Vertex("CSC209");
            Vertex Intro263 = new Vertex("CSC263");
            Vertex[] starter = {pythonIntro};
            DirectedGraph graph = new DirectedGraph(starter, "CS Introduction Series");
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
            try{
                graph.complete("CSC263");
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), VERTEX_LOCKED);
            }
        } catch (Exception e) {
            fail();
        }
    }
}
