import java.util.*;
import org.junit.*;
import java.io.*;
import java.sql.*;
import static org.junit.Assert.*;

import com.csds393.LiveAlertPost;
import com.csds393.Location;
import com.csds393.Categorization;
import com.csds393.Feed;
import com.csds393.PostType;
import com.csds393.User;


public class FeedTest {
    Connection conn;
    @Before
    public void initDatabase() {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        try (Scanner fr = new Scanner(new 
            File("./untracked.txt"))) {
            String PASS = fr.nextLine();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clearDatabase() {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.livealertpost");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPosts() {
        Feed liveAlertFeed = new Feed();
        PostType liveAlertPostType = PostType.EXCESSIVE_RAIN;
        Location location = Location.EUCLID_ADELBERT;
        User user = new User("User", "Parthana", "pjg83");
        LiveAlertPost liveAlertPost = new LiveAlertPost(liveAlertPostType, location, user);
        assertTrue(liveAlertFeed.addPost(liveAlertPost)); 
        PostType liveAlertPostType2 = PostType.EXCESSIVE_RAIN;
        Location location2 = Location.CUTTER;
        User user2 = new User("User", "Ana", "alp133");
        LiveAlertPost liveAlertPost2 = new LiveAlertPost(liveAlertPostType2, location2, user2);
        assertTrue(liveAlertFeed.addPost(liveAlertPost2)); 
        Feed liveAlertFeed2 = new Feed();
    }
    @Test
    public void testFiltering() {
        Feed liveAlertFeed = new Feed();
        PostType liveAlertPostType = PostType.EXCESSIVE_RAIN;
        Location location = Location.EUCLID_ADELBERT;
        User user = new User("User", "Parthana", "pjg83");
        LiveAlertPost liveAlertPost = new LiveAlertPost(liveAlertPostType, location, user);
        assertTrue(liveAlertFeed.addPost(liveAlertPost)); 
        PostType liveAlertPostType2 = PostType.EXCESSIVE_RAIN;
        Location location2 = Location.CUTTER;
        User user2 = new User("User", "Ana", "alp133");
        LiveAlertPost liveAlertPost2 = new LiveAlertPost(liveAlertPostType2, location2, user2);
        assertTrue(liveAlertFeed.addPost(liveAlertPost2)); 
        Feed liveAlertFeed2 = new Feed();
        List<Categorization> category = new LinkedList<>();
        category.add(Categorization.SAFETY);
        liveAlertFeed2.filterFeed(category);
    }
    @Test
    public void testGetFeed() {
        Feed liveAlertFeed = new Feed();
        PostType liveAlertPostType = PostType.EXCESSIVE_RAIN;
        Location location = Location.EUCLID_ADELBERT;
        User user = new User("User", "Parthana", "pjg83");
        LiveAlertPost liveAlertPost = new LiveAlertPost(liveAlertPostType, location, user);
        assertTrue(liveAlertFeed.addPost(liveAlertPost));
        ArrayList<LiveAlertPost> testFeed = new ArrayList<>(); 
        testFeed.add(liveAlertPost);
        assertEquals(testFeed, liveAlertFeed.getFeed());
    }




}

