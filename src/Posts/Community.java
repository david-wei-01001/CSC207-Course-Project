package Posts;

import User.User;

import java.io.Serializable;
import java.util.HashMap;

public class Community implements HasPost, Serializable {
    private HashMap<String, Post> mapOfPost = new HashMap<>();
    private int numberOfPosts;
    private String nameOfCommunity;



    public Community(String name){
        nameOfCommunity = name;
    }


    @Override
    public String add(String content, User creator) {
        String postId = getNextId();
        mapOfPost.put(postId, new Post(content, postId, creator));
        numberOfPosts += 1;
        return postId;
    }



    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfPost.containsKey(id)) {
            mapOfPost.get(id).setAsUnvisualable();
        } else {



            throw ABSENT;
        }
    }



    public String displayPosts() {
        StringBuilder result = new StringBuilder();
        for (Post post : mapOfPost.values()) {
            if(post.visulableStatus()) {
                result.append(post.toString()).append("\n");
            }
        }
        return result.toString();
    }



    public HashMap<String, Post> getMapOfPost() {
        return mapOfPost;
    }

    public int getNumberOfPosts(){
        return numberOfPosts;
    }
    public String getNextId(){
        return "Post #" + numberOfPosts;
    }
}
