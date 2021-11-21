//package Test;
//
//import User.User;
//import User.UserInfo;
//import Resource.ResourceManager;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import Resource.Resource;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * TODO: implement this test.
// */
//class ResourceTest {
//    ResourceManager manager;
//    Resource resource;
//    User user;
//
//    @BeforeEach
//    void setUp() {
//        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
//        user = new User(userInfo);
//        manager = new ResourceManager();
//        resource = new Resource("nothing", manager.getNextId(),
//                0, "Please do not download it.", user);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void addDownloadTimes() {
//        manager.addResource(resource.getContent(), resource.getPointsRequired(), resource.getDescription(),user);
//        UserInfo userInfo = new UserInfo("Su", "123@mail.com", "123");
//        User poorUser = new User(userInfo);
//        manager.downloadResource(poorUser, "Resource #0");
//        assertEquals(1, manager.getMapOfResource().get("Resource #0").getDownloadTimes());
//    }
//
//    @Test
//    void testToString() {
//        assertEquals( "Resource{" +
//                "content='" + "nothing" + '\'' +
//                ", pointsRequired=" + 0 +
//                ", description='" + "Please do not download it." + '\'' +
//                ", downloadTimes=" + 0 +
//                '}',resource.toString());
//    }
//}