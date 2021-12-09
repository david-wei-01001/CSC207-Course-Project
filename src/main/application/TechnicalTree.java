package application;

import interfaceadapter.SystemInOut;
import interfaceadapter.UIAdapter;

/**
 * The main class of our application. This is where to start the program.
 */
public class TechnicalTree {
    /**
     * The main method of our program. Call this method to begin.
     */
    public static void main(String[] args) {
        try {
            UIAdapter uiAdapter = new CommandLineInput();
            SystemInOut inOut = new SystemInOut(uiAdapter);
            inOut.run();
        } catch (Exception ignored) {
        }
    }

}
