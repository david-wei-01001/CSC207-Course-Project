package Graph;

import Java.CommunityLibrary;
import Posts.Community;

public class Vertex {
    private String name;
    private int inLevel;


    public Vertex(String name){
        this.name = name;
        CommunityLibrary.addCommunity(name);
    }

    public void addInLevel(){
        inLevel += 1;
    }

    public void minusInLevel(){
        inLevel -= 1;
    }

    public int getInLevel(){
        return inLevel;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getCommunityName(){return name;}

    @Override
    public String toString(){
        return name;
    }

}
