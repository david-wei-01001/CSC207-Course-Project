package User;

import Graph.DirectedGraph;
import Posts.Post;
import Resource.Resource;
import constants.Achievements;

import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    private String name;
    private String email;
    private String password;
    private int rewardPoints;
    private LocalDateTime lastLogin;
    private int totalLogins;
    private ArrayList<Post> listOfPost = new ArrayList<>();
    private ArrayList<DirectedGraph> listOfGraph = new ArrayList<>();
    private ArrayList<Resource> listOfResource = new ArrayList<>();
    private Map<String, Boolean> mapOfAchievement = new HashMap<>();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public UserInfo(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rewardPoints = 0;
        this.lastLogin = LocalDateTime.now();
        this.totalLogins = 0;
    }

    public void addPost(Post post){
        listOfPost.add(post);
        checkAndRequestAchievement(Achievements.ARRAY_OF_POST_THRESHOLDS, Achievements.MAP_POST_THRESHOLDS_TO_ACHIEVEMENT, listOfPost.size());

    }

    public void incrementTotalLogins() {
        totalLogins += 1;
        checkAndRequestAchievement(Achievements.ARRAY_OF_TOTAL_LOGINS_THRESHOLDS, Achievements.MAP_TOTAL_LOGINS_THRESHOLDS_TO_ACHIEVEMENT, totalLogins);
    }

    private void checkAndRequestAchievement(int[] thresholds, Map<Integer, String> thresholdsToAchievement, int property) {
        for (int threshold : thresholds) {
            if (property == threshold) {
                mapOfAchievement.replace(thresholdsToAchievement.get(property), false, true);
            }
        }
    }

    public void initializeMapOfAchievement() {
        for (String achievement : Achievements.ARRAY_OF_ALL_ACHIEVEMENTS) {
            mapOfAchievement.put(achievement, false);
        }
    }


    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }





//    Deprecated:

//    private void checkAndRequestAchievement(int[] thresholds, int property, String achievementName) {
//        for (int threshold : thresholds) {
//            if (property == threshold) {
//                observable.firePropertyChange(achievementName, property - 1, property);
//            }
//        }
//    }

}
