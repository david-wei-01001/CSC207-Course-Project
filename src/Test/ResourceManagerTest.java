package Test;

import Resource.HasResource;
import Resource.Resource;
import Resource.ResourceManager;
import User.User;
import User.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TODO: implement this test
 */
public class ResourceManagerTest {
    ResourceManager manager;
    User user;

    @BeforeEach
    public void setUp() {
        manager = new ResourceManager();
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        user = new User(userInfo);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
     public void testAddAndGetMapOfResource() {
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", user);
        manager.add(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription(),user);
        Resource resource2 = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", user);
        manager.add(resource2.getContent(), resource2.getPointsRequired(), resource2.getDescription(),user);
        Assertions.assertTrue(manager.getMapOfResource().containsKey("Resource #0"));
        Assertions.assertTrue(manager.getMapOfResource().containsKey("Resource #1"));
        Assertions.assertEquals( resource1.toString(),manager.getMapOfResource().get("Resource #0").toString());
        Assertions.assertEquals(resource2.toString(),manager.getMapOfResource().get("Resource #1").toString());
    }



    @Test
    void delete() throws HasResource.PostNotFoundException {
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", user);
        manager.add(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription(),user);
        Resource resource2 = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", user);
        manager.add(resource2.getContent(), resource2.getPointsRequired(), resource2.getDescription(),user);
        manager.delete("Resource #0");
        Assertions.assertTrue(manager.getMapOfResource().containsKey("Resource #1"));
        Assertions.assertFalse(manager.getMapOfResource().get("Resource #0").visibility());
        Assertions.assertEquals(manager.getMapOfResource().get("Resource #1").toString(), resource2.toString());
        Assertions.assertEquals( 2,manager.getNumberOfResources());
    }

    @Test
    void getNextId() {
        Assertions.assertEquals(manager.getNextId(), "Resource #0");
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", user);
        manager.add(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription(),user);
        Assertions.assertEquals("Resource #1",manager.getNextId());
    }

    @Test
    void downloadResource() {
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", user);
        manager.add(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription(),user);
        UserInfo userInfo = new UserInfo("Su", "123@mail.com", "123");
        User poorUser = new User(userInfo);
        String message1 = manager.downloadResource(poorUser, "Resource #0");
        Assertions.assertEquals("Sorry, you do not have enough points", message1);
        userInfo.setRewardPoints(300);
        String message2 = manager.downloadResource(poorUser, "Resource #0");
        Assertions.assertEquals(resource1.getContent(),message2);
    }
}