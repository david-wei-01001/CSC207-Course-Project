package Posts;

public class PublishedContents implements Likable{
    private String id;
    private int numLike;

    public PublishedContents(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int getNumberOfLike() {
        return numLike;
    }

    @Override
    public void like() {
        numLike += 1;
    }
}
