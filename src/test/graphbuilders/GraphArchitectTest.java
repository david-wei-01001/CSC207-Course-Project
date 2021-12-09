package graphbuilders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphArchitectTest {

    @Test
    void setBuilderAndBuildGraph() {
        try {
            GraphArchitect.setBuilderAndBuildGraph("Alfred is the most handsome man in the world!");
        } catch (Exception e) {
            assertEquals("This is not a built-in tree.", e.getMessage());
        }
    }
}