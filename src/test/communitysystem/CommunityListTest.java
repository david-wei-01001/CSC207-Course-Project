package communitysystem;

import org.junit.jupiter.api.Test;
import user.UserInfo;
import user.UserList;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CommunityListTest {

    @Test
    void add() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        HashMap<String, Community> target = new HashMap<>();
        target.put("python", community);
        assertTrue(communitylist.getCommunityList().equals(target));
    }

    @Test
    void remove() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        communitylist.remove("python");
        HashMap<String, Community> target = new HashMap<String, Community>();
        assertTrue(communitylist.getCommunityList().equals(target));
    }

    @Test
    void get() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        assertEquals(communitylist.get("python"), community);
    }

    @Test
    void containsKey() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        assertTrue(communitylist.containsKey("python"));
        assertFalse(communitylist.containsKey("abc"));
    }
}