package resource;

import user.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The use case that controls a main.user's interaction with the resource system.
 */
public class ResourceManager implements HasResource, Serializable{


    private Map<String, Resource> mapOfResource = new HashMap<>();


    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Add the target resource to the map of resource
     * @param content: The content that the resource contains, normally a link
     * @param point: The point which the resource contains
     * @param description: The brief description for the resource
     */
    @Override
    public void addResource(String content, int point, String description) {
        String id = getNextId();
        Resource resourceToAdd = new Resource(content,id , point, description, currentUser);
        mapOfResource.put(resourceToAdd.getId(), resourceToAdd);
        currentUser.addResource(resourceToAdd);
    }

    /**
     * @param id: The id of the main.resource to be deleted
     * @throws PostNotFoundException
     */
    @Override
    public void deleteResource(String id) throws PostNotFoundException {
        if (mapOfResource.containsKey(id)) {
            mapOfResource.get(id).setInvisible();
        } else {
            throw ABSENT;
        }

    }

    /**
     * Get the ID for the main.resource to be added
     * @return: The string for the next ID
     */
    public String getNextId(){
        return "Resource #" + mapOfResource.size();
    }


    /**
     * Return the download link for the resources if the main.user have had enough points to redeem it
     * @param resourceId: The ID of the main.resource
     * @return: A notification if the main.user does not have enough points to redeem the main.resource,
     * or a download link if the main.user have enough points to redeem the main.resource
     */
    public String downloadResource(String resourceId) {
        Resource resource = this.mapOfResource.get(resourceId);
        if (currentUser.getRewardPoints() < resource.getPointsRequired()) {
            return "Sorry, you do not have enough points";
        }
        else{
            int newPoints = currentUser.getRewardPoints() - resource.getPointsRequired();
            currentUser.setRewardPoints(newPoints);
            currentUser.addResource(resource);
            resource.addDownloadTimes();
        }
        return resource.getContent();
    }

    /**
     * Get the total number of the resources in the map
     * @return: The number of the resources in the map
     */
    public int getNumberOfResources(){
        return mapOfResource.size();
    }

    /**
     * Getter for the map of main.resource
     * @return: The map of resources
     */
    public Map<String, Resource> getMapOfResource() {
        return mapOfResource;
    }

    public void addDefault() {
        User user = new User("Tong", "123@mail.com", "123");
        Resource resourceToAdd1 = new Resource("https://www.teach.cs.toronto.edu/~csc110y/fall/notes/",
                getNextId(), 0, "Course notes of csc110", user);
        mapOfResource.put(resourceToAdd1.getId(), resourceToAdd1);
        Resource resourceToAdd2 = new Resource("https://www.youtube.com/watch?v=eIrMbAQSU34",
                getNextId(), 25, "Java Tutorial for Beginners", user);
        mapOfResource.put(resourceToAdd2.getId(), resourceToAdd2);
    }

}
