package Posts;

import User.User;

public class Comment extends PublishedContents {
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

    @Override
    public int getNumberOfLike() {
        return 0;
    }

    @Override
    public void like() {

    }
}
