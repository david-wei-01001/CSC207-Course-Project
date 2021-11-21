package Posts;

import User.UserInfo;

/**
 * A Comment class, which is used for leaving comments on posts and community
 */
public class Comment extends PublishedContents {
    String content;

    /**
     * Constructor for the comment class
     * @param content : The content of the comment
     * @param id: The id of the comment
     * @param creator: The creator of the comment
     */
    public Comment(String content, String id, ) {
        super(id, creator);
        this.content = content;
    }

    /**
     * Get the content of the comment
     * @return Then content of the comment
     */
    public String getContent() {
        return content;
    }

    /**
     * @return The string representing the comment
     */
    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                '}';
    }
}
