package phase1;

import java.util.Scanner;

public class Presenter {

    protected static final String ONE = "1";
    protected static final String TWO = "2";
    protected static final String EXIT = "exit";

    protected void LoginOptions() {
        System.out.println("Options: 1.Sign-in, 2.Register, or enter \"exit\" to exit program");
    }

    protected String getCorrectLoginInput(String input) {
        Scanner scanner = new Scanner(System.in);
        while (!(input.equals("1") || input.equals("2") || input.equals("exit"))) {
            System.out.println("Incorrect input, please try again.");
            input = scanner.nextLine();
        }
        return input;
    }
}
