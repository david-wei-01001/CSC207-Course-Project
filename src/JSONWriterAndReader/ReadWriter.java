////package JSONWriterAndReader;
////
////import Java.UserInfo;
////
////import java.io.IOException;
////import java.util.Map;
////
////public interface ReadWriter {
////
////
////    void saveToFile() throws IOException;
////
////
////    String readFromFile() throws IOException, ClassNotFoundException;
////}
//
//import JSONWriterAndReader.UserReadWriter;
//
//public class ReadWriter {
//
//    public static void main(String[] args) throws  Exception{
//        String s = UserReadWriter.readFromFile("/Users/asheg/IdeaProjects/course-project-whatever-you-want-1/src/JSON/read_this_file.json");
//        System.out.println(s);
//    }
////        String path = Hello.class.getClassLoader().getResource("test.json").getPath();
////        String s = ReadUtils.readJsonFile(path);
////        JSONObject jobj = JSON.parseObject(s);
////        System.out.println("name"+jobj.get("name"));
////        JSONObject address1 = jobj.getJSONObject("address");
////        String street = (String) address1.get("street");
////        String city = (String) address1.get("city");
////        String country = (String) address1.get("country");
////
////        System.out.println("street :" + street);
////        System.out.println("city :" + city);
////        System.out.println("country :" + country);
////
////        JSONArray links = jobj.getJSONArray("links");
////
////        for (int i = 0 ; i < links.size();i++){
////            JSONObject key1 = (JSONObject)links.get(i);
////            String name = (String)key1.get("name");
////            String url = (String)key1.get("url");
////            System.out.println(name);
////            System.out.println(url);
////        }
////    }
//}
