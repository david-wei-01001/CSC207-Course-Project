package Test;

import Posts.Comment;
import Posts.HasPost;
import Posts.Post;
import User.User;
import org.junit.Before;
import org.junit.Test;
import User.UserInfo;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class postTest {
    Post ps;
    User user;

    @Before
    public void setUp() {
        UserInfo userInfo = new UserInfo("Tong", "123@mail.com", "123");
        user = new User(userInfo);
        ps = new Post("nothing", "0",user);
        ps.add("testing",user);
    }

    @Test(timeout = 500)
    public void testAdd() {
        Comment c1 = new Comment("testing", "Comment #0",user);
        HashMap<String, Comment> testmap = new HashMap<>();
        testmap.put("Comment #0", c1);
        assertEquals(testmap,ps.getMapOfComments());
        assertEquals(1, ps.getNumberOfComments());
    }


    @Test(timeout = 500)
    public void testdelete() throws HasPost.PostNotFoundException {
        Comment c = new Comment("testing", "Comment #0",user);
        ps.delete("Comment #0");
        HashMap<String, Comment> testmap1 = new HashMap<>();
        testmap1.put("Comment #0", c);
        assertEquals(testmap1, ps.getMapOfComments());
        assertEquals(0, ps.getNumberOfComments());
        assertTrue(!ps.getMapOfComments().get("1").visulableStatus());
    }

    @Test(timeout = 500)
    public void testgetNextIdNoDelete() throws HasPost.PostNotFoundException {
        ps.add("debugging",user);
        assertEquals("Comment #2", ps.getNextId());
    }

    @Test(timeout = 500)
    public void testgetNextIdWithDelete() throws HasPost.PostNotFoundException {
        ps.add("debugging",user);
        ps.delete("Comment #0");
        assertEquals("Comment #2", ps.getNextId());
    }

    @Test(timeout = 500)
    public void testgetContent() throws HasPost.PostNotFoundException {
        assertEquals("nothing", ps.getContent());
    }
}


