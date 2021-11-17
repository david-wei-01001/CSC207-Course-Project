package Test;

    import CommunitySystem.Community;
    import Posts.HasPublishedContents;
    import Posts.Post;
    import User.User;
    import org.junit.Before;
    import org.junit.Test;
    import User.UserInfo;

    import static org.junit.Assert.*;

public class communityTest {
    Community community;
    User user;

    @Before
    public void setUp() throws Exception {
        community = new Community("hi");
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        user = new User(userInfo);
    }

    @Test(timeout = 50)
    public void testNextIdNoPost() {

        assertEquals(community.getNextId(), "Post #0");
    }

    /**
     * TODO: missing test for whether
     */
    @Test(timeout = 500)
    public void testAdd() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        assertTrue(community.getMapOfPost().containsKey("Post #0"));
        assertTrue(community.getMapOfPost().containsKey("Post #1"));
        assertEquals(community.getMapOfPost().get("Post #0").toString(), post1.toString());
        assertEquals(community.getMapOfPost().get("Post #1").toString(), post2.toString());
    }

    @Test(timeout = 50)
    public void testNextId() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        assertEquals(community.getNextId(), "Post #2");
    }

    @Test(timeout = 50)
    public void testGetNumPost() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        assertEquals(community.getNumberOfPosts(), 2);
    }

    @Test(timeout = 500)
    public void testDelete() throws HasPublishedContents.PostNotFoundException {
        Post post0 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post0.getContent(),user);
        Post post1 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post1.getContent(),user);
        community.deletePublishedContent("Post #1");
        assertTrue(community.getMapOfPost().containsKey("Post #0"));
        assertEquals(community.getMapOfPost().get("Post #0").toString(), post0.toString());
        assertFalse(community.getMapOfPost().get("Post #1").visibility());
        assertEquals(community.getNumberOfPosts(), 2);
    }

    @Test(timeout = 500)
    public void testDisplayPosts() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        String str1 = post1 + "\n" + post2 + "\n";
        String str2 = post2 + "\n" + post1 + "\n";
        assertTrue(community.displayPosts().equals(str1) || community.displayPosts().equals(str2));
    }

    /**
     * TODO: implement this test
     * check if this community has been instantiated correctly.
     */
    @Test(timeout = 500)
    public void testToString() {

    }
}