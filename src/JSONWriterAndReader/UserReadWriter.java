package JSONWriterAndReader;

import User.User;
import User.UserManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserReadWriter implements ReadWriter {
    private Map<String, User> libraryOfUsers;
    private String filePath;

    public UserReadWriter(UserManager userManager) {
        this.libraryOfUsers = userManager.getLibraryOfUsers();
        this.filePath = "src/JSON/LibraryOfUsersUsers.json";
    }


    @Override
    public void saveToFile() throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(libraryOfUsers.toString());
        output.close();
    }

    @Override
    public Map<String, User> readFromFile() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // deserialize the Map
        HashMap<String, User> result = (HashMap<String, User>) input.readObject();
        input.close();
        return result;
    }
}
