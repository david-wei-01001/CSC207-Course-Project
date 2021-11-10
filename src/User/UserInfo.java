package User;

import Graph.DirectedGraph;
import Posts.Post;
import Resource.Resource;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    private String name;
    private String email;
    private String password;
    private int rewardPoints;
    private Date lastLogin;
    private ArrayList<Post> listOfPost = new ArrayList<>();
    private ArrayList<DirectedGraph> listOfGraph = new ArrayList<>();
    private ArrayList<Resource> listOfResource = new ArrayList<>();
    private Map<String, Boolean> mapOfAchievement = new HashMap<>();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public void addPost(Post post){
        listOfPost.add(post);
    }

//    public boolean checkPostNumberAchievements(){
////        if(){
//
//        }
//    }

}
