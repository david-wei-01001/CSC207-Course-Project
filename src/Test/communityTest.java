package Test;

    import Posts.Community;
    import Posts.HasPost;
    import Posts.Post;
    import org.junit.Before;
    import org.junit.Test;

    import static org.junit.Assert.*;

public class communityTest {
    Community community;

    @Before
    public void setUp() {
        community = new Community("SOSAD");
    }

    @Test(timeout = 50)
    public void testNextIdNoPost() {

        assertEquals(community.getNextId(), "Post #0");
    }

    @Test(timeout = 50)
    public void testAdd() {
        Post post1 = new Post("abc", community.getNextId());
        community.add(post1);
        Post post2 = new Post("abc", community.getNextId());
        community.add(post2);
        assertTrue(community.getMapOfPost().containsKey("Post #0"));
        assertTrue(community.getMapOfPost().containsKey("Post #1"));
        assertEquals(community.getMapOfPost().get("Post #0"), post1);
        assertEquals(community.getMapOfPost().get("Post #1"), post2);
    }

    @Test(timeout = 50)
    public void testNextId() {
        Post post1 = new Post("abc", community.getNextId());
        community.add(post1);
        Post post2 = new Post("abc", community.getNextId());
        community.add(post2);
        assertEquals(community.getNextId(), "Post #2");
    }

    @Test(timeout = 50)
    public void testGetNumPost() {
        Post post1 = new Post("abc", community.getNextId());
        community.add(post1);
        Post post2 = new Post("abc", community.getNextId());
        community.add(post2);
        assertEquals(community.getNumPosts(), 2);
    }

    @Test(timeout = 50)
    public void testDelete() throws HasPost.PostNotFoundException {
        Post post1 = new Post("abc", community.getNextId());
        community.add(post1);
        Post post2 = new Post("abc", community.getNextId());
        community.add(post2);
        community.delete("Post #1");
        assertTrue(community.getMapOfPost().containsKey("Post #0"));
        assertEquals(community.getMapOfPost().get("Post #0"), post1);
        assertEquals(community.getNumPosts(), 1);
    }

    @Test(timeout = 50)
    public void testDisplayPosts() {
        Post post1 = new Post("abc", community.getNextId());
        community.add(post1);
        Post post2 = new Post("abc", community.getNextId());
        community.add(post2);
        String str1 = post1 + "\n" + post2 + "\n";
        String str2 = post2 + "\n" + post1 + "\n";
        assertTrue(community.displayPosts().equals(str1) || community.displayPosts().equals(str2));
    }
}