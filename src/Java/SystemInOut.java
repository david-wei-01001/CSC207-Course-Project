//package Java;
//
//import AchievementSystem.AchievementManager;
//import Graph.DirectedGraph;
//import Graph.GraphManager;
//import Graph.Vertex;
//import CommunitySystem.CommunityLibrary;
//import Resource.ResourceManager;
//import RewardSystem.RewardManager;
//import User.UserInfo;
//import User.UserManager;
//import constants.Exceptions;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class SystemInOut {
//    private UserManager userManager;
//    private GraphManager graphManager;
//    private RewardManager rewardManager;
//    private AchievementManager achievementManager;
//    private CommunityLibrary communityLibrary;
//    private ResourceManager resourceManager;
//    private Scanner scanner = new Scanner(System.in);
//
//    public SystemInOut() {
//        userManager = new UserManager();
//    }
//
//    public void run() {
//        logIn();
//
//        scanner.close();
//    }
//
//    public void logIn() {
//        System.out.println("1. Sign-in  2. Register  Or \"exit\" to exit program");
//        if (scanner.nextLine().equals("1")) {
//            signIn();
//            userManager.incrementTotalLogins();
//        }
//        else if (scanner.nextLine().equals("2")) {
//            register();
//            userManager.incrementTotalLogins();
//        }
//        else if (scanner.nextLine().equals("exit")) {
//            exitProgram();
//        } else {
//            System.out.println("Incorrect input, please try again.");
//            logIn();
//        }
//    }
//
//
//    public void signIn() {
//        String username = getUsername();
//        getPassword(username);
//        setCurrentUser(username);
//    }
//
//    /**
//     * fully implement this.
//     * @param username
//     */
//    public void setCurrentUser(String username) {
//        try {
//            userManager.setCurrentUserInfoTo(username);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getPassword(String username) {
//        String passwordEntered = getNonEmptyCredential("password");
//        while (!getCorrectPassword(username).equals(passwordEntered)) {
//            System.out.println("Incorrect password. Please try again.");
//            passwordEntered = getNonEmptyCredential("password");
//        }
//    }
//
//    public String getCorrectPassword(String username) {
//        String password = null;
//        try {
//            password = userManager.getAUserInfo(username).getPassword();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return password;
//    }
//
//    public String getNonEmptyCredential(String credential) {
//        System.out.println(credential + ": ");
//        String nonEmptyCredential = scanner.nextLine();
//        while (nonEmptyCredential.length() == 0) {
//            System.out.println("You did not enter a " + credential + ". Please try again:");
//            nonEmptyCredential = scanner.nextLine();
//        }
//        return nonEmptyCredential;
//    }
//
//    private String getUsername() {
//        String username = getNonEmptyCredential("username");
//        while (!userManager.containsUsername(username)) {
//            System.out.println(Exceptions.INCORRECT_USERNAME + " Please try again.");
//            username = getNonEmptyCredential("username");
//        }
//        return username;
//    }
//
//    private void register() {
////        System.out.println("Welcome to Tech Tree! We are happy to be with you! Please type your username below:");
////        String username = scanner.nextLine();
////        while (username.length() == 0) {
////            System.out.println("You have not put your username, please type your username below:");
////            username = scanner.nextLine();
////        }
////        System.out.println("Please type your email below:");
////        String email = scanner.nextLine();
////        while (!email.contains("@")) {
////            System.out.println("You have not put your email or the email you have put is invalid" +
////                    ", please type your correct email below:");
////            email = scanner.nextLine();
////        }
////
////
////        System.out.println("Please type your password below:");
////        String password = scanner.nextLine();
////        while (password.length() == 0) {
////            System.out.println("You have not put your password, please type your password below:");
////            password = scanner.nextLine();
////
////        }
////        try {
////            facade.getUserManager().addNewUserInfo(username, email, password);
////            facade.setCurrentUserTo(username);
////            return "Congratulations! You have created your account!";
////        } catch (Exception e) {
////            return "The Username has already exists, please try again";
////        }
//    }
//
//
//
//    public void exitProgram() {
//
//    }
////
////    public static UserActionFacade newFacade(){
////        GraphManager graphManager = new GraphManager();
////        Map<String, UserInfo> mapOfUserInfo = new HashMap<>();
////        UserManager userManager = new UserManager(mapOfUserInfo);
////        RewardManager rewardManager = new RewardManager();
////        AchievementManager achievementManager = new AchievementManager();
////        CommunityLibrary communityLibrary = new CommunityLibrary();
////        ResourceManager resourceManager = new ResourceManager();
////
////        return new UserActionFacade(userManager,
////                rewardManager,
////                achievementManager,
////                graphManager,
////                communityLibrary,
////                resourceManager
////        );
////    }
////
////
////    public static String newUser(UserActionFacade facade) {
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Welcome to Tech Tree! We are happy to be with you! Please type your username below:");
////        String username = scanner.nextLine();
////        while (username.length() == 0) {
////            System.out.println("You have not put your username, please type your username below:");
////            username = scanner.nextLine();
////        }
////        System.out.println("Please type your email below:");
////        String email = scanner.nextLine();
////        while (!email.contains("@")) {
////            System.out.println("You have not put your email or the email you have put is invalid" +
////                    ", please type your correct email below:");
////            email = scanner.nextLine();
////        }
////
////
////        System.out.println("Please type your password below:");
////        String password = scanner.nextLine();
////        while (password.length() == 0) {
////            System.out.println("You have not put your password, please type your password below:");
////            password = scanner.nextLine();
////
////        }
////        try {
////            facade.getUserManager().addNewUserInfo(username, email, password);
////            facade.setCurrentUserTo(username);
////            return "Congratulations! You have created your account!";
////        } catch (Exception e) {
////            return "The Username has already exists, please try again";
////        }
////    }
////
////    public static DirectedGraph getGraph(GraphManager manager){
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Please choose the Tree you want to start with:" + manager.getAllGraphs());
////        String number = scanner.nextLine();
////        while (!manager.getAllGraphs().containsKey(number)) {
////            System.out.println("You have not put a number or the number you put is invalid" +
////                    ", please choose the Tree you want to start with:");
////            number = scanner.nextLine();
////        }
////        return manager.getAllGraphs().get(number);
////    }
////
////    public static Vertex gotVertex(DirectedGraph graph){
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Please choose the node you want to start with:" + graph.availableVertex());
////        String number = scanner.nextLine();
////        while (!graph.availableVertex().containsKey(number)) {
////            System.out.println("You have not put a number or the number you put is invalid" +
////                    ", please choose the node you want to start with:");
////            number = scanner.nextLine();
////        }
////        return graph.availableVertex().get(number);
////    }
////
////    public static void completeVertex(Vertex node, DirectedGraph graph, UserActionFacade facade){
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Now please study the node you chose. Once you have completed, type \"Yes\" below:");
////        String yes = scanner.nextLine();
////        while (!yes.equals("Yes")) {
////            System.out.println("It seems that you have not complete your task, please continue working." +
////                    "Once you have completed, type yes below:");
////            yes = scanner.nextLine();
////        }
////        System.out.println("Congratulations! You have completed this task! Next you need to make some post!");
////        System.out.println("Please type in the content you want to post:");
////        String content = scanner.nextLine();
////        facade.getCommunityLibrary().getCommunity(node.getCommunityName()).addPublishedContent(content, facade.getCurrentUser());
////        System.out.println("Congratulations! You have made your first post!");
////        System.out.println("You have completed this node. Now you can proceed to the next node.");
////        graph.complete(node.getName());
////    }
////
////    public static void menu(){
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Please choose what you want to proceed: 1. Check ");
////    }
//
//
//}
