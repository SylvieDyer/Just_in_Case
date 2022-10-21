import java.util.*;
import org.junit.*;
import java.io.*;
import java.sql.*;
import static org.junit.Assert.*;

import backend.LiveAlertPost;
import backend.User;
import backend.PostType;
import static backend.Location.BINGHAM;

public class LiveAlertPostTest{
    User user = new User("Administrator", "Liyi", "lxc596");
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
    public void testGetPostType(){
        LiveAlertPost testEXCESSIVE_RAIN = new LiveAlertPost(PostType.EXCESSIVE_RAIN, BINGHAM, user);
        assertEquals(PostType.EXCESSIVE_RAIN, testEXCESSIVE_RAIN.getPostType());
    }

    @Test
    public void testGetLocation(){
        LiveAlertPost testEXCESSIVE_RAIN = new LiveAlertPost(PostType.EXCESSIVE_RAIN, BINGHAM, user);
        assertEquals(BINGHAM, testEXCESSIVE_RAIN.getLocation());
    }

    @Test
    public void testGetUser(){
        LiveAlertPost testEXCESSIVE_RAIN = new LiveAlertPost(PostType.EXCESSIVE_RAIN, BINGHAM, user);
        assertEquals(user, testEXCESSIVE_RAIN.getUser());
    }

    @Test
    public void testLiveAlertPostCategorization_Rain(){
        LiveAlertPost testEXCESSIVE_RAIN = new LiveAlertPost(PostType.EXCESSIVE_RAIN, BINGHAM, user);
        assertEquals("WEATHER",testEXCESSIVE_RAIN.getCategorizations().iterator().next().toString());
    }
    @Test
    public void testLiveAlertPostCategorization_Snow(){
        LiveAlertPost testEXCESSIVE_SNOW = new LiveAlertPost(PostType.EXCESSIVE_SNOW, BINGHAM, user);
        assertEquals("WEATHER",testEXCESSIVE_SNOW.getCategorizations().iterator().next().toString());
    }

    @Test
    public void testLiveAlertPostCategorization_Suspicious(){
        LiveAlertPost testSUSPICIOUS_BEHAVIOR = new LiveAlertPost(PostType.SUSPICIOUS_BEHAVIOR, BINGHAM, user);
        assertEquals("SAFETY",testSUSPICIOUS_BEHAVIOR.getCategorizations().iterator().next().toString());
    }

    @Test
    public void testLiveAlertPostCategorization_Crowded(){
        LiveAlertPost  testCROWDED = new LiveAlertPost(PostType.CROWDED, BINGHAM, user);
        assertEquals("STUDY",testCROWDED.getCategorizations().iterator().next().toString());
    }

    @Test
    public void testLiveAlertPostCategorization_Closed(){
        LiveAlertPost  testCLOSED = new LiveAlertPost(PostType.CLOSED, BINGHAM, user);
        assertEquals("STUDY",testCLOSED.getCategorizations().iterator().next().toString());
    }        
        
}
