package jsonreadwriter;


import java.io.IOException;

/**
 * An interface specifying what all ReadWriter's need to read from and write to a json file
 */
public interface ReadWriter {

    /**
     * @param filepath location of ser file
     * @param o object to be serialized
     */
    void saveToFile(String filepath, Object o) throws IOException;

    /**
     * @param filepath location of ser file
     */
    Object readFromFile(String filepath) throws IOException, ClassNotFoundException;

}

