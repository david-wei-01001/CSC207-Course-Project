package Test;

import Posts.Comment;
import Posts.HasPublishedContents;
import Posts.Post;
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
    UserInfo userInfo;

    @Before
    public void setUp() {
        userInfo = new UserInfo("Tong", "123@mail.com", "123");
        post = new Post("nothing", "0", userInfo);
        post.addPublishedContent("testing", userInfo);
    }

    @After
    public void tearDown() {

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddCommentSuccessfulAndGetMapOfContents() {
        Comment c1 = new Comment("testing", "Comment #0", userInfo);

        HashMap<String, Comment> mapToCompare = new HashMap<>();

        mapToCompare.put("Comment #0", c1);

        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());

        assertEquals(1, post.getNumberOfComments());

        assertTrue(post.getMapOfComments().get("Comment #0").visibility());
    }

    /**
     * TODO: How can this be unsuccessful? In the method, a comment can always be added.
     */
    @Test
    public void testAddCommentUnsuccessful() {}

    @Test
    public void testDeleteCommentUnsuccessful() throws HasPublishedContents.PostNotFoundException {
        expectedException.expect(HasPublishedContents.PostNotFoundException.class);
        post.deletePublishedContent("Comment #100");
    }

    /**
     *
     * @throws HasPublishedContents.PostNotFoundException
     */
    @Test
    public void testDeleteAndNumberOfComments() throws HasPublishedContents.PostNotFoundException {
        post.deletePublishedContent("Comment #0");

        Comment c1 = new Comment("testing", "Comment #0", userInfo);

        HashMap<String, Comment> mapToCompare = new HashMap<>();

        mapToCompare.put("Comment #0", c1);

        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());

        assertEquals(1, post.getNumberOfComments());

        assertTrue(!post.getMapOfComments().get("Comment #0").visibility());
    }

//    /**
//     * TODO: @Alfred choose one from the previous one or this one.
//     * This test contains a mistake
//     * @throws HasPublishedContents.PostNotFoundException
//     */
//    @Test
//    public void testDeleteComment() throws HasPublishedContents.PostNotFoundException {
//        Comment c = new Comment("testing", "Comment #0", userInfo);
//
//        post.deletePublishedContent("Comment #0");
//
//        HashMap<String, Comment> mapToCompare = new HashMap<>();
//
//        mapToCompare.put("Comment #0", c);
//
//        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());
//
//        assertEquals(0, post.getNumberOfComments());
//
//        assertFalse(post.getMapOfComments().get("Comment #0").visibility());
//    }

    @Test
    public void testGetNextIdNoDelete() {
        post.addPublishedContent("debugging", userInfo);
        assertEquals("Comment #2", post.getNextId());
    }

    @Test
    public void testGetNextIdWithDelete() throws HasPublishedContents.PostNotFoundException {
        post.addPublishedContent("debugging", userInfo);
        post.deletePublishedContent("Comment #0");
        assertEquals("Comment #2", post.getNextId());
    }

    @Test
    public void testGetContent() {
        assertEquals("nothing", post.getContent());
    }
}


