package Posts;

import User.UserInfo;

import java.util.HashMap;

/**
 * A post in which a user can share his/her reflection on a study session.
 */
public class Post extends PublishedContents implements HasPublishedContents {
    /**
     * the content of this post.
     */
    private final String content;
    /**
     * The map of all comments to this post, with comment ids as keys and comments as values.
     */
    private final HashMap<String, Comment> mapOfComments = new HashMap<>();
    private int numberOfComments;


    /**
     * Constructor of the post
     * @param content: The content of the post
     * @param id: The ID of the post
     * @param creator: The creator of the post
     */
    public Post(String content, String id, User creator) {
        super(id, creator);
        this.content = content;
    }


    /**
     * Add a comment to this post.
     * @param content the content of the comment being added
     * @param creator the creator of the comment being added
     * @return the id of the comment added.
     */
    @Override
    public String addPublishedContent(String content, UserInfo creator) {
        String commentId = getNextId();
        mapOfComments.put(commentId, new Comment(content, commentId, creator));
        numberOfComments ++;
        return commentId;
    }

    /**
     * Delete a comment.
     * @param id the id of the comment being deleted
     * @throws PostNotFoundException if no comment with the inputted id was found.
     */
    @Override
    public void deletePublishedContent(String id) throws PostNotFoundException {
        if (mapOfComments.containsKey(id)) {
            mapOfComments.get(id).setInvisible();
//            this.numberOfComments --;
        } else {
            throw ABSENT;
        }
    }

    /**
     * Get the number of comments
     * @return The number of the comments
     */
    public int getNumberOfComments(){
        return numberOfComments;
    }

    /**
     * Get the ID for the comment to be added
     * @return: The ID for the comment to be added
     */
    public String getNextId(){
        return "Comment #" + numberOfComments;
    }

    /**
     * Get function for the hashmap of comments
     * @return: The map of comments
     */
    public HashMap<String, Comment> getMapOfComments(){
        return this.mapOfComments;
    }

    /**
     * @return The string representing the post
     */
    @Override
    public String toString() {
        return "Post{" +
                "id=" + super.getId() +
                ", creator=" + this.getCreator().getUsername() + '\'' +
                ", content='" + content + '\'' +
                ", listOfComments=" + mapOfComments +
                '}';
    }

    /**
     * @return The content of the post
     */
    public String getContent() {
        return content;
    }

}