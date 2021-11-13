package Posts;

import User.User;

import java.io.Serializable;
import java.util.HashMap;

public class Post extends PublishedContents implements HasPost {
    private String content;
    private User creator;
    private HashMap<String, Comment> mapOfComments = new HashMap<>();
    private int numComments;

    public Post(String content, String id, User creator) {
        super(id, creator);
        this.content = content;
        this.creator = creator;
    }

    public HashMap<String, Comment> getMapOfComments(){
        return this.mapOfComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + super.getId() +
                ", creator=" + creator.getUserInfo().getUserName() + '\'' +
                ", content='" + content + '\'' +
                ", listOfComments=" + mapOfComments +
                '}';
    }

    public String getContent() {
        return content;
    }

    @Override
    public void add(String content, User creator) {
        Comment commentToAdd = new Comment(content, getNextId());
        mapOfComments.put(commentToAdd.getId(), commentToAdd);
        numComments += 1;
    }

    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfComments.containsKey(id)) {
            mapOfComments.get(id).setAsUnvisualable();
        } else {
            throw ABSENT;
        }
    }


    public int getNumComments(){
        return numComments;
    }

    public String getNextId(){
        return "Comment #" + numComments;
    }

}