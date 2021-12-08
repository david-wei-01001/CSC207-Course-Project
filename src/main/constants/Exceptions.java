package constants;

/**
 * This class stores all exception messages of this program.
 */
public class Exceptions {
    /**
     * Exceptions class imitates that of JavaShell's.
     */

    //error messages used by GraphArchitect
    public static final String CANNOT_RECOGNIZE_BUILT_IN_TREE = "This is not a built-in tree.";

    //error messages used by UserManager
    public static final String USER_NAME_TAKEN = "This username has already been taken.";
    public static final String INCORRECT_USERNAME = "Invalid username";
    public static final String SAME_USERNAME_AS_CURRENT = "Same username as your current one.";
    public static final String EMAIL_ALREADY_EXIST = "This email already exists. Please try a different one.";

    //error messages used by GraphManager
    public static final String CANNOT_RECOGNIZE_TREE = "Cannot recognize this tree.";
    public static final String GRAPH_ALREADY_EXISTS = "This main.graph already exists in this user.";


    //error messages used by CommunityLibrary
    public static String CANNOT_RECOGNIZE_COMMUNITY = "Cannot recognize this community.";

    //error messages used by DirectedGraph
    public static String VERTEX_NOT_FOUND ="Vertex is absent in the main.graph.";
    public static String VERTEX_LOCKED ="This vertex is locked.";
    public static String VERTEX_ALREADY_COMPLETED ="This vertex is already completed.";
    public static String EDGE_NOT_FOUND ="Vertex is absent in the main.graph, " +
            "OR there is no relation between these two vertices.";
    public static String EDGE_ALREADY_EXIST ="This edge already exists.";
}

