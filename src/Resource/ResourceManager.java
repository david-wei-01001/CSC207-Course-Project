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

    public HashMap<String, Resource> getMapOfResource() {
        return mapOfResource;

    }

}
