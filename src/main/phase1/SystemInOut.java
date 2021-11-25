package phase1;

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

    public SystemInOut() throws Exception {
        userManager = new UserManager();
        userManager.addNewUserInfo("alfred", "@", "123");
        resourceManager = new ResourceManager(userManager.getCurrentUserInfo());
        resourceManager.addDefault();
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
                    technicalTreePage();
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
        System.out.println("Enter anything to return to Main Menu.");
        String input = scanner.nextLine();
        mainMenu();
    }

    private void resourcePage() {
        System.out.println("Your reward point: " + userManager.getCurrentUserInfo().getRewardPoints());
        System.out.println("Resource: 1.My Resource, 2.Download Resource, 3.Create Resource, " +
                "0. Main Menu");
        String input = scanner.nextLine();
        while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("0"))) {
            System.out.println("Incorrect input, please try again.");
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
        System.out.println("Your resource: " + userManager.getCurrentUserInfo().getListOfResource());
        System.out.println("Enter anything to return to Main Menu.");
        String input = scanner.nextLine();
        mainMenu();
        }

    private void downloadResources() {
        System.out.println("Please choose the resource you want to download:" +
                this.resourceManager.getMapOfResource());
        String content = scanner.nextLine();
        while(resourceManager.downloadResource(content).equals("Sorry, you do not have enough points")){
            System.out.println("Sorry, you do not have enough points");
            System.out.println("Please choose the resource you want to download:" +
                    this.resourceManager.getMapOfResource());
            content = scanner.nextLine();
        };
        while(!resourceManager.downloadResource(content).equals("Sorry, you do not have enough points")){
            System.out.println("You have successfully download this resource");
            resourceManager.downloadResource(content);
            System.out.println("Enter anything to return to Main Menu.");
            String input = scanner.nextLine();
            mainMenu();
        };

    }

    private void createResource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the description of the resource you want to create");
        String description = scanner.nextLine();
        System.out.println("Please write the required points of the resource you want to create");
        String point = scanner.nextLine();
        System.out.println("Please write the content of the resource you want to create");
        String content = scanner.nextLine();
        resourceManager.addResource(content, Integer.parseInt(point), description);
        System.out.println("You have successfully created this resource");
        System.out.println("Enter anything to return to Main Menu.");
        String input = scanner.nextLine();
        mainMenu();
    }


    private void technicalTreePage() {

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
//
//    public static UserActionFacade newFacade(){
//        GraphManager graphManager = new GraphManager();
//        Map<String, UserInfo> mapOfUserInfo = new HashMap<>();
//        UserManager userManager = new UserManager(mapOfUserInfo);
//        RewardManager rewardManager = new RewardManager();
//        AchievementManager achievementManager = new AchievementManager();
//        CommunityLibrary communityLibrary = new CommunityLibrary();
//        ResourceManager resourceManager = new ResourceManager();
//
//        return new UserActionFacade(userManager,
//                rewardManager,
//                achievementManager,
//                graphManager,
//                communityLibrary,
//                resourceManager
//        );
//    }
//
//
//    public static String newUser(UserActionFacade facade) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to Tech Tree! We are happy to be with you! Please type your username below:");
//        String username = scanner.nextLine();
//        while (username.length() == 0) {
//            System.out.println("You have not put your username, please type your username below:");
//            username = scanner.nextLine();
//        }
//        System.out.println("Please type your email below:");
//        String email = scanner.nextLine();
//        while (!email.contains("@")) {
//            System.out.println("You have not put your email or the email you have put is invalid" +
//                    ", please type your correct email below:");
//            email = scanner.nextLine();
//        }
//
//
//        System.out.println("Please type your password below:");
//        String password = scanner.nextLine();
//        while (password.length() == 0) {
//            System.out.println("You have not put your password, please type your password below:");
//            password = scanner.nextLine();
//
//        }
//        try {
//            facade.getUserManager().addNewUserInfo(username, email, password);
//            facade.setCurrentUserTo(username);
//            return "Congratulations! You have created your account!";
//        } catch (Exception e) {
//            return "The Username has already exists, please try again";
//        }
//    }
//
//    public static DirectedGraph getGraph(GraphManager manager){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please choose the Tree you want to start with:" + manager.getAllGraphs());
//        String number = scanner.nextLine();
//        while (!manager.getAllGraphs().containsKey(number)) {
//            System.out.println("You have not put a number or the number you put is invalid" +
//                    ", please choose the Tree you want to start with:");
//            number = scanner.nextLine();
//        }
//        return manager.getAllGraphs().get(number);
//    }
//
//    public static Vertex gotVertex(DirectedGraph main.graph){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please choose the node you want to start with:" + main.graph.availableVertex());
//        String number = scanner.nextLine();
//        while (!main.graph.availableVertex().containsKey(number)) {
//            System.out.println("You have not put a number or the number you put is invalid" +
//                    ", please choose the node you want to start with:");
//            number = scanner.nextLine();
//        }
//        return main.graph.availableVertex().get(number);
//    }
//
//    public static void completeVertex(Vertex node, DirectedGraph main.graph, UserActionFacade facade){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Now please study the node you chose. Once you have completed, type \"Yes\" below:");
//        String yes = scanner.nextLine();
//        while (!yes.equals("Yes")) {
//            System.out.println("It seems that you have not complete your task, please continue working." +
//                    "Once you have completed, type yes below:");
//            yes = scanner.nextLine();
//        }
//        System.out.println("Congratulations! You have completed this task! Next you need to make some post!");
//        System.out.println("Please type in the content you want to post:");
//        String content = scanner.nextLine();
//        facade.getCommunityLibrary().getCommunity(node.getCommunityName()).addPublishedContent(content, facade.getCurrentUser());
//        System.out.println("Congratulations! You have made your first post!");
//        System.out.println("You have completed this node. Now you can proceed to the next node.");
//        main.graph.complete(node.getName());
//    }
//
//    public static void menu(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please choose what you want to proceed: 1. Check ");
//    }


}
