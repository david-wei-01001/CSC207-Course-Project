package JSONWriterAndReader;
import User.User;
import User.UserInfo;
import User.UserManager;
import org.junit.Test;



import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserReadWriter{
    private Map<String, User> libraryOfUsers;
    private String filePath;

    public UserReadWriter() {
//        UserManager userManager
//        this.libraryOfUsers = userManager.getLibraryOfUsers();
//        this.filePath = "src/JSON/LibraryOfUsersUsers.json";

    }

//
//    public static void saveToFile() throws IOException {
//        OutputStream file = new FileOutputStream(filePath);
//        OutputStream buffer = new BufferedOutputStream(file);
//        ObjectOutput output = new ObjectOutputStream(buffer);
//
//        // serialize the Map
//        output.writeObject(libraryOfUsers.toString());
//        output.close();
//    }


    public static String readFromFile(String fileName) throws IOException, ClassNotFoundException {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
