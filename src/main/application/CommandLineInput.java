package application;

import interfaceadapter.UIAdapter;

import java.util.Scanner;

/**
 * The Command Line UI
 */
public class CommandLineInput implements UIAdapter {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Get the next input from the user
     *
     * @return the input of the user
     */
    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * quit the program
     */
    @Override
    public void exit() {
        System.exit(0);
    }
}
