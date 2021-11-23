package test;

import resource.HasResource;
import resource.Resource;
import resource.ResourceManager;
import user.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;



public class ResourceManagerTest {
    ResourceManager manager;
    UserInfo userInfo;

    @BeforeEach
    public void setUp() {
        userInfo = new UserInfo("Tong", "123@mail.com", "123");
        manager = new ResourceManager(userInfo);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
     public void testAddAndGetMapOfResource() {
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", userInfo);
        manager.addResource(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription());
        Resource resource2 = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", userInfo);
        manager.addResource(resource2.getContent(), resource2.getPointsRequired(), resource2.getDescription());
        Assertions.assertTrue(manager.getMapOfResource().containsKey("Resource #0"));
        Assertions.assertTrue(manager.getMapOfResource().containsKey("Resource #1"));
        Assertions.assertEquals( resource1.toString(),manager.getMapOfResource().get("Resource #0").toString());
        Assertions.assertEquals(resource2.toString(),manager.getMapOfResource().get("Resource #1").toString());
    }



    @Test
    void delete() throws HasResource.PostNotFoundException {
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", userInfo);
        manager.addResource(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription());
        Resource resource2 = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", userInfo);
        manager.addResource(resource2.getContent(), resource2.getPointsRequired(), resource2.getDescription());
        manager.deleteResource("Resource #0");
        Assertions.assertTrue(manager.getMapOfResource().containsKey("Resource #1"));
        Assertions.assertFalse(manager.getMapOfResource().get("Resource #0").visibility());
        Assertions.assertEquals(manager.getMapOfResource().get("Resource #1").toString(), resource2.toString());
        Assertions.assertEquals( 2,manager.getNumberOfResources());
    }

    @Test
    void getNextId() {
        Assertions.assertEquals(manager.getNextId(), "Resource #0");
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", userInfo);
        manager.addResource(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription());
        Assertions.assertEquals("Resource #1",manager.getNextId());
    }

    @Test
    void downloadResource() {
        UserInfo otherUser = new UserInfo("Su", "123@mail.com", "123");
        Resource resource1 = new Resource("This is not visible until you paid", manager.getNextId(),
                12, "Please download it.", otherUser);
        manager.addResource(resource1.getContent(), resource1.getPointsRequired(), resource1.getDescription());
        String message1 = manager.downloadResource("Resource #0");
        Assertions.assertEquals("Sorry, you do not have enough points", message1);
        userInfo.setRewardPoints(300);
        String message2 = manager.downloadResource("Resource #0");
        Assertions.assertEquals(resource1.getContent(),message2);
    }
}
