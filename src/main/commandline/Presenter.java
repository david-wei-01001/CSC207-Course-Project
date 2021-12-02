package commandline;

import resource.ResourceManager;
import user.UserManager;
import java.util.Scanner;

public class Presenter {
    private UserManager userManager;
    private ResourceManager resourceManager;

    protected static final String ONE = "1";
    protected static final String TWO = "2";
    protected static final String EXIT = "exit";
    protected static final String RETURN = "return";
    protected static final String PASSWORD = "password";
    protected static final String USERNAME = "username";
    protected static final String EMAIL = "email";
    protected static final String AT = "@";
    protected static final String LOGIN_OPTIONS = "Options: " + ONE + ".Sign-in, "+ TWO + ".Register, or enter \"" + EXIT + "\" to exit program";
    protected static final String INCORRECT_INPUT = "Incorrect input";
    protected static final String YOU_DID_NOT_ENTER_A = "You did not enter a";
    protected static final String PLEASE_TRY_AGAIN = "please try again";
    protected static final String INCORRECT = "Incorrect";
    protected static final String OR = "or";
    protected static final String ENTER_RETURN = "enter \"" + RETURN + "\" to return to \"Options.\"";

    public Presenter(UserManager userManager, ResourceManager resourceManager) {
        this.userManager = userManager;
        this.resourceManager = resourceManager;
    }
    public String mainMenuReturn(){
        return "Enter anything to return to Main Menu.";
    }

    public String rewardPoints(){
        return "Your reward point: " + userManager.getCurrentUserInfo().getRewardPoints();
    }

    public String resourcePage(){
        return "Resource: 1.My Resource, 2.Download Resource, 3.Create Resource, " +
                "0. Main Menu";
    }

    public String incorrectInput(){
        return "Incorrect input, please try again.";
    }

    public String currentResource(){
        return "Your resource: " + userManager.getCurrentUserInfo().getMapOfResource();
    }

    public String resourceChoose(){
        return "Please choose the resource you want to download:" +
                this.resourceManager.getMapOfResource();
    }

    public String insufficientPoints(){
        return "Sorry, you do not have enough points";
    }

    public String downloadSuccessfully(){
        return "You have successfully download this resource";
    }

    public String resourceDescription(){
        return "Please write the description of the resource you want to create";
    }

    public String resourcePoints(){
        return "Please write the required points of the resource you want to create";
    }

    public String resourceContents(){
        return "Please write the content of the resource you want to create";
    }

    public String resourceCreateSuccessfully(){
        return "You have successfully created this resource";
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
