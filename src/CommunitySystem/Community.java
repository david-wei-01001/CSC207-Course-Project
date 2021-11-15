package CommunitySystem;

import Posts.HasPost;
import Posts.Post;
import User.User;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A community, where users add posts.
 */
public class Community implements HasPost, Serializable {
    private HashMap<String, Post> mapOfPost = new HashMap<>();
    private int numberOfPosts;
    private String nameOfCommunity;



    public Community(String name){
        nameOfCommunity = name;
    }


    /**
     * add a post into this community.
     * @param content content of the post being added
     * @param creator creator of the post being added
     * @return the id of the post being added.
     */
    @Override
    public String add(String content, User creator) {
        String postId = getNextId();
        mapOfPost.put(postId, new Post(content, postId, creator));
        numberOfPosts += 1;
        return postId;
    }

    /**
     * delete a post in this community.
     * @param id the id of the post being deleted
     * @throws PostNotFoundException if the post with the inputted id is not found in this community.
     */
    @Override
    public void delete(String id) throws PostNotFoundException {
        if (mapOfPost.containsKey(id)) {
            mapOfPost.get(id).setAsUnvisualable();
        } else {
            throw ABSENT;
        }
    }

    /**
     * display all posts in this community in the form of text.
     * @return all posts in the form of text
     */
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
