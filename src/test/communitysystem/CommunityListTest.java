package communitysystem;

import org.junit.jupiter.api.Test;

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
        assertEquals(communitylist.getCommunityList(), target);
    }

    @Test
    void remove() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        communitylist.remove("python");
        HashMap<String, Community> target = new HashMap<>();
        assertEquals(communitylist.getCommunityList(), target);
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