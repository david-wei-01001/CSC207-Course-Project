package Test;

import Posts.Comment;
import Posts.HasPublishedContents;
import Posts.Post;
import User.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.After;
import User.UserInfo;
import org.junit.rules.ExpectedException;

import java.util.HashMap;

import static org.junit.Assert.*;

public class postTest {
    Post post;
    User user;

    @Before
    public void setUp() {
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        user = new User(userInfo);
        post = new Post("nothing", "0", user);
        post.addPublishedContent("testing", user);
    }

    @After
    public void tearDown() {

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(timeout = 500)
    public void testAddCommentSuccessful() {
        Comment c1 = new Comment("testing", "Comment #0", user);

        HashMap<String, Comment> mapToCompare = new HashMap<>();

        mapToCompare.put("Comment #0", c1);

        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());

        assertEquals(1, post.getNumberOfComments());
    }

    /**
     * TODO: @Alfred choose one from the previous one or this one.
     */
    @Test(timeout = 500)
    public void testAddAndNumberOfComments() {
        assertTrue(post.getMapOfComments().containsKey("Comment #0"));
        assertEquals("testing", post.getMapOfComments().get("Comment #0").getContent());
        assertEquals(1, post.getNumberOfComments());

    }

    /**
     * TODO: How can this be unsuccessful? In the method, a comment can always be added.
     */
    @Test(timeout = 500)
    public void testAddCommentUnsuccessful() {}

    @Test(timeout = 500)
    public void testDeleteCommentUnsuccessful() throws HasPublishedContents.PostNotFoundException {
        expectedException.expect(HasPublishedContents.PostNotFoundException.class);
        post.deletePublishedContent("Comment #100");
    }

    @Test(timeout = 500)
    public void testGetMapOfComments() {
        HashMap<String, Comment> thisMap = post.getMapOfComments();
        assertTrue(thisMap.containsKey("Comment #0"));
        Comment thisComment = thisMap.get("Comment #0");
        assertEquals("testing", thisComment.getContent());
    }

    /**
     * This test contains a mistake
     * @throws HasPublishedContents.PostNotFoundException
     */
    @Test(timeout = 500)
    public void testdeleteAndNumberOfComments() throws HasPublishedContents.PostNotFoundException {
        post.deletePublishedContent("Comment #0");
        assertTrue(post.getMapOfComments().containsKey("Comment #0"));
        assertEquals("testing", post.getMapOfComments().get("Comment #0").getContent());
        assertEquals(1, post.getNumberOfComments());
//        TODO： what is the purpose of the following line?
//        assertTrue(!post.getMapOfComments().get("1").visulableStatus());
    }

    /**
     * TODO: @Alfred choose one from the previous one or this one.
     * This test contains a mistake
     * @throws HasPublishedContents.PostNotFoundException
     */
    @Test(timeout = 500)
    public void testDeleteComment() throws HasPublishedContents.PostNotFoundException {
//        Comment c = new Comment("testing", "Comment #0", user);
//
//        post.delete("Comment #0");
//
//        HashMap<String, Comment> mapToCompare = new HashMap<>();
//
//        mapToCompare.put("Comment #0", c);
//
//        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());
//
//        assertEquals(0, post.getNumberOfComments());
//
//        assertFalse(post.getMapOfComments().get("Comment #0").visulableStatus());
    }

    @Test(timeout = 500)
    public void testgetNextIdNoDelete() {
        post.addPublishedContent("debugging",user);
        assertEquals("Comment #2", post.getNextId());
    }

    @Test(timeout = 500)
    public void testgetNextIdWithDelete() throws HasPublishedContents.PostNotFoundException {
        post.addPublishedContent("debugging", user);
        post.deletePublishedContent("Comment #0");
        assertEquals("Comment #2", post.getNextId());
    }

    @Test(timeout = 500)
    public void testgetContent() {
        assertEquals("nothing", post.getContent());
    }
}


