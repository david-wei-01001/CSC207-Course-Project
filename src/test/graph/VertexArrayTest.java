package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static constants.Exceptions.EDGE_NOT_FOUND;
import static org.junit.Assert.*;

/**
 * Test the VertexArray class
 */
public class VertexArrayTest {
    VertexArray va;

    /**
     * The setup method that setup each test.
     * It creates a DirectedGraph.
     */
    @Before
    public void setUp() {
        Vertex pythonIntro = new Vertex("Introductory Python");
        va = new VertexArray(pythonIntro);
    }

    /**
     * Test if getVertex method correctly fetch a vertex from the main.graph.
     */
    @Test
    public void testAddEdgeEndEqualAndIsEnd() {
        Vertex java = new Vertex("Introductory Java");
        va.addEdge(java);
        ArrayList<Vertex> result = new ArrayList<>();
        result.add(java);
        assertTrue(va.endEqual(result));
        assertTrue(va.isEnd(java));
    }

    /**
     * Test if indexToInsert method returns the correct index if END is empty.
     */
    @Test
    public void testIndexToInsertEmpty(){
        Vertex java = new Vertex("Introductory Java");
        assertEquals(va.indexToInsert(java), 0);
    }

    /**
     * Test if indexToInsert method returns the correct index if vertices have the same inLevel.
     */
    @Test
    public void testIndexToInsertSameInLevel(){
        Vertex java = new Vertex("Introductory Java");
        va.addEdge(java);
        Vertex toc = new Vertex("Theory of Computation");
        va.addEdge(toc);
        Vertex os = new Vertex("Operating System");
        assertTrue(va.indexToInsert(os) <= 2);
    }

    /**
     * Test if indexToInsert method returns the correct index if vertex to be inserted has more inLevel.
     */
    @Test
    public void testIndexToInsertMoreInLevel(){
        Vertex java = new Vertex("Introductory Java");
        va.addEdge(java);
        Vertex toc = new Vertex("Theory of Computation");
        va.addEdge(toc);
        Vertex os = new Vertex("Operating System");
        os.addInLevel();
        assertEquals(va.indexToInsert(os), 2);
    }

    /**
     * Test if indexToInsert method returns the correct index if vertex to be inserted has less inLevel.
     */
    @Test
    public void testIndexToInsertLessInLevel(){
        Vertex java = new Vertex("Introductory Java");
        java.addInLevel();
        va.addEdge(java);
        Vertex toc = new Vertex("Theory of Computation");
        toc.addInLevel();
        va.addEdge(toc);
        Vertex os = new Vertex("Operating System");
        assertEquals(va.indexToInsert(os), 0);
    }

    /**
     * Test if indexToInsert method returns the correct index if vertex to be inserted has inLevel between two vertices.
     */
    @Test
    public void testIndexToInsertMiddleInLevel(){
        Vertex java = new Vertex("Introductory Java");
        va.addEdge(java);
        Vertex toc = new Vertex("Theory of Computation");
        toc.addInLevel();
        toc.addInLevel();
        va.addEdge(toc);
        Vertex os = new Vertex("Operating System");
        os.addInLevel();
        assertEquals(va.indexToInsert(os), 1);
    }

    /**
     * Test if getStart method correctly fetch the starting vertex.
     */
    @Test
    public void testGetStart(){
        assertEquals("Introductory Python", va.getStart().toString());
    }

    /**
     * Test if deleteThisEdge method correctly delete a DirectedEdge.
     */
    @Test
    public void testDeleteThisEdge(){
        try {
            Vertex java = new Vertex("Introductory Java");
            va.addEdge(java);
            Vertex toc = new Vertex("Theory of Computation");
            va.addEdge(toc);
            Vertex os = new Vertex("Operating System");
            va.addEdge(os);
            va.deleteThisEdge(toc);
            ArrayList<Vertex> result = new ArrayList<>();
            result.add(java);
            result.add(os);
            assertTrue(va.endEqual(result));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if deleteThisEdge method correctly throws an exception.
     */
    @Test
    public void testDeleteThisEndException() {
        try {
            Vertex java = new Vertex("Introductory Java");
            va.addEdge(java);
            Vertex os = new Vertex("Operating System");
            va.deleteThisEdge(os);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), EDGE_NOT_FOUND);
        }
    }

    /**
     * Test if toString method function as intended if there is no ending vertex.
     */
    @Test
    public void testToStringNoEndingVertex(){
        assertEquals(va.toString(), "Introductory Python\n");
    }

    /**
     * Test if toString method function as intended if there are ending vertices.
     */
    @Test
    public void testToStringWithEndingVertex(){
        Vertex java = new Vertex("Introductory Java");
        va.addEdge(java);
        Vertex toc = new Vertex("Theory of Computation");
        va.addEdge(toc);
        Vertex os = new Vertex("Operating System");
        va.addEdge(os);
    }

    /**
     * Test if updateVertex method function as intended.
     */
    @Test
    public void testUpdateVertex(){
        try {
            Vertex java = new Vertex("Introductory Java");
            va.addEdge(java);
            Vertex toc = new Vertex("Theory of Computation");
            va.addEdge(toc);
            Vertex os = new Vertex("Operating System");
            va.addEdge(os);
            toc.addInLevel();
            va.updateVertex(toc);
            ArrayList<Vertex> result = new ArrayList<>();
            result.add(java);
            result.add(os);
            result.add(toc);
            assertTrue(va.endEqual(result));
        } catch (Exception e) {
            fail();
        }

    }
}