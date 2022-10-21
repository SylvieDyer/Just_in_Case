import java.util.*;
import java.sql.Timestamp;
import static backend.Location.BINGHAM;
import static org.junit.Assert.*;
import org.junit.Test;
import backend.LiveAlertPost;
import backend.User;
import backend.PostType;

public class LiveAlertPostTest{
    User user = new User("Administrator", "Liyi", "lxc596");

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
