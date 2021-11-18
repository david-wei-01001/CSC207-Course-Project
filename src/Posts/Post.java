package Posts;

import User.User;

import java.util.HashMap;

/**
 * A post in which a user can share his/her reflection on a study session.???
 */
public class Post extends PublishedContents implements HasPublishedContents {
    /**
     * the content of this post.
     */
    private String content;
    /**
     * The map of all comments to this post, with comment ids as keys and comments as values.
     */
    private HashMap<String, Comment> mapOfComments = new HashMap<>();
    private int numberOfComments;

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
    public String addPublishedContent(String content, User creator) {
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

    public int getNumberOfComments(){
        return numberOfComments;
    }

    public String getNextId(){
        return "Comment #" + numberOfComments;
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

}