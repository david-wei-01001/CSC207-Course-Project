package posts;

/**
 * An interface specifies all properties of objects that can "delete" PublishedContents need to have
 */
public interface Visible {

    /**
     * Set the status of published contents to false
     */
    void setInvisible();

    /**
     * @Return the status of the visibility
     */
    boolean visibility();
}
