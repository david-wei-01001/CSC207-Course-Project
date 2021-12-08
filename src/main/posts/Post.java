package posts;

import maps.IterableMap;
import user.User;

import java.util.Map;

/**
 * A post in which a main.user can share his/her reflection on a study session.
 */
public class Post extends PublishedContents implements HasPublishedContents {
    /**
     * the content of this post.
     */
    private final String content;
    /**
     * The map of all comments to this post, with comment ids as keys and comments as values.
     */
    private final IterableMap<String, Comment> mapOfComments = new IterableMap<>();
    private int numberOfComments;


    /**
     * Constructor of the post
     *
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
     *
     * @param content the content of the comment being added
     * @param creator the creator of the comment being added
     * @return the id of the comment added.
     */
    @Override
    public String addPublishedContent(String content, User creator) {
        String commentId = getNextId();
        mapOfComments.put(commentId, new Comment(content, commentId, creator));
        numberOfComments ++;
        return commentId;
    }

    /**
     * Delete a comment.
     *
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
     *
     * @return The number of the comments
     */
    public int getNumberOfComments(){
        return numberOfComments;
    }

    /**
     * Get the ID for the comment to be added
     *
     * @return: The ID for the comment to be added
     */
    public String getNextId(){
        return "Comment #" + numberOfComments;
    }

    /**
     * Get function for the map of comments
     *
     * @return: The map of comments
     */
    public Map<String, Comment> getMapOfComments(){
        return this.mapOfComments;
    }

    /**
     * @return The string representing the post
     */
    @Override
    public String toString() {
        StringBuilder comments = new StringBuilder();
        for(String commentName: mapOfComments){
            Comment comment = mapOfComments.get(commentName);
            comments.append(commentName).append("=").append(comment.toString()).append('\n').append(
                    "-----------------------------------------------" +
                    "-------------------------------------------------------").append('\n');
        }
        return "Post" + '\n' +
                "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                +'\n'+
                "id=" + super.getId() + ", creator=" + this.getCreator().getName() + '\n' +
                "------------------------------------------------------------------------------------------------------"
                +'\n'+
                content + '\n' +
                "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                +'\n'+
                "Comments" + '\n' +
                "------------------------------------------------------------------------------------------------------"
                +'\n'+
                comments +
                "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                ;
    }

    /**
     * @return The content of the post
     */
    public String getContent() {
        return content;
    }

}