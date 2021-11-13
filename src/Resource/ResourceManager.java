package Resource;

import Posts.HasPost;
import Posts.Post;
import User.User;

import java.io.Serializable;
import java.util.HashMap;

public class ResourceManager implements HasResource, Serializable{
    private HashMap<String, Resource> mapOfResource = new HashMap<>();
    private int numberOfResource;

    @Override
    public void add(String content,int point, String description, User creator) {
        Resource resourceToAdd = new Resource(content, getNextId(), point, description, creator);
        mapOfResource.put(resourceToAdd.getId(), resourceToAdd);
        numberOfResource += 1;

    }

    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfResource.containsKey(id)) {
            mapOfResource.get(id).setAsUnvisualable();
        } else {
            throw ABSENT;
        }

    }
    public String getNextId(){
        return "Resource #" + numberOfResource;
    }

    public String download(User user, String id){
        Resource resource = this.mapOfResource.get(id);
        if (user.getRewardPoints() < resource.getPointsRequired()) {
            return "Sorry, you do not have enough points";
        }
        else{
            int newPoints = user.getRewardPoints() - resource.getPointsRequired();
            user.setRewardPoints(newPoints);
            user.addResource(resource);
            resource.addDownloadTimes();
        }
        return resource.getContent();
    }

}
