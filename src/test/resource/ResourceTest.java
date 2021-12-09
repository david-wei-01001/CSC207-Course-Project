package resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the Resource class
 */
class ResourceTest {
    ResourceManager manager;
    Resource resource;
    User user;

    /**
     * The setup method that setup each test.
     * It creates a ResourceManager, a resource and a user.
     */
    @BeforeEach
    void setUp() {
        user = new User("Tong", "123@mail.com", "123");
        manager = new ResourceManager();
        manager.setCurrentUser(user);
        resource = new Resource("nothing", manager.getNextId(),
                0, "Please do not download it.", user);
    }

    /**
     * Test if the getDownloadTimes method correctly return the total number of downloads
     */
    @Test
    void testAddDownloadTimes() {
        manager.addResource(resource.getContent(), resource.getPointsRequired(), resource.getDescription());
        manager.downloadResource("Resource #0");
        assertEquals(1, manager.getMapOfResource().get("Resource #0").getDownloadTimes());
    }

    /**
     * test if the resource is properly converted to string format
     */
    @Test
    void testToString() {
        assertEquals("Resource{" +
                "content='" + "nothing" + '\'' +
                ", pointsRequired=" + 0 +
                ", description='" + "Please do not download it." + '\'' +
                ", downloadTimes=" + 0 +
                '}', resource.toString());
    }
}