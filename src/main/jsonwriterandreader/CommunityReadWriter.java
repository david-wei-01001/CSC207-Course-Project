//package jsonwriterandreader;
//import communitysystem.Community;
//import communitysystem.CommunityLibrary;
//
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CommunityReadWriter implements ReadWriter {
//    private Map<String, Community> mapOfCommunity;
//    private String filePath;
//
//    public CommunityReadWriter(CommunityLibrary communityLibrary) {
//        this.mapOfCommunity = communityLibrary.getMapOfCommunity();
//        this.filePath = "src/JSON/MapOfCommunity.main.json";
//    }
//
//
//    @Override
//    public void saveToFile() throws IOException {
//        OutputStream file = new FileOutputStream(filePath);
//        OutputStream buffer = new BufferedOutputStream(file);
//        ObjectOutput output = new ObjectOutputStream(buffer);
//
//        // serialize the Map
//        output.writeObject(mapOfCommunity);
//        output.close();
//    }
//
//    @Override
//    public Map<String, Community> readFromFile() throws IOException, ClassNotFoundException {
//        InputStream file = new FileInputStream(filePath);
//        InputStream buffer = new BufferedInputStream(file);
//        ObjectInput input = new ObjectInputStream(buffer);
//
//        // deserialize the Map
//        HashMap<String, Community> result = (HashMap<String, Community>) input.readObject();
//        input.close();
//        return result;
//    }
//}
