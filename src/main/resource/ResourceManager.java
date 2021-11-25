package resource;

import user.UserInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager implements HasResource, Serializable{


    private Map<String, Resource> mapOfResource = new HashMap<>();
    private UserInfo currentUserInfo;

    public ResourceManager(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    /**
     * Add the target main.resource to the map of main.resource
     * @param content: The content that the main.resource contains, normally a link
     * @param point: The point which the main.resource contains
     * @param description: The brief description for the main.resource
     */
    @Override
    public void addResource(String content, int point, String description) {
        Resource resourceToAdd = new Resource(content, getNextId(), point, description, currentUserInfo);
        mapOfResource.put(resourceToAdd.getId(), resourceToAdd);
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
        if (currentUserInfo.getRewardPoints() < resource.getPointsRequired()) {
            return "Sorry, you do not have enough points";
        }
        else{
            int newPoints = currentUserInfo.getRewardPoints() - resource.getPointsRequired();
            currentUserInfo.setRewardPoints(newPoints);
            currentUserInfo.addResource(resource);
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
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        Resource resourceToAdd1 = new Resource("https://www.teach.cs.toronto.edu/~csc110y/fall/notes/",
                getNextId(), 5, "Course notes of csc110", userInfo);
        mapOfResource.put(resourceToAdd1.getId(), resourceToAdd1);
        Resource resourceToAdd2 = new Resource("https://www.youtube.com/watch?v=eIrMbAQSU34",
                getNextId(), 25, "Java Tutorial for Beginners", userInfo);
        mapOfResource.put(resourceToAdd2.getId(), resourceToAdd2);
    }

}
