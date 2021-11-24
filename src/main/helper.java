//
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
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class helper {
//    private UserManager userManager = new UserManager();
//    private GraphManager graphManager = new GraphManager();
//    private RewardManager rewardManager = new RewardManager();
//    private AchievementManager achievementManager = new AchievementManager();
//    private CommunityLibrary communityLibrary = new CommunityLibrary();
//    private ResourceManager resourceManager = new ResourceManager();
//
//    public static String newUser() {
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
//            userManager.addNewUser(username, email, password);
//            setCurrentUserTo(username);
//            userManager.incrementTotalLogins();
//            return "Congratulations! You have created your account!";
//        } catch (Exception e) {
//            return "The Username has already exists, please try again";
//        }
//    }
//
//    private static void setCurrentUserTo(String username) {
//    }
//
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
//    public static Vertex gotVertex(DirectedGraph main.graph) throws Exception {
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
//    public static void completeVertex(Vertex node, DirectedGraph main.graph, UserActionFacade facade) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Now please study the node you chose. Once you have completed, type \"Yes\" below:");
//        String yes = scanner.nextLine();
//        while (!yes.equals("Yes")) {
//            System.out.println("It seems that you have not complete your task, please continue working." +
//                    "Once you have completed, type yes below:");
//            yes = scanner.nextLine();
//        }
//
//        System.out.println("Congratulations! You have completed this task! Next you need to make some post!");
//        facade.getCurrentUser().getUserInfo().setRewardPoints(facade.getCurrentUser().getUserInfo().getRewardPoints()+5);
//        System.out.println("Please type in the content you want to post:");
//        String content = scanner.nextLine();
//
//        facade.createPost(node.getCommunityName(), content);
//        System.out.println("Congratulations! You have made your first post!");
//        facade.getCurrentUser().getUserInfo().setRewardPoints(facade.getCurrentUser().getUserInfo().getRewardPoints()+5);
//        System.out.println("You have completed this node. Now you can proceed to the next task.");
//        main.graph.complete(node.getName());
//    }
//
//    public static void main.resource(UserActionFacade main.user){
//        System.out.println("Currently you have " + main.user.getCurrentUser().getUserInfo().getRewardPoints() +
//                " reward points.");
//    }
//
//    public static void downloadResource(UserActionFacade main.user){
//        System.out.println("Now you can download some resources.");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please choose the main.resource you want to download:" +
//                main.user.getResourceManager().getMapOfResource());
//        String content = scanner.nextLine();
//        System.out.println(main.user.downloadResource(content));
//    }
//
//    public static void achievementCheck(UserActionFacade main.user){
//        System.out.println("Next, let us check how many achievements you have currently");
//        System.out.println(main.user.getCurrentUser().getUserInfo().getMapOfAchievement());
//
//    }
//
//
//
//}
