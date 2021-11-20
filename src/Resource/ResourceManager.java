package Resource;

import User.User;

import java.io.Serializable;
import java.util.HashMap;
import User.UserInfo;

public class ResourceManager implements HasResource, Serializable{

    private HashMap<String, Resource> mapOfResource = new HashMap<>();

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
