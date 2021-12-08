package resource;

import posts.PublishedContents;
import user.User;

/**
 * A resource in which a user can share what he/she used to study a session.
 */
public class Resource extends PublishedContents {
    private final String content;
    private final int pointsRequired;
    private final String description;
    private int downloadTimes;


    /**
     * Constructor for the main. resource class
     *
     * @param content     The content of the main. resource
     * @param id          The ID of the main. resource
     * @param point       The reward point needed for the main. resource
     * @param description The description of the main. resource
     * @param creator     The creator of the main. resource
     */
    public Resource(String content, String id, int point, String description, User creator) {
        super(id, creator);
        this.content = content;
        this.pointsRequired = point;
        this.description = description;
    }

    /**
     * Get the description of the main. resource
     *
     * @return The description of the main. resource
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the total download time of the main. resource
     *
     * @return: The total download time of the main. resource
     */
    public int getDownloadTimes() {
        return this.downloadTimes;
    }

    /**
     * Get the required points for download of the main. resource
     *
     * @return: The required points of the main. resource
     */
    public int getPointsRequired() {
        return this.pointsRequired;
    }

    /**
     * Get the content of the main. resource
     *
     * @return: The content of the main. resource
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Increment the download time by 1
     */
    public void addDownloadTimes() {
        this.downloadTimes++;
    }

    /**
     * @return The string representing the main. resource
     */
    @Override
    public String toString() {
        return "Resource{" +
                "content='" + content + '\'' +
                ", pointsRequired=" + pointsRequired +
                ", description='" + description + '\'' +
                ", downloadTimes=" + downloadTimes +
                '}';
    }
}

