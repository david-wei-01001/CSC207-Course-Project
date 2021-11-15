package Posts;

import User.User;

import java.io.Serializable;
import java.util.HashMap;

public class Post extends PublishedContents implements HasPost {
    private String content;
    private HashMap<String, Comment> mapOfComments = new HashMap<>();
    private int numComments;

    public Post(String content, String id, User creator) {
        super(id, creator);
        this.content = content;
    }

    public HashMap<String, Comment> getMapOfComments(){
        return this.mapOfComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + super.getId() +
                ", creator=" + this.getCreator().getUserInfo().getUserName() + '\'' +
                ", content='" + content + '\'' +
                ", listOfComments=" + mapOfComments +
                '}';
    }

    public String getContent() {
        return content;
    }

    @Override
    public String add(String content, User creator) {
        String commentId = getNextId();
        mapOfComments.put(commentId, new Comment(content, commentId, creator));
        numComments += 1;
        return commentId;
    }

    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfComments.containsKey(id)) {
            mapOfComments.get(id).setAsUnvisualable();
            this.numComments --;
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