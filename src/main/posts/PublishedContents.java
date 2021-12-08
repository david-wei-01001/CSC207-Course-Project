package posts;

import user.User;

import java.io.Serializable;

/**
 * A super class that specifies all the properties of a PublishedContent
 */
public class PublishedContents implements Visible, Serializable {
    private final String id;
    private boolean visible = true;
    private final User creator;

    /**
     * Constructor for the published contents class
     *
     * @param id: The id of the comment
     * @param creator: The creator of the comment
     */
    public PublishedContents(String id, User creator) {
        this.id = id;
        this.creator = creator;
    }

    /**
     * Get the ID of the published contents
     *
     * @return Then ID of the published contents
     */
    public String getId() {
        return id;
    }


    /**
     * Get the creator of the published contents
     *
     * @return Then creator of the published contents
     */
    public User getCreator() {
        return creator;
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
