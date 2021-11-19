package CommunitySystem;

import Posts.HasPublishedContents;
import Posts.Post;
import User.User;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A community, where users add posts.
 */
public class Community implements HasPublishedContents, Serializable {
    private final HashMap<String, Post> MAPOFPOST = new HashMap<>();
    private final String nameOfCommunity;



    public Community(String name){
        nameOfCommunity = name;
    }

    @Override
    public String toString() {
        return "Community{" +
                "mapOfPost=" + MAPOFPOST +
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
    public String addPublishedContent(String content, User creator) {
        String postId = getNextId();
        MAPOFPOST.put(postId, new Post(content, postId, creator));
        return postId;
    }

    /**
     * TODO: Alfred: i dont think we should implement it this way.
     * delete a post in this community.
     * @param id the id of the post being deleted
     * @throws PostNotFoundException if the post with the inputted id is not found in this community.
     */
    @Override
    public void deletePublishedContent(String id) throws PostNotFoundException {
        if (MAPOFPOST.containsKey(id)) {
            MAPOFPOST.get(id).setInvisible();
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
        for (Post post : MAPOFPOST.values()) {
            if(post.visibility()) {
                result.append(post.toString()).append("\n");
            }
        }
        return result.toString();
    }



    public HashMap<String, Post> getMapOfPost() {
        return MAPOFPOST;
    }

    public int getNumberOfPosts(){
        return MAPOFPOST.size();
    }

    public String getNextId(){
        return "Post #" + MAPOFPOST.size();
    }
}
