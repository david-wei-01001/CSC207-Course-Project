package test;

import main.user.UserInfo;
import main.resource.ResourceManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.resource.Resource;

import static org.junit.jupiter.api.Assertions.*;


class ResourceTest {
    ResourceManager manager;
    Resource resource;
    UserInfo userInfo;

    @BeforeEach
    void setUp() {
        userInfo = new UserInfo("Tong", "123@mail.com", "123");
        manager = new ResourceManager(userInfo);
        resource = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", userInfo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addDownloadTimes() {
        manager.addResource(resource.getContent(), resource.getPointsRequired(), resource.getDescription());
        manager.downloadResource("Resource #0");
        assertEquals(1, manager.getMapOfResource().get("Resource #0").getDownloadTimes());
    }

    @Test
    void testToString() {
        assertEquals( "Resource{" +
                "content='" + "nothing" + '\'' +
                ", pointsRequired=" + 0 +
                ", description='" + "Please do not download it." + '\'' +
                ", downloadTimes=" + 0 +
                '}',resource.toString());
    }
}