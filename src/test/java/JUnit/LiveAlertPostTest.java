import java.util.*;
import java.sql.Timestamp;
import com.csds393.Location;
import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;
import com.csds393.LiveAlertPost;
import com.csds393.PostType;

public class LiveAlertPostTest {
    @Test
    public void testLiveAlertPost_noParam(){
        LiveAlertPost post = new LiveAlertPost();
        assertEquals(0,post.getPostID());
        assertNull(post.getPostType());
        assertNull(post.getLocation());
        assertNull(post.getDate());
        assertEquals(0,post.getNumDownvotes());
        assertEquals(0,post.getNumUpvotes());
    }

    @Test
    public void testLiveAlertPost_fourParam(){
        LiveAlertPost post = new LiveAlertPost(PostType.CLOSED, Location.ADELBERT_GYM,1,2);
        assertEquals(PostType.CLOSED, post.getPostType());
        assertEquals(Location.ADELBERT_GYM,post.getLocation());
        assertEquals(new Timestamp(System.currentTimeMillis()),post.getDate());
        assertEquals(1,post.getNumUpvotes());
        assertEquals(2,post.getNumDownvotes());
    }

    @Test
    public void testLiveAlertPost_fiveParam(){
        LiveAlertPost post = new LiveAlertPost(PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(PostType.CLOSED, post.getPostType());
        assertEquals(Location.ADELBERT_GYM,post.getLocation());
        assertEquals(new Timestamp(System.currentTimeMillis()),post.getDate());
        assertEquals(1,post.getNumUpvotes());
        assertEquals(2,post.getNumDownvotes());
    }

    @Test
    public void testLiveAlertPost_sixParam(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(100,post.getPostID());
        assertEquals(PostType.CLOSED, post.getPostType());
        assertEquals(Location.ADELBERT_GYM,post.getLocation());
        assertEquals(new Timestamp(System.currentTimeMillis()),post.getDate());
        assertEquals(1,post.getNumUpvotes());
        assertEquals(2,post.getNumDownvotes());
    }

    @Test
    public void testEquals(){
        LiveAlertPost post1 = new LiveAlertPost(PostType.CLOSED, Location.ADELBERT_GYM,1,2);
        LiveAlertPost post2 = new LiveAlertPost(PostType.CLOSED, Location.ADELBERT_GYM,3,4);
        LiveAlertPost post3 = new LiveAlertPost(PostType.CROWDED, Location.ADELBERT_GYM,1,2);
        LiveAlertPost post4 = new LiveAlertPost(PostType.CLOSED, Location.ALUMNI,1,2);
        assertTrue(post1.equals(post2));
        assertFalse(post1.equals(post3));
        assertFalse(post1.equals(post4));
    }

    @Test
    public void testGetPostID(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(100,post.getPostID());
    }

    @Test
    public void testGetPostType(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(PostType.CLOSED, post.getPostType());
    }

    @Test
    public void testGetLocation(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(Location.ADELBERT_GYM,post.getLocation());
    }

    @Test
    public void testGetDate(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(new Timestamp(System.currentTimeMillis()),post.getDate());
    }

    @Test
    public void testGetNumUpvotes(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(1,post.getNumUpvotes());
    }

    @Test
    public void testGetNumDownvotes(){
        LiveAlertPost post = new LiveAlertPost(100,PostType.CLOSED, Location.ADELBERT_GYM,new Timestamp(System.currentTimeMillis()),1,2);
        assertEquals(2,post.getNumDownvotes());
    }

    @Test
    public void testSetPostID(){
        LiveAlertPost post = new LiveAlertPost();
        post.setPostID(100);
        assertEquals(100,post.getPostID());
    }

    @Test
    public void testSetPostType(){
        LiveAlertPost post = new LiveAlertPost();
        post.setPostType(PostType.CLOSED);
        assertEquals(PostType.CLOSED, post.getPostType());
    }

    @Test
    public void testSetLocation(){
        LiveAlertPost post = new LiveAlertPost();
        post.setLocation(Location.ADELBERT_GYM);
        assertEquals(Location.ADELBERT_GYM,post.getLocation());
    }

    @Test
    public void testSetDate(){
        LiveAlertPost post = new LiveAlertPost();
        post.setDate(new Timestamp(System.currentTimeMillis()));
        assertEquals(new Timestamp(System.currentTimeMillis()),post.getDate());
    }

    @Test
    public void testSetNumUpvotes(){
        LiveAlertPost post = new LiveAlertPost();
        post.setNumUpvotes(1);
        assertEquals(1,post.getNumUpvotes());
    }

    @Test
    public void testSetNumDownvotes(){
        LiveAlertPost post = new LiveAlertPost();
        post.setNumDownvotes(2);
        assertEquals(2,post.getNumDownvotes());
    }

}
