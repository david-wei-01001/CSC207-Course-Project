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

    /**
     * The setup method that setup each test.
     * It creates a community and a user.
     */
    @Before
    public void setUp() {
        community = new Community("hi");
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        user = new User(userInfo);
    }

    /**
     * Test if the NextId method works if there is no post added.
     */
    @Test
    public void testNextIdNoPost() {

        assertEquals(community.getNextId(), "Post #0");
    }

    /**
     * TODO: missing test for whether
     */
    @Test
    public void testAdd() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        assertTrue(community.getMapOfPost().containsKey("Post #0"));
        assertTrue(community.getMapOfPost().containsKey("Post #1"));
        assertEquals( post1.toString(),community.getMapOfPost().get("Post #0").toString());
        assertEquals(post2.toString(),community.getMapOfPost().get("Post #1").toString());
    }
    /**
     * Test if the NextId method correctly generate the id for next post when a post is added.
     */
    @Test
    public void testNextId() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        assertEquals("Post #2",community.getNextId());
    }
    /**
     * Test if the getNumPost method correctly reflect the number of posts in the community.
     */
    @Test
    public void testGetNumPost() {
        Post post1 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post1.getContent(),user);
        Post post2 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post2.getContent(),user);
        assertEquals( 2,community.getNumberOfPosts());
    }

    /**
     * test whether the delete method correctly set the post to be deleted as invisible
     * and whether the getNumberOfPosts method correctly reflect this delete.
     * @throws HasPublishedContents.PostNotFoundException if the post to be deleted does not exist.
     */
    @Test
    public void testDelete() throws HasPublishedContents.PostNotFoundException {
        Post post0 = new Post("abc", community.getNextId(),user);
        community.addPublishedContent(post0.getContent(),user);
        Post post1 = new Post("abc", community.getNextId(), user);
        community.addPublishedContent(post1.getContent(),user);
        community.deletePublishedContent("Post #1");
        assertTrue(community.getMapOfPost().containsKey("Post #0"));
        assertEquals( post0.toString(),community.getMapOfPost().get("Post #0").toString());
        assertFalse(community.getMapOfPost().get("Post #1").visibility());
        assertEquals(2,community.getNumberOfPosts());
    }

    /**
     * Test whether displayPosts method correctly display posts.
     */
    @Test
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
     * check if the toString method correctly generates a string representation of this community.
     */
    @Test
    public void testToString() {
        assertEquals("Community{" +
                "mapOfPost=" + "{}" +
                ", nameOfCommunity='" + "hi" + '\'' +
                '}',community.toString());

    }
}