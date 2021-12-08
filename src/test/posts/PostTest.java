package posts;

import org.junit.Before;
import org.junit.Test;
import user.User;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test the Post class
 */
public class PostTest {
    Post post;
    User user;

    /**
     * The setup method that setup each test.
     * It creates a post and a user.
     */
    @Before
    public void setUp() {
        user = new User("Tong", "123@mail.com", "123");
        post = new Post("nothing", "0", user);
        post.addPublishedContent("testing", user);
        post.addPublishedContent("aa", user);
    }

    /**
     * Test if the getMapOfComments method successfully retrieve a comment
     * and the getMapOfComments method functions as intended
     */
    @Test
    public void testAddCommentSuccessfulAndGetMapOfContents() {
        Comment c1 = new Comment("testing", "Comment #0", user);
        Comment c2 = new Comment("aa", "Comment #1", user);

        Map<String, Comment> mapToCompare = new HashMap<>();

        mapToCompare.put("Comment #0", c1);
        mapToCompare.put("Comment #1", c2);

        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());

        assertEquals(2, post.getNumberOfComments());

        assertTrue(post.getMapOfComments().get("Comment #0").visibility());
    }

    /**
     * Test if the deletePublishedContent correctly throw an exception
     */
    @Test
    public void testDeleteCommentUnsuccessful() {
        try {
            post.deletePublishedContent("Comment #100");
            fail();
        } catch (HasPublishedContents.PostNotFoundException ignored) {
        }
    }

    /**
     * Test if the deletePublishedContent and getNumberOfComments methods function as intended
     */
    @Test
    public void testDeleteAndNumberOfComments() {
        try {
            post.deletePublishedContent("Comment #0");
        } catch (HasPublishedContents.PostNotFoundException e) {
            fail();
        }

        Comment c1 = new Comment("testing", "Comment #0", user);
        Comment c2 = new Comment("aa", "Comment #1", user);

        Map<String, Comment> mapToCompare = new HashMap<>();

        mapToCompare.put("Comment #0", c1);
        mapToCompare.put("Comment #1", c2);

        assertEquals(mapToCompare.toString(), post.getMapOfComments().toString());

        assertEquals(2, post.getNumberOfComments());

        assertFalse(post.getMapOfComments().get("Comment #0").visibility());
    }

    /**
     * Test if the getNextId methods function as intended when there was no deletion
     */
    @Test
    public void testGetNextIdNoDelete() {
        post.addPublishedContent("debugging", user);
        assertEquals("Comment #3", post.getNextId());
    }

    /**
     * Test if the getNextId methods function as intended when there was deletion
     */
    @Test
    public void testGetNextIdWithDelete() throws HasPublishedContents.PostNotFoundException {
        post.addPublishedContent("debugging", user);
        post.deletePublishedContent("Comment #0");
        assertEquals("Comment #3", post.getNextId());
    }

    /**
     * Test if the getContent methods function as intended
     */
    @Test
    public void testGetContent() {
        assertEquals("nothing", post.getContent());
    }

    /**
     * test if the post is properly converted to string format
     */
    @Test
    public void testToString() {
        Comment comment0 = post.getMapOfComments().get("Comment #0");
        Comment comment1 = post.getMapOfComments().get("Comment #1");
        assertEquals("Post" + '\n' +
                        "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                        + '\n'
                        + "id=" + post.getId()
                        + ", creator=" + post.getCreator().getName()
                        + '\n'
                        + "------------------------------------------------------------------------------------------" +
                        "------------"
                        + '\n'
                        + post.getContent()
                        + '\n'
                        + "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +
                        "::::::::::::"
                        + '\n'
                        + "Comments"
                        + '\n'
                        + "----------------------------------------------------------------------------------------" +
                        "--------------"
                        + '\n'
                        + comment0.getId()
                        + '='
                        + '\n'
                        + comment0.getCreator().getName()
                        + '\n'
                        + comment0.getContent()
                        + '\n'
                        + "----------------------------------------------------------------------------------------" +
                        "--------------"
                        + '\n'
                        + comment1.getId()
                        + '='
                        + '\n'
                        + comment1.getCreator().getName()
                        + '\n'
                        + comment1.getContent()
                        + '\n'
                        + "----------------------------------------------------------------------------------------" +
                        "--------------"
                        + '\n'
                        + "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +
                        "::::::::::::"


                , post.toString());
    }
}


