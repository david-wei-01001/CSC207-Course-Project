package Test;

import Posts.Comment;
import Posts.HasPost;
import Posts.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    private Comment createComment() {
        Comment c1 = new Comment("testing", "1");
        HashMap<String, Comment> testmap = new HashMap<>();
        testmap.put("1", c1);
        ps.add(c1);
        assertEquals(testmap,ps.getMapOfComments());
        assertEquals(1, ps.getNumComments());
        return c1;
    }

    @Test(timeout = 50)
    public void testdelete() throws HasPost.PostNotFoundException {
        Comment c = createComment();
        ps.delete("1");
        HashMap<String, Comment> testmap1 = new HashMap<>();
        testmap1.put("1", c);
        assertEquals(testmap1, ps.getMapOfComments());
        assertEquals(1, ps.getNumComments());
        assertTrue(!ps.getMapOfComments().get("1").visulableStatus());
    }
}


