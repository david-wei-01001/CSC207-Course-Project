package Posts;

public class Comment extends PublishedContents {
    String content;

    public Comment(String content, String id) {
        super(id);
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
