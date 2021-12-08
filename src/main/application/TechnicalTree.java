package application;

import interfaceadapter.SystemInOut;

/**
 * The main class of our application. This is where to start the program.
 */
public class TechnicalTree {
    /**
     * The main method of our program. Call this method to begin.
     */
    public static void main(String[] args) {
        try {
            SystemInOut inOut = new SystemInOut();
            inOut.run();
        } catch (Exception ignored) {}
    }

}
