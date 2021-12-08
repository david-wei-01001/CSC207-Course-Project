package interfaceadapter;

/**
 * Apply Dependency Inversion Principle so that the controller SystemInOut no longer depends on the specific UI
 */
public interface UIAdapter {

    /**
     * Get the next input from the user
     *
     * @return the input of the user
     */
    String getInput();

    /**
     * quit the program
     */
    void exit();
}
