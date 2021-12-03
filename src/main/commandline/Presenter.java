package commandline;

import constants.Exceptions;
import graph.GraphManager;
import resource.ResourceManager;
import user.UserManager;
import java.util.Scanner;

public class Presenter {
    private UserManager userManager;
    private ResourceManager resourceManager;
    private GraphManager graphManager;


    protected static final String ONE = "1";
    protected static final String TWO = "2";
    protected static final String EXIT = "exit";
    protected static final String RETURN = "return";
    protected static final String PASSWORD = "password";
    protected static final String USERNAME = "username";
    protected static final String EMAIL = "email";
    protected static final String AT = "@";
    protected static final String LOGIN_OPTIONS = "Options: " + ONE + ".Sign-in, "+ TWO + ".Register, or enter \""
            + EXIT + "\" to exit program";
    protected static final String INCORRECT_INPUT = "Incorrect input";
    protected static final String YOU_DID_NOT_ENTER_A = "You did not enter a";
    protected static final String PLEASE_TRY_AGAIN = "please try again";
    protected static final String INCORRECT = "Incorrect";
    protected static final String OR = "or";
    protected static final String ENTER_RETURN = "enter \"" + RETURN + "\" to return to \"Options.\"";

    public Presenter(UserManager userManager, ResourceManager resourceManager, GraphManager graphManager) {
        this.userManager = userManager;
        this.resourceManager = resourceManager;
        this.graphManager = graphManager;

    }
    public void mainMenuReturn(){
        System.out.println("Enter anything to return to Main Menu.");
    }

    public void mainMenuOptions(){
        System.out.println("Main Menu: 1.Technical Tree, 2.Resource, 3.Achievement, or enter \"exit\" to exit program");
    }

    public void rewardPoints(){
        System.out.println("Your reward point: " + userManager.getCurrentUserInfo().getRewardPoints());
    }

    public void resourcePage(){
        System.out.println("Resource: 1.My Resource, 2.Download Resource, 3.Create Resource, " +
                "0. Main Menu");
    }

    public void incorrectInput(){
        System.out.println( "Incorrect input, please try again.");
    }

    public void currentResource(){
        System.out.println( "Your resource: " + userManager.getCurrentUserInfo().getMapOfResource());
    }

    public void resourceChoose(){
        System.out.println( "Please choose the resource you want to download:" +
                this.resourceManager.getMapOfResource());
    }

    public void insufficientPoints(){
        System.out.println( "Sorry, you do not have enough points");
    }

    public void downloadSuccessfully(){
        System.out.println( "You have successfully download this resource");
    }

    public void resourceDescription(){
        System.out.println("Please write the description of the resource you want to create");
    }

    public void resourcePoints(){
        System.out.println("Please write the required points of the resource you want to create");
    }

    public void resourceContents(){
        System.out.println("Please write the content of the resource you want to create");
    }

    public void resourceCreateSuccessfully(){
        System.out.println( "You have successfully created this resource");
    }


    public void achievementPage(){
        System.out.println(userManager.displayAchievement());
    }


    public void technicalTreeMainPage(){
        System.out.println("Hi! Now you've entered the technical tree page");
        System.out.println("Select the tree you want to study!");
        System.out.println("Tech Trees: " + graphManager.getAllGraphs());
        System.out.println("Enter \"main\" to return to main page.");
    }

    public void technicalTreeDisplayCurrentGraph(){
        System.out.println(graphManager.displayCurrentGraph());
    }

    public void technicalTreePage() throws Exception {
        System.out.println("Please choose the node you want to start with" +
                graphManager.getCurrentGraph().availableVertex() + "or enter \"main\" to return to home page");
    }

    public void studyVertex(){
        System.out.println("Now study the node you have chosen, once you're finished, type \"Yes\" below");
    }

    public void studyVertexNotFinished(){
        System.out.println("It seems like you have not finished your study yet, keep working on it!" +
                "Once you finished, type \"Yes\" below");
    }

    public void studyVertexFinished(){
        System.out.println("Congratulations! You've made one giant step toward success! Now let's make some posts " +
                "on what you've just learned.");
    }

    public void enterPublishContent(){
        System.out.println("Please enter the content you want to publish below: ");
    }

    public void publishPostSuccessful(){
        System.out.println("Congratulations! You've successfully published a post :)");
    }

    public void nodeCompleted(){
        System.out.println("You have completed this node, you can now proceed to the next " +
                "node you want to study. Press any key to continue");
    }

    protected String LoginOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(LOGIN_OPTIONS);
        return scanner.nextLine();
    }


    protected String getCorrectLoginOption(String input) {
        Scanner scanner = new Scanner(System.in);
        while (!(input.equals(ONE) || input.equals(TWO) || input.equals(EXIT))) {
            System.out.println(INCORRECT_INPUT + ", " + PLEASE_TRY_AGAIN + ".");
            input = scanner.nextLine();
        }
        return input;
    }

    protected String getNonEmptyCredential(String credential) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(credential + ": ");
        String nonEmptyCredential = scanner.nextLine();
        while (nonEmptyCredential.length() == 0) {
            System.out.println(YOU_DID_NOT_ENTER_A + " " + credential + ", " + PLEASE_TRY_AGAIN + ".");
            nonEmptyCredential = scanner.nextLine();
        }
        return nonEmptyCredential;
    }

    protected String getNonEmptyPassword() {
        return getNonEmptyCredential(PASSWORD);
    }

    protected String getNonEmptyUsername() {
        return getNonEmptyCredential(USERNAME);
    }

    protected void incorrectPassword() {
        pleaseTryAgain(PASSWORD);
    }

    protected void incorrectUsername() {
        pleaseTryAgain(USERNAME);
    }

    protected void incorrectEmail() {
        pleaseTryAgain(EMAIL);
    }

    protected void usernameTaken() {
        pleaseTryAgain(Exceptions.USER_NAME_TAKEN);
    }

    private void pleaseTryAgain(String credential) {
        System.out.println(INCORRECT + " " + credential + ", " + PLEASE_TRY_AGAIN + ", " + OR + " " + ENTER_RETURN);
    }

    protected void getEmail() {
        System.out.println(EMAIL + ": ");
    }



}
