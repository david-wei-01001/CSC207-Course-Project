package main.posts;

import main.user.UserInfo;

import java.io.Serializable;

public class PublishedContents implements Likable, Visible, Serializable {
    private String id;
    private int numLike;
    private boolean visible = true;
    private UserInfo creator;

    /**
     * Constructor for the published contents class
     * @param id: The id of the comment
     * @param creator: The creator of the comment
     */
    public PublishedContents(String id, UserInfo creator) {
        this.id = id;
        this.creator = creator;
    }

    /**
     * Get the ID of the published contents
     * @return Then ID of the published contents
     */
    public String getId() {
        return id;
    }


    /**
     * Get the creator of the published contents
     * @return Then creator of the published contents
     */
    public UserInfo getCreator() {
        return creator;
    }

    /**
     * Get the number of like of the published contents
     * @return Then number of like of the published contents
     */
    @Override
    public int getNumberOfLike() {
        return numLike;
    }

    /**
     * Increment the like by 1
     * @return The incremented likes
     */
    @Override
    public void like() {
        numLike += 1;
    }

    /**
     * Set the status of published contents to true
     */
    @Override
    public void setVisible() {
        visible = true;
    }

    /**
     * Set the status of published contents to false
     */
    @Override
    public void setInvisible() {
        visible = false;
    }

    /**
     * Return the status of the visibility
     */
    @Override
    public boolean visibility() {
        return visible;
    }
}
