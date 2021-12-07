package communitysystem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of community name —> Community object.
 */
public class CommunityList implements Serializable {
    private final Map<String, Community> communityList = new HashMap<>();

    public Map<String, Community> getCommunityList() {
        return communityList;
    }

    /**
     * Add community to this community list.
     *
     * @param community is the community need to add
     */
    public void add(Community community) {
        communityList.put(community.getNameOfCommunity(), community);
    }

    /**
     * Remove community to this community list.
     *
     * @param nameOfCommunity is the community need to add
     */
    public void remove(String nameOfCommunity){
        communityList.remove(nameOfCommunity);
    }

    /**
     * Return the User associated with username.
     *
     * @param nameOfCommunity the username of the user to get.
     */
    public Community get(String nameOfCommunity) {
        return communityList.get(nameOfCommunity);
    }

    /**
     * Check whether the given key is in the list
     *
     * @param nameOfCommunity is the key to check whether this community is in the list
     */
    public boolean containsKey(String nameOfCommunity){ return communityList.containsKey(nameOfCommunity); }

}
