package application;

import interfaceadapter.SystemInOut;

/**
 * The main class of our application. This is where to start the program.
 */
public class TechnicalTree {
    /**
     * The main method of our program. Call this method to begin.
     * TODO: write JavaDoc for Exception
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SystemInOut inOut = new SystemInOut();
        inOut.run();
    }

}
