package jsonreadwriter;

import interfaceadapter.UIAdapter;

/**
 * A mock UI with an array of inputs used for testing
 */
public class TestingInput implements UIAdapter {

    private final String[] inputs;
    private int curr;
    private boolean reachEnd;

    public TestingInput(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Get the next input from the user
     *
     * @return the input of the user
     */
    @Override
    public String getInput() {
        String toReturn = inputs[curr];
        System.out.println(toReturn);
        curr += 1;
        return toReturn;
    }

    /**
     * quit the program
     */
    @Override
    public void exit() {
        reachEnd = true;
    }

    /**
     * Only intended to be used for testing.
     * return whether the test reaches the end of the inputs
     *
     *  @return true if the test reaches the end of the inputs, false otherwise
     */
    public boolean getToEnd() {
        return reachEnd;
    }
}
