package Posts;

import java.util.HashMap;

public class Post extends PublishedContents implements HasPost {
    private String content;
    private HashMap<String, Comment> mapOfComments = new HashMap<>();
    private int numComments;

    public Post(String content, String id) {
        super(id);
        this.content = content;
    }

    public HashMap<String, Comment> getMapOfComments(){
        return this.mapOfComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + super.getId() +
                ", content='" + content + '\'' +
                ", listOfComments=" + mapOfComments +
                '}';
    }

    public String getContent() {
        return content;
    }

    @Override
    public void add(PublishedContents content) {
        mapOfComments.put(content.getId(), ((Comment) content));
        numComments += 1;
    }

    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfComments.containsKey(id)) {
            mapOfComments.remove(id);
            numComments -= 1;
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