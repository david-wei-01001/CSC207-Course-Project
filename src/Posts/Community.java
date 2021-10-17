package Posts;

import java.util.HashMap;

public class Community implements HasPost{
    private HashMap<String, Post> mapOfPost = new HashMap<>();
    private int numPosts;
    private String nameOfCommunity;

    public Community(String name){
        nameOfCommunity = name;
    }

    @Override
    public void add(PublishedContents content) {
        mapOfPost.put(content.getId(), ((Post) content));
        numPosts += 1;
    }

    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfPost.containsKey(id)) {
            mapOfPost.remove(id);
            numPosts -= 1;
        } else {
            throw ABSENT;
        }
    }

    public String displayPosts() {
        StringBuilder result = new StringBuilder();
        for (Post post : mapOfPost.values()) {
            result.append(post.toString()).append("\n");
        }
        return result.toString();
    }

    public HashMap<String, Post> getMapOfPost() {
        return mapOfPost;
    }

    public int getNumPosts(){
        return numPosts;
    }
    public String getNextId(){
        return "Post #" + numPosts;
    }
}
