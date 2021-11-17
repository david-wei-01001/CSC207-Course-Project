package Resource;

import Posts.PublishedContents;
import User.User;

public class Resource extends PublishedContents {
    private String content;
    private int pointsRequired;
    private String description;
    private int downloadTimes;

    public Resource(String content, String id, int point, String description, User creator) {
        super(id, creator);
        this.content = content;
        this.pointsRequired = point;
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public int requiredPointsOfDownload() {
        return this.pointsRequired;
    }

    public int getDownloadTimes(){
        return this.downloadTimes;
    }

    public int getPointsRequired(){
        return this.pointsRequired;
    }

    public void addDownloadTimes(){
        this.downloadTimes ++;
    }

    public String getContent(){
        return this.content;
    }

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

