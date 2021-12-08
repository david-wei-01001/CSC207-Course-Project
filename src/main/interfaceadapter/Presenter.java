package interfaceadapter;

import communitysystem.CommunityLibrary;
import constants.Exceptions;
import graph.DirectedGraph;
import graph.GraphManager;
import resource.ResourceManager;
import user.UserManager;

import java.util.HashMap;

/**
 * Presenter class which is bridging Use Cases and the UI and contains information the UI outputs to user.
 */
public class Presenter {
    private final UserManager userManager;
    private final ResourceManager resourceManager;
    private final GraphManager graphManager;
    private final CommunityLibrary communityLibrary;

    protected static final String ZERO = "0";
    protected static final String ONE = "1";
    protected static final String TWO = "2";
    protected static final String THREE = "3";
    protected static final String YES = "yes";
    protected static final String EXIT = "exit";
    protected static final String MAIN = "main";
    protected static final String RETURN = "return";
    protected static final String PASSWORD = "password";
    protected static final String USERNAME = "username";
    protected static final String EMAIL = "email";
    protected static final String LOGIN_OPTIONS = "Options: " + ONE + ".Sign-in, " + TWO + ".Register, or enter \""
            + EXIT + "\" to exit program";
    protected static final String INSUFFICIENT_POINTS = "Sorry, you do not have enough points";
    protected static final String INCORRECT_INPUT = "Incorrect input";
    protected static final String YOU_DID_NOT_ENTER_A = "You did not enter a";
    protected static final String PLEASE_TRY_AGAIN = "please try again";
    protected static final String INCORRECT = "Incorrect";
    protected static final String OR = "or";
    protected static final String ENTER_RETURN = "enter \"" + RETURN + "\" to return to \"Options.\"";

    /**
     * The constructor of a Presenter.
     * When a Presenter is instantiated, this is called to set up its contents.
     *
     * @param userManager      The Use Case userManager which controls all actions of a user.
     * @param resourceManager  The Use Case resourceManager which controls
     *                         all actions a user may perform on resource system.
     * @param graphManager     The Use Case graphManager which actions that can perform on DirectedGraphs.
     * @param communityLibrary The Use Case communityLibrary contains all the communities.
     */
    public Presenter(UserManager userManager, ResourceManager resourceManager, GraphManager graphManager,
                     CommunityLibrary communityLibrary) {
        this.userManager = userManager;
        this.resourceManager = resourceManager;
        this.graphManager = graphManager;
        this.communityLibrary = communityLibrary;

    }

    /**
     * Information to display about how to return to Main Menu.
     */
    public void mainMenuReturn() {
        System.out.println("Enter anything to return to Main Menu.");
    }

    /**
     * Information to display all Main Menu options.
     */
    public void mainMenuOptions() {
        System.out.println("Main Menu: 0. My tree 1.Technical Tree, 2.Resource, 3.Achievement, " +
                "or enter \"exit\" to exit program");
    }

    /**
     * Information to display a user's reward points.
     */
    public void rewardPoints() {
        System.out.println("Your reward point: " + userManager.getCurrentUser().getRewardPoints());
    }

    /**
     * Information to display all resource page options.
     */
    public void resourcePage() {
        System.out.println("Resource: 1.My Resource, 2.Download Resource, 3.Create Resource, " +
                "0. Main Menu");
    }

    /**
     * Information to display if the input is incorrect.
     */
    public void incorrectInput() {
        System.out.println("Incorrect input, please try again.");
    }

    /**
     * Information to display a user's current resources.
     */
    public void currentResource() {
        System.out.println("Your resource: " + userManager.getCurrentUser().getMapOfResource());
    }

    /**
     * Information to display when prompting a user to choose a resource to download.
     */
    public void resourceChoose() {
        System.out.println("Please choose the resource you want to download:" +
                this.resourceManager.getMapOfResource());
    }

    /**
     * Information to display when a user does not have enough reward points.
     */
    public void insufficientPoints() {
        System.out.println("Sorry, you do not have enough points.");
    }

    /**
     * Information to display when a user download a resource successfully.
     */
    public void downloadSuccessfully() {
        System.out.println("You have successfully download this resource.");
    }

    /**
     * Information to display when prompting a user to write descriptions of a resource he wants to create.
     */
    public void resourceDescription() {
        System.out.println("Please write the description of the resource you want to create.");
    }

    /**
     * Information to display when prompting a user to state
     * the amount of points required for a resource he wants to create.
     */
    public void resourcePoints() {
        System.out.println("Please write the required points of the resource you want to create.");
    }

    /**
     * Information to display when prompting a user to write contents of a resource he wants to create.
     */
    public void resourceContents() {
        System.out.println("Please write the content of the resource you want to create.");
    }

    /**
     * Information to display when a user creates a resource successfully.
     */
    public void resourceCreateSuccessfully() {
        System.out.println("You have successfully created this resource.");
    }

    /**
     * Information to display when prompted for displaying the achievement page.
     */
    public void achievementPage() {
        System.out.println(userManager.displayAchievement());
    }

    /**
     * Information to display when a prompted for displaying the technical tree main page.
     */
    public void myTreePage() {
        System.out.println("Hi! Now you've entered your tree");
        System.out.println("Select the tree you want to study!");

        HashMap<String, DirectedGraph> myTree1 = (HashMap<String, DirectedGraph>)
                userManager.getCurrentUser().getMapOfGraph();
        HashMap<String, DirectedGraph> myTree2 = new HashMap<>();
        HashMap<String, DirectedGraph> allTree = (HashMap<String, DirectedGraph>) graphManager.getAllGraphs();
        for (String id : allTree.keySet()) {
            DirectedGraph graph = allTree.get(id);
            if (myTree1.containsKey(graph.getName())) {
                myTree2.put(id, graph);
            }
        }
        System.out.println("Tech Trees: " + "\n" + getAllGraphName(myTree2));
        System.out.println("Enter \"main\" to return to main page.");

    }

    /**
     * Information to display when a user haven't begun learning any field of knowledge.
     */
    public void myTreePageEmpty() {
        System.out.println("Oops, your have not begin the study for any graph.");
        System.out.println("To begin your learning experience, back to main menu and select Tech Tree!");
    }

    /**
     * Display the names of all DirectedGraphs.
     *
     * @param mapOfGraphs a collection of DirectedGraphs
     * @return the names of all DirectedGraphs in mapOfGraphs
     */
    public String getAllGraphName(HashMap<String, DirectedGraph> mapOfGraphs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String i : mapOfGraphs.keySet()) {
            stringBuilder.append(i).append(": ");
            stringBuilder.append(mapOfGraphs.get(i).getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Information to display in the technical tree page.
     */
    public void technicalTreeMainPage() {
        System.out.println("Hi! Now you've entered the technical tree page.");
        System.out.println("Select the tree you want to study!");
        System.out.println("Tech Trees: " + "\n" + getAllGraphName((HashMap<String, DirectedGraph>)
                graphManager.getAllGraphs()));
        System.out.println("Enter \"main\" to return to main page.");
    }

    /**
     * Information to display when prompted for displaying the current graph.
     */
    public void technicalTreeDisplayCurrentGraph() {
        System.out.println(graphManager.displayCurrentGraph());
    }

    /**
     * Information to display when the user are required to choose a tree or return to the home page.
     */
    public void technicalTreePage() throws Exception {
        System.out.println("Please choose the node you want to study with: " +
                graphManager.getCurrentGraph().availableVertex() + ", or enter \"main\" to return to home page.");
    }

    /**
     * Information to display when studying the node.
     */
    public void studyVertex() {
        System.out.println("Now study the node you have chosen, once you're finished, type \"yes\" below:");
    }

    /**
     * Information to display when entered other than yes.
     */
    public void studyVertexNotFinished() {
        System.out.println("It seems like you have not finished your study yet, keep working on it!" +
                "Once you finished, type \"yes\" below:");
    }

    /**
     * Information to display when finish studying a node.
     */
    public void studyVertexFinished() {
        System.out.println("Congratulations! You've made one giant step toward success! Now let's make some posts " +
                "on what you've just learned.");
    }

    /**
     * Display the current community the vertex refers to.
     *
     * @param vertexName The name of the vertex.
     */
    public void displayVertexCommunity(String vertexName) {
        System.out.println(this.communityLibrary.getMapOfCommunity().get(vertexName).toString());
    }


    /**
     * Information to display when finishing a node and prompted for a comment.
     */
    public void enterPublishContent() {
        System.out.println("Please enter the content you want to publish below: ");
    }

    /**
     * Information to display when post is published successfully.
     */
    public void publishPostSuccessful() {
        System.out.println("Congratulations! You've successfully published a post. :)");
    }

    /**
     * Information to display when the node is finished.
     */
    public void nodeCompleted() {
        System.out.println("You have completed this node, you can now proceed to the next " +
                "node you want to study. Press any key to continue.");
    }

    /**
     * Requiring for the login options
     */
    protected void LoginOptions() {
        System.out.println(LOGIN_OPTIONS);
    }

    /**
     * Prompting the user to enter the correct login option
     */
    protected void getCorrectLoginOption() {
        System.out.println(INCORRECT_INPUT + ", " + PLEASE_TRY_AGAIN + ".");
    }

    /**
     * Check if the given input is incorrect
     *
     * @param input the input from the user
     * @return true if the input is incorrect, false otherwise
     */
    protected boolean incorrectLoginOption(String input) {
        return !(input.equals(ONE) || input.equals(TWO) || input.equals(EXIT));
    }

    /**
     * @return whether the user entered a credential or not.
     */
    protected boolean isEmptyCredential(String input) {
        return input.length() == 0;
    }


    /**
     * Information to display when the user does not enter a password.
     */
    protected void emptyPassword() {
        System.out.println(YOU_DID_NOT_ENTER_A + " " + PASSWORD + ", " + PLEASE_TRY_AGAIN + ".");
    }

    /**
     * Information to display when the user does not enter a username.
     */
    protected void emptyUsername() {
        System.out.println(YOU_DID_NOT_ENTER_A + " " + USERNAME + ", " + PLEASE_TRY_AGAIN + ".");
    }

    /**
     * Prompting the user to enter the correct password
     */
    protected void incorrectPassword() {
        pleaseTryAgain(PASSWORD);
    }

    /**
     * Prompting the user to enter the correct username
     */
    protected void incorrectUsername() {
        pleaseTryAgain(USERNAME);
    }

    /**
     * Prompting the user to enter the correct email
     */
    protected void incorrectEmail() {
        pleaseTryAgain(EMAIL);
    }

    /**
     * Prompting the user to enter another username since the current one is already in use
     */
    protected void usernameTaken() {
        pleaseTryAgain(Exceptions.USER_NAME_TAKEN);
    }

    /**
     * Prompting the user to try again since the credentials entered is incorrect
     */
    private void pleaseTryAgain(String credential) {
        System.out.println(INCORRECT + " " + credential + ", " + PLEASE_TRY_AGAIN + ", " + OR + " " + ENTER_RETURN);
    }

    /**
     * Prompting the user to enter the email
     */
    protected void getEmail() {
        System.out.println(EMAIL + ": ");
    }

    /**
     * Prompting the user to enter the password
     */
    protected void getPassword() {
        System.out.println(PASSWORD + ": ");
    }

    /**
     * Prompting the user to enter the username
     */
    protected void getUsername() {
        System.out.println(USERNAME + ": ");
    }


}
