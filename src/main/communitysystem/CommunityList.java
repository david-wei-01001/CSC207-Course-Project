package communitysystem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of community name —> Community object.
 */

public class CommunityList implements Serializable {
    private final Map<String, Community> communityList = new HashMap<>();

    /**
     * Add community to this community list.
     * @param community is the community need to add
     */
    public void add(Community community) {
        communityList.put(community.getNameOfCommunity(), community);
    }

    public void remove(String nameofCommunity){
        communityList.remove(nameofCommunity);
    }

    /**
     * Return the User associated with username.
     * @param nameOfCommunity the username of the user to get.
     */
    public Community get(String nameOfCommunity) {
        return communityList.get(nameOfCommunity);
    }

    public boolean containsKey(String nameOfCommunity){ return communityList.containsKey(nameOfCommunity); }

}
