package Resource;

import User.User;

import java.io.Serializable;
import java.util.HashMap;

public class ResourceManager implements HasResource, Serializable{

    private final HashMap<String, Resource> mapOfResource = new HashMap<>();

    public HashMap<String, Resource> getMapOfResource() {
        return mapOfResource;
    }


    @Override
    public void add(String content,int point, String description, User creator) {
        Resource resourceToAdd = new Resource(content, getNextId(), point, description, creator);
        mapOfResource.put(resourceToAdd.getId(), resourceToAdd);
    }

    /**
     * TODO: Alfred: i dont think we should implement it this way
     * @param id
     * @throws PostNotFoundException
     */
    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfResource.containsKey(id)) {
            mapOfResource.get(id).setInvisible();
        } else {
            throw ABSENT;
        }

    }
    public String getNextId(){
        return "Resource #" + mapOfResource.size();
    }

    public String downloadResource(User user, String resourceId) {
        Resource resource = this.mapOfResource.get(resourceId);
        if (user.getUserInfo().getRewardPoints() < resource.getPointsRequired()) {
            return "Sorry, you do not have enough points";
        }
        else{
            int newPoints = user.getUserInfo().getRewardPoints() - resource.getPointsRequired();
            user.getUserInfo().setRewardPoints(newPoints);
            user.getUserInfo().addResource(resource);
            resource.addDownloadTimes();
        }
        return resource.getContent();
    }

    public int getNumberOfResources(){
        return mapOfResource.size();
    }

}
