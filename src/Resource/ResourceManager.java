package Resource;

import User.UserInfo;
import User.User;

import java.io.Serializable;
import java.util.HashMap;

public class ResourceManager implements HasResource, Serializable{

    private HashMap<String, Resource> mapOfResource = new HashMap<>();
    private UserInfo currentUserInfo;


    public ResourceManager(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    @Override
    public void addResource(String content, int point, String description) {
        Resource resourceToAdd = new Resource(content, getNextId(), point, description, currentUserInfo);
        mapOfResource.put(resourceToAdd.getId(), resourceToAdd);
    }

    /**
     *
     * @param id
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
    public String getNextId(){
        return "Resource #" + mapOfResource.size();
    }

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

    public int getNumberOfResources(){
        return mapOfResource.size();
    }

    public HashMap<String, Resource> getMapOfResource() {
        return mapOfResource;
    }

}
