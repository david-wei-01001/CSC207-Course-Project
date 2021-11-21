package Test;

import CommunitySystem.Community;
    import Posts.HasPublishedContents;
    import Posts.Post;
    import User.User;
    import org.junit.Before;
    import org.junit.Rule;
    import org.junit.Test;
    import User.UserInfo;
    import org.junit.rules.ExpectedException;

    import static org.junit.Assert.*;

public class communityTest {
    Community community;
    UserInfo userInfo;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * The setup method that setup each test.
     * It creates a community and a user.
     */
    @Before
    public void setUp() {
        community = new Community("hi");
        userInfo = new UserInfo("Tong", "123@mail.com", "123");
    }

    /**
     * Test if the NextId method works if there is no post added.
     */
    @Test
    public void testNextIdNoPost() {

        assertEquals(community.getNextId(), "Post #0");
    }


    @Test(timeout = 500)

    public void testAdd() {
        Post post1 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post1.getContent(), userInfo);
        Post post2 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post2.getContent(), userInfo);
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
        Post post1 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post1.getContent(), userInfo);
        Post post2 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post2.getContent(), userInfo);
        assertEquals("Post #2",community.getNextId());
    }
    /**
     * Test if the getNumPost method correctly reflect the number of posts in the community.
     */
    @Test
    public void testGetNumPost() {
        Post post1 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post1.getContent(), userInfo);
        Post post2 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post2.getContent(), userInfo);
        assertEquals( 2,community.getNumberOfPosts());
    }


    @Test
    public void testDeleteUnsuccessful() throws HasPublishedContents.PostNotFoundException {
        Post post0 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post0.getContent(), userInfo);
        Post post1 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post1.getContent(), userInfo);
        expectedException.expect(HasPublishedContents.PostNotFoundException.class);
        community.deletePublishedContent("Post #999");
    }

    @Test(timeout = 500)

    /**
     * test whether the delete method correctly set the post to be deleted as invisible
     * and whether the getNumberOfPosts method correctly reflect this delete.
     * @throws HasPublishedContents.PostNotFoundException if the post to be deleted does not exist.
     */
    public void testDelete() throws HasPublishedContents.PostNotFoundException {
        Post post0 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post0.getContent(), userInfo);
        Post post1 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post1.getContent(), userInfo);
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
        Post post1 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post1.getContent(), userInfo);
        Post post2 = new Post("abc", community.getNextId(), userInfo);
        community.addPublishedContent(post2.getContent(), userInfo);
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