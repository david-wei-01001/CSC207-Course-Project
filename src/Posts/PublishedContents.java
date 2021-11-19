package Posts;

import User.User;

import java.io.Serializable;

public class PublishedContents implements Likable, Visible, Serializable {
    private String id;
    private int numLike;
    private boolean visible = true;
    private final User creator;

    public PublishedContents(String id, User creator) {
        this.id = id;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }


    public User getCreator() {
        return creator;
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
    public void setVisible() {
        visible = true;
    }

    @Override
    public void setInvisible() {
        visible = false;
    }

    @Override
    public boolean visibility() {
        return visible;
    }
}
