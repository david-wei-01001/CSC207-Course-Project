package JSONWriterAndReader;
import User.User;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface ReaderWriter{
    void saveToFile() throws IOException;



    Map<String, ?> readFromFile() throws IOException, ClassNotFoundException;
//    private Map<String, User> libraryOfUsers;
//    private String filePath;


//    public static String reader(String fileName) throws IOException, ClassNotFoundException {
//        String jsonStr = "";
//        try {
//            File jsonFile = new File(fileName);
//            FileReader fileReader = new FileReader(jsonFile);
//
//            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
//            int ch = 0;
//            StringBuffer sb = new StringBuffer();
//            while ((ch = reader.read()) != -1) {
//                sb.append((char) ch);
//            }
//            fileReader.close();
//            reader.close();
//            jsonStr = sb.toString();
//            return jsonStr;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String writer(String fileName) throws IOException, ClassNotFoundException{
//        //need TODO
//        return null;
//    }
}

