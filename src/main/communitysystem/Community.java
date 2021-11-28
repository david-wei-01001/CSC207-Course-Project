package communitysystem;

import posts.HasPublishedContents;
import posts.Post;
import user.UserInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A community, where users add main.posts.
 */
public class Community implements HasPublishedContents, Serializable {
    private final Map<String, Post> mapOfPost = new HashMap<>();

    public String getNameOfCommunity() {
        return nameOfCommunity;
    }

    private final String nameOfCommunity;


    /**
     * The constructor of Community class
     * @param name the name of this community
     */

    public Community(String name){
        nameOfCommunity = name;
    }

    /**
     * @return the string representation of this community.
     */
    @Override
    public String toString() {
        return "Community{" +
                "mapOfPost=" + mapOfPost +
                ", nameOfCommunity='" + nameOfCommunity + '\'' +
                '}';
    }

    /**
     * add a post into this community.
     * @param content content of the post being added
     * @param creator creator of the post being added
     * @return the id of the post being added.
     */
    @Override
    public String addPublishedContent(String content, UserInfo creator) {
        String postId = getNextId();
        mapOfPost.put(postId, new Post(content, postId, creator));
        return postId;
    }

    /**
     * delete a post in this community.
     * @param id the id of the post being deleted
     * @throws PostNotFoundException if the post with the inputted id is not found in this community.
     */
    @Override
    public void deletePublishedContent(String id) throws PostNotFoundException {
        if (mapOfPost.containsKey(id)) {
            mapOfPost.get(id).setInvisible();
        } else {
            throw ABSENT;
        }
    }

    /**
     * display all main.posts in this community in the form of text.
     * @return all main.posts in the form of text
     */
    public String displayPosts() {
        StringBuilder result = new StringBuilder();
        for (Post post : mapOfPost.values()) {
            if(post.visibility()) {
                result.append(post).append("\n");
            }
        }
        return result.toString();
    }


    /**
     * @return the mapOfPost instance variable
     */
    public Map<String, Post> getMapOfPost() {
        return mapOfPost;
    }

    /**
     * @return the size of the mapOfPost instance variable
     */
    public int getNumberOfPosts(){
        return mapOfPost.size();
    }

    /**
     * Generate the id of the next post
     * @return a string representation of the id of the next potential post
     */
    public String getNextId(){
        return "Post #" + mapOfPost.size();
    }
}
