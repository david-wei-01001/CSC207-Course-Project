package Resource;

import User.UserInfo;
import User.User;

import java.io.Serializable;
import java.util.HashMap;
import User.UserInfo;

public class ResourceManager implements HasResource, Serializable{


    private HashMap<String, Resource> mapOfResource = new HashMap<>();
    private UserInfo currentUserInfo;

    public ResourceManager(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    /**
     * Add the target resource to the map of resource
     * @param content: The content that the resource contains, normally a link
     * @param point: The point which the resource contains
     * @param description: The brief description for the resource
     * @param creator: The creator of the resource
     */
    @Override
    public void addResource(String content, int point, String description) {
        Resource resourceToAdd = new Resource(content, getNextId(), point, description, currentUserInfo);
        mapOfResource.put(resourceToAdd.getId(), resourceToAdd);
    }

    /**
     * @param id: The id of the resource to be deleted
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
     * Get the ID for the resource to be added
     * @return: The string for the next ID
     */
    public String getNextId(){
        return "Resource #" + mapOfResource.size();
    }


    /**
     * Return the download link for the resources if the user have had enough points to redeem it
     * @param resourceId: The ID of the resource
     * @return: A notification if the user does not have enough points to redeem the resource,
     * or a download link if the user have enough points to redeem the resource
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
     * Getter for the hashmap of resource
     * @return: The map of resources
     */
    public HashMap<String, Resource> getMapOfResource() {
        return mapOfResource;

    public void addDefault() {
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        User user = new User(userInfo);
        Resource resourceToAdd1 = new Resource("https://www.teach.cs.toronto.edu/~csc110y/fall/notes/",
                getNextId(), 5, "Course notes of csc110", user);
        mapOfResource.put(resourceToAdd1.getId(), resourceToAdd1);
        Resource resourceToAdd2 = new Resource("https://www.youtube.com/watch?v=eIrMbAQSU34",
                getNextId(), 25, "Java Tutorial for Beginners", user);
        mapOfResource.put(resourceToAdd2.getId(), resourceToAdd2);
    }

}
