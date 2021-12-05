package graphbuilders;

import constants.BuiltInGraphs;
import graph.DirectedGraph;
import graph.Vertex;

import java.util.HashMap;
import java.util.Map;

public class MathematicsGraphBuilder implements GraphBuilder{

    @Override
    public Map<String, Vertex> buildVertices() {
        Map<String, Vertex> result = new HashMap<>();
        String[] vertexNames = {"Single Variable Calculus",
                "Linear Algebra",
                "Discrete Mathematics",
                "Multivariable Calculus",
                "Probability Theory",
                "Mathematical Statistics",
                "Linear Regression",
                "Stochastic Processes",
                "ODE",
                "PDE",
                "Point-set Topology",
                "Differential Geometry",
                "Real Analysis",
                "Complex Analysis",
                "Abstract Algebra",
                "Combinatorics",
                "Graph Theory",
                "Number Theory",
                "Algebraic Number Theory",
                "Analytic Number Theory",
                "Riemann Geometry",
                "Algebraic Geometry",
                "Commutative Algebra",
                "Functional Analysis",
                "K-Theory and C* algebra",
                "Smooth Manifolds",
                "Algebraic Topology"
        };

        for (String vertexName : vertexNames) {
            result.put(vertexName, new Vertex(vertexName));

        }

        return result;
    }

    @Override
    public Vertex[][] buildDirectedEdges(Map<String, Vertex> vertices) {
        return new Vertex[][]{
                new Vertex[]{vertices.get("Single Variable Calculus"), vertices.get("Multivariable Calculus")},
                new Vertex[]{vertices.get("Multivariable Calculus"), vertices.get("Probability Theory")},
                new Vertex[]{vertices.get("Probability Theory"), vertices.get("Mathematical Statistics")},
                new Vertex[]{vertices.get("Mathematical Statistics"), vertices.get("Linear Regression")},
                new Vertex[]{vertices.get("Linear Regression"), vertices.get("Stochastic Processes")},
                new Vertex[]{vertices.get("Multivariable Calculus"), vertices.get("ODE")},
                new Vertex[]{vertices.get("ODE"), vertices.get("PDE")},
                new Vertex[]{vertices.get("Multivariable Calculus"), vertices.get("Point-set Topology")},
                new Vertex[]{vertices.get("Point-set Topology"), vertices.get("Smooth Manifolds")},
                new Vertex[]{vertices.get("Smooth Manifolds"), vertices.get("Algebraic Topology")},
                new Vertex[]{vertices.get("Point-set Topology"), vertices.get("Real Analysis")},
                new Vertex[]{vertices.get("Real Analysis"), vertices.get("Functional Analysis")},
                new Vertex[]{vertices.get("Point-set Topology"), vertices.get("Differential Geometry")},
                new Vertex[]{vertices.get("Functional Analysis"), vertices.get("K-Theory and C* algebra")},
                new Vertex[]{vertices.get("Differential Geometry"), vertices.get("Riemann Geometry")},
                new Vertex[]{vertices.get("Linear Algebra"), vertices.get("Abstract Algebra")},
                new Vertex[]{vertices.get("Abstract Algebra"), vertices.get("Algebraic Geometry")},
                new Vertex[]{vertices.get("Abstract Algebra"), vertices.get("Commutative Algebra")},
                new Vertex[]{vertices.get("Multivariable Calculus"), vertices.get("Complex Analysis")},
                new Vertex[]{vertices.get("Discrete Mathematics"), vertices.get("Combinatorics")},
                new Vertex[]{vertices.get("Discrete Mathematics"), vertices.get("Graph Theory")},
                new Vertex[]{vertices.get("Discrete Mathematics"), vertices.get("Number Theory")},
                new Vertex[]{vertices.get("Number Theory"), vertices.get("Algebraic Number Theory")},
                new Vertex[]{vertices.get("Number Theory"), vertices.get("Analytic Number Theory")}
        };
    }

    @Override
    public DirectedGraph buildGraph() throws Exception {
        Map<String, Vertex> vertices = buildVertices();

        Vertex[][] directedEdges = buildDirectedEdges(vertices);
        DirectedGraph result = new DirectedGraph(new Vertex[] {vertices.get("Single Variable Calculus"),
                vertices.get("Linear Algebra"),
                vertices.get("Discrete Mathematics"),
        },
                BuiltInGraphs.MATHEMATICS);
        for (Vertex[] directedEdge : directedEdges) {
            result.addEdge(directedEdge);
        }
        result.setTreeid(0);
        return result;
    }
}
