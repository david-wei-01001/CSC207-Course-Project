
//package User;
//
//import constants.Exceptions;
//
//import java.util.Scanner;
//
//public class InterfaceAction {
//
//    public static void loginOrSignup() throws Exception {
//        // if user select login, then the flag = 1, if user select signup, then the flag = 0
//        Scanner scanner = new Scanner(System.in);
//        String flag = scanner.nextLine();
//        if(flag.equals("1")){
//            logIn();
//        }
//        else if(flag.equals("0")){
//            signUp();
//        }
//        else{
//            throw new Exception(Exceptions.ACTION_NOT_FOUND);
//        }
//    }
//
//    public static void logIn(){
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        String password = scanner.nextLine();
//        if(UserManager.checkLoginInfo(username, password).equals(new UserInfo(null,null,null)));
//        //need to change
//
//    }
//    public static void signUp(){
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        String email = scanner.nextLine();
//        String password = scanner.nextLine();
//    }
//
//}

