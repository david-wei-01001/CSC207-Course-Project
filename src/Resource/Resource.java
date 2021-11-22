package Resource;

import Posts.PublishedContents;
import User.UserInfo;

public class Resource extends PublishedContents {
    private final String content;
    private final int pointsRequired;
    private final String description;
    private int downloadTimes;


    /**
     * Constructor for the resource class
     * @param content: The content of the resource
     * @param id: The ID of the resource
     * @param point: The reward point needed for the resource
     * @param description: The description of the resource
     * @param creator: The creator of the resource
     */
    public Resource(String content, String id, int point, String description, UserInfo creator) {
        super(id, creator);
        this.content = content;
        this.pointsRequired = point;
        this.description = description;
    }

    /**
     * Get the description of the resource
     * @return: The description of the resource
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Get the total download time of the resource
     * @return: The total download time of the resource
     */
    public int getDownloadTimes(){
        return this.downloadTimes;
    }

    /**
     * Get the required points for download of the resource
     * @return: The required points of the resource
     */
    public int getPointsRequired(){
        return this.pointsRequired;
    }

    /**
     * Get the content of the resource
     * @return: The content of the resource
     */
    public String getContent(){
        return this.content;
    }

    /**
     * Increment the download time by 1
     */
    public void addDownloadTimes(){
        this.downloadTimes ++;
    }

    /**
     * @return The string representing the resource
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

