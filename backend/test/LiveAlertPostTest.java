import java.util.*;
import java.sql.Timestamp;
import static backend.Location.BINGHAM;
import static org.junit.Assert.*;
import org.junit.Test;
import backend.LiveAlertPost;
import backend.User;
import backend.PostType;

public class LiveAlertPostTest{
    @Test
    public void testLiveAlertPost(){
        User user = new User("administrator", "Liyi", "lxc596");
        LiveAlertPost  testEXCESSIVE_RAIN = new LiveAlertPost(PostType.EXCESSIVE_RAIN, BINGHAM, user);
        assertEquals("WEATHER",testEXCESSIVE_RAIN.getCategorizations().iterator().next().toString());
        LiveAlertPost  testEXCESSIVE_SNOW = new LiveAlertPost(PostType.EXCESSIVE_SNOW, BINGHAM, user);
        assertEquals("WEATHER",testEXCESSIVE_SNOW.getCategorizations().iterator().next().toString());
        LiveAlertPost  testSUSPICIOUS_BEHAVIOR = new LiveAlertPost(PostType.SUSPICIOUS_BEHAVIOR, BINGHAM, user);
        assertEquals("SAFETY",testSUSPICIOUS_BEHAVIOR.getCategorizations().iterator().next().toString());
        LiveAlertPost  testCROWDED = new LiveAlertPost(PostType.CROWDED, BINGHAM, user);
        assertEquals("STUDY",testCROWDED.getCategorizations().iterator().next().toString());
        LiveAlertPost  testCLOSED = new LiveAlertPost(PostType.CLOSED, BINGHAM, user);
        assertEquals("STUDY",testCLOSED.getCategorizations().iterator().next().toString());
    }
}
