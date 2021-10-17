package Test;

import Posts.Comment;
import Posts.HasPost;
import Posts.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class postTest {
    Post ps;

    @Before
    public void setUp() {
        ps = new Post("nothing", "0");
    }

    @Test(timeout = 50)
    public void testAdd() {
        createComment();
    }

    private void createComment() {
        Comment c1 = new Comment("testing", "1");
        HashMap<String, Comment> testmap = new HashMap<>();
        testmap.put("1", c1);
        ps.add(c1);
        assertEquals(testmap,ps.getMapOfComments());
        assertEquals(1, ps.getNumComments());
    }

    @Test(timeout = 50)
    public void testdelete() throws HasPost.PostNotFoundException {
        createComment();
        ps.delete("1");
        HashMap<String, Comment> testmap1 = new HashMap<>();
        assertEquals(testmap1, ps.getMapOfComments());
        assertEquals(0, ps.getNumComments());
    }
}


