package commandline;

import achievementsystem.AchievementManager;
import graph.GraphManager;
import communitysystem.CommunityLibrary;
import resource.ResourceManager;
import rewardsystem.RewardManager;
import user.UserManager;
import constants.Exceptions;

import java.util.Scanner;

public class SystemInOut {
    private UserManager userManager;
    private GraphManager graphManager;
    private RewardManager rewardManager;
    private AchievementManager achievementManager;
    private CommunityLibrary communityLibrary;
    private ResourceManager resourceManager;
    private Scanner scanner = new Scanner(System.in);
    private Presenter presenter = new Presenter(userManager, resourceManager);

    public SystemInOut() throws Exception {
        graphManager = new GraphManager();
        userManager = new UserManager();
        userManager.addNewUserInfo("alfred", "@", "123");
        resourceManager = new ResourceManager();
        resourceManager.addDefault();
        presenter = new Presenter(userManager, resourceManager);
    }

    public void run() {
        logIn();
        mainMenu();

        scanner.close();
    }

    public void mainMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("Main Menu: 1.Technical Tree, 2.Resource, 3.Achievement, or enter \"exit\" to exit program");
            String input = scanner.nextLine();

            while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("exit"))) {
                System.out.println("Incorrect input, please try again.");
                input = scanner.nextLine();
            }

            switch (input) {
                case "1":
                    try {
                        technicalTreeMainPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    resourcePage();
                    break;
                case "3":
                    achievementPage();
                    break;
                case "exit":
                    keepRunning = false;
                    break;
            }
        }
    }

    private void achievementPage() {
        System.out.println(userManager.displayAchievement());
        System.out.println(presenter.mainMenuReturn());
        String input = scanner.nextLine();
        mainMenu();
    }

    private void resourcePage() {
        System.out.println(presenter.rewardPoints());
        System.out.println(presenter.resourcePage());
        String input = scanner.nextLine();
        while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("0"))) {
            System.out.println(presenter.incorrectInput());
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                myResource();
                break;
            case "2":
                downloadResources();
                break;
            case "3":
                createResource();
                break;
            case "0":
                mainMenu();
        }
    }



    private void myResource() {
        System.out.println(presenter.currentResource());
        System.out.println(presenter.mainMenuReturn());
        String input = scanner.nextLine();
        mainMenu();
        }

    private void downloadResources() {
        System.out.println(presenter.resourceChoose());
        String content = scanner.nextLine();
        while(resourceManager.downloadResource(content).equals(presenter.insufficientPoints())){
            System.out.println(presenter.insufficientPoints());
            System.out.println(presenter.resourceChoose());
            content = scanner.nextLine();
        }
        while(!resourceManager.downloadResource(content).equals(presenter.insufficientPoints())){
            System.out.println(presenter.downloadSuccessfully());
            resourceManager.downloadResource(content);
            System.out.println(presenter.mainMenuReturn());
            String input = scanner.nextLine();
            mainMenu();
        }
    }

    private void createResource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(presenter.resourceDescription());
        String description = scanner.nextLine();
        System.out.println(presenter.resourcePoints());
        String point = scanner.nextLine();
        System.out.println(presenter.resourceContents());
        String content = scanner.nextLine();
        resourceManager.addResource(content, Integer.parseInt(point), description);
        System.out.println(presenter.resourceCreateSuccessfully());
        System.out.println(presenter.mainMenuReturn());
        String input = scanner.nextLine();
        mainMenu();
    }


    private void technicalTreeMainPage() throws Exception {
        System.out.println("Hi! Now you've entered the technical tree page");
        System.out.println("Select the tree you want to study!");
        System.out.println("Tech Trees: " + graphManager.getAllGraphs());
        System.out.println("Enter \"main\" to return to main page.");
        String input = scanner.nextLine();

        while (!graphManager.getAllGraphs().containsKey(input) && !input.equals("main")) {
            System.out.println("You have input an invalid number, try again :(");
            input = scanner.nextLine();
        }

        if (input.equals("main")){
            mainMenu();
        }

        else {
            technicalTreePage(input);
        }

    }

    private void technicalTreePage(String treeId) throws Exception {
        graphManager.setCurrentGraph(treeId);
        System.out.println(graphManager.displayCurrentGraph());

        System.out.println("Please choose the node you want to start with" + graphManager.getCurrentGraph().availableVertex());
        String input = scanner.nextLine();
        while (!graphManager.getCurrentGraph().availableVertex().containsKey(input)){
            System.out.println("You have input an invalid number. Please try again :(");
            input = scanner.nextLine();
        }
        String vertexName = graphManager.getCurrentGraph().availableVertex().get(input).toString();

        studyVertex(vertexName);

    }

    private void studyVertex(String vertexName) throws Exception {

        System.out.println("Now study the node you have chosen, once you're finished, type \"Yes\" below");
        String input = scanner.nextLine();
        while (!input.equals("Yes")){
            System.out.println("It seems like you have not finished your study yet, keep working on it!" +
                    "Once you finished, type \"Yes\" below");
            input = scanner.nextLine();
        }

        System.out.println("Congratulations! You've made one giant step toward success! Now let's make some posts" +
                "on what you've just learned.");
        userManager.getCurrentUserInfo().addRewardPoints(5); // TODO: Add this to studyVertex
        System.out.println("Please enter the content you want to publish below: ");
        String publishedContent = scanner.nextLine();

        communityLibrary.setCurrentCommunity(vertexName);
        communityLibrary.createPost(publishedContent, achievementManager, rewardManager);
        System.out.println("Congratulations! You've successfully published a post :)");
        userManager.getCurrentUserInfo().addRewardPoints(5); // TODO: Add this line to createPost
        System.out.println("You have completed this node, you can now proceed to the next " +
                "node or return to the main menu.");


    }


    public void logIn() {
        System.out.println("Options: 1.Sign-in, 2.Register, or enter \"exit\" to exit program");
        String input = scanner.nextLine();

        while (!(input.equals("1") || input.equals("2") || input.equals("exit"))) {
            System.out.println("Incorrect input, please try again.");
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                if (!signIn()) {
                    logIn();
                } else {
                    userManager.incrementTotalLogins();
                }
                break;
            case "2":
                if (!register()) {
                    logIn();
                } else {
                    userManager.incrementTotalLogins();
                }
                break;
            case "exit":
                exitProgram();
                break;
        }
    }

    public boolean signIn() {
        String username = getUsernameSignIn();
        if (username.equals("return")) {
            return false;
        }
        if (!enterPassword(username)) {
            return false;
        }
        setCurrentUser(username);
        return true;
    }

    /**
     * fully implement this.
     * @param username
     */
    public void setCurrentUser(String username) {
        try {
            userManager.setCurrentUserInfoTo(username);
            resourceManager.setCurrentUserInfo(userManager.getCurrentUserInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean enterPassword(String username) {
        String password = getNonEmptyCredential("password");
        while (!getCorrectPassword(username).equals(password)) {
            System.out.println("Incorrect password, please try again, or enter \"return\" to return to Options.\"");
            password = getNonEmptyCredential("password");
            if (password.equals("return")) {
                return false;
            }
        }
        return true;
    }

    public String getCorrectPassword(String username) {
        String password = null;
        try {
            password = userManager.getAUserInfo(username).getPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public String getNonEmptyCredential(String credential) {
        System.out.println(credential + ": ");
        String nonEmptyCredential = scanner.nextLine();
        while (nonEmptyCredential.length() == 0) {
            System.out.println("You did not enter a " + credential + ", please try again:");
            nonEmptyCredential = scanner.nextLine();
        }
        return nonEmptyCredential;
    }

    private String getUsernameSignIn() {
        String username = getNonEmptyCredential("username");
        while (!userManager.containsUsername(username)) {
            System.out.println(Exceptions.INCORRECT_USERNAME + ", please try again, or enter \"return\" to return to Options.");
            username = getNonEmptyCredential("username");
            if (username.equals("return")) {
                return username;
            }
        }
        return username;
    }

    public String getUsernameRegister() {
        String username = getNonEmptyCredential("username");
        while (userManager.containsUsername(username)) {
            System.out.println(Exceptions.USER_NAME_TAKEN + ", please try again, or enter \"return\" to return to Options.");
            username = getNonEmptyCredential("username");
            if (username.equals("return")) {
                return username;
            }
        }
        return username;
    }

    public String getEmailRegister() {
        System.out.println("email: ");
        String email = scanner.nextLine();
        while (!email.contains("@")) {
            System.out.println("Invalid email, or enter \"return\" to return to Options.");
            email = scanner.nextLine();
            if (email.equals("return")) {
                return email;
            }
        }
        return email;
    }

    public String getPasswordRegister() {
        return getNonEmptyCredential("password");
    }


    private boolean register() {
        String username = getUsernameRegister();
        if (username.equals("return")) {
            return false;
        }
        String email = getEmailRegister();
        if (email.equals("return")) {
            return false;
        }
        String password = getPasswordRegister();
        try {
            userManager.addNewUserInfo(username, email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCurrentUser(username);
        return true;
    }




    public void exitProgram() {
        save();
        System.exit(0);
    }

    private void save() {
        // uses an interface to save.
    }

    private void load() {

    }
}
