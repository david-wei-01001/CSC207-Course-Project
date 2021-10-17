package Posts;

public class PublishedContents implements Likable, Visualable{
    private String id;
    private int numLike;
    private boolean visual = true;

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

    @Override
    public void setAsVisualable() {
        visual = true;
    }

    @Override
    public void setAsUnvisualable() {
        visual = false;
    }

    @Override
    public boolean visulableStatus() {
        return visual;
    }
}
