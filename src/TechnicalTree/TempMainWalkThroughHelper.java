//package Java;
//
//import Graph.DirectedGraph;
//import Graph.GraphManager;
//import Graph.Vertex;
//import CommunitySystem.CommunityLibrary;
//import User.User;
//import Posts.Post;
//
//import java.util.Scanner;
//
//public class TempMainWalkThroughHelper {
//    public static User newUser() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to Tech Tree! We are happy to be with you! Please type your username below:");
//        String username = scanner.nextLine();
//        while (username.length() == 0) {
//            System.out.println("You have not put your username, please type your username below:");
//            username = scanner.nextLine();
//        }
//        System.out.println("Please type your email below:");
//        String email = scanner.nextLine();
//        while (email.length() == 0 || !email.contains("@")) {
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
//        System.out.println("Congratulations! You have created your account!");
//        return new User(username, email, password);
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
//    public static Vertex gotVertex(DirectedGraph graph){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please choose the node you want to start with:" + graph.availableVertex());
//        String number = scanner.nextLine();
//        while (!graph.availableVertex().containsKey(number)) {
//            System.out.println("You have not put a number or the number you put is invalid" +
//                    ", please choose the node you want to start with:");
//            number = scanner.nextLine();
//        }
//        return graph.availableVertex().get(number);
//    }
//
//    public static void completeVertex(Vertex node, DirectedGraph graph){
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
//        System.out.println("Please type in the content you want to post:");
//        String content = scanner.nextLine();
//        String postId = CommunityLibrary.getCommunity(node.getCommunityName()).getNextId();
//        Post newPost = new Post(content, postId);
//
//        //commenting the following line to resolve an error. the error arises
//        //from the change in parameter in Post's constructor.
//
////        CommunityLibrary.getCommunity(node.getCommunityName()).add(newPost);
//        System.out.println("Congratulations! You have made your first post!");
//        System.out.println("You have completed this node. Now you can proceed to the next node.");
//        graph.complete(node.getName());
//    }
//}
