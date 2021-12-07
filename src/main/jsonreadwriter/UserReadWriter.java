package jsonreadwriter;
import user.UserList;

import java.io.*;

/**
 * A class responsible for reading and writing users for json file
 */
public class UserReadWriter implements ReadWriter {

    /**
     * Writes the users to file at filePath.
     *
     * @param filePath the file to write the records to
     * @param o    stores the list of users to be serialized
     * @throws IOException the exception is thrown
     */
    @Override
    public void saveToFile(String filePath, Object o) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(o);
        output.close();
    }


    /**
     * Store the users to file at filePath.
     *
     * @param filePath file where the user list is stored
     * @return list of users
     */
    @Override
    public UserList readFromFile(String filePath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        UserList usersList = (UserList) input.readObject();
        input.close();
        return usersList;
    }
}