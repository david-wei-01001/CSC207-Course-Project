package resource;

import user.User;
import resource.ResourceManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resource.Resource;

import static org.junit.jupiter.api.Assertions.*;


class ResourceTest {
    ResourceManager manager;
    Resource resource;
    User user;

    @BeforeEach
    void setUp() {
        user = new User("Tong", "123@mail.com", "123");
        manager = new ResourceManager();
        manager.setCurrentUser(user);
        resource = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", user);
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