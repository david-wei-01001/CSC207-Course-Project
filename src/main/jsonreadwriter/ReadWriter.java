package jsonreadwriter;


import user.UserList;

import java.io.*;

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

//    Map<String, ?> readFromFile() throws IOException, ClassNotFoundException;
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

