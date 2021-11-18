package Posts;

import User.User;

/**
 * A comment.
 */
public class Comment extends PublishedContents {
    /**
     * the content of a comment
     */
    String content;

    public Comment(String content, String id, User creator) {
        super(id, creator);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                '}';
    }
}
