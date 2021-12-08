package communitysystem;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the CommunityList class
 */
class CommunityListTest {

    /**
     * Test if the add method correctly add a community
     */
    @Test
    void testAdd() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        HashMap<String, Community> target = new HashMap<>();
        target.put("python", community);
        assertEquals(communitylist.getCommunityList(), target);
    }

    /**
     * Test if the remove method correctly remove a community
     */
    @Test
    void testRemove() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        communitylist.remove("python");
        HashMap<String, Community> target = new HashMap<>();
        assertEquals(communitylist.getCommunityList(), target);
    }

    /**
     * Test if the get method correctly retrieve a community
     */
    @Test
    void testGet() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        assertEquals(communitylist.get("python"), community);
    }

    /**
     * Test if the containsKey method works as intended
     */
    @Test
    void testContainsKey() {
        CommunityList communitylist = new CommunityList();
        Community community = new Community("python");
        communitylist.add(community);
        assertTrue(communitylist.containsKey("python"));
        assertFalse(communitylist.containsKey("abc"));
    }
}