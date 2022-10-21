import java.util.*;
import java.sql.Timestamp;
import static Just_in_Case.Location.BINGHAM;
import static org.junit.Assert.*;

import Just_in_Case.PostType;
import org.junit.Test;

public class LiveAlertPostTest{
    @Test
    public void testLiveAlertPost(){
        Just_in_Case.User user = new Just_in_Case.User("administrator", "Liyi", "lxc596");
        Just_in_Case.LiveAlertPost  testEXCESSIVE_RAIN = new Just_in_Case.LiveAlertPost(PostType.EXCESSIVE_RAIN, BINGHAM, user, null);
        assertEquals("WEATHER",testEXCESSIVE_RAIN.getCategorizations().iterator().next().toString());
        Just_in_Case.LiveAlertPost  testEXCESSIVE_SNOW = new Just_in_Case.LiveAlertPost(PostType.EXCESSIVE_SNOW, BINGHAM, user, null);
        assertEquals("WEATHER",testEXCESSIVE_SNOW.getCategorizations().iterator().next().toString());
        Just_in_Case.LiveAlertPost  testSUSPICIOUS_BEHAVIOR = new Just_in_Case.LiveAlertPost(PostType.SUSPICIOUS_BEHAVIOR, BINGHAM, user, null);
        assertEquals("SAFETY",testSUSPICIOUS_BEHAVIOR.getCategorizations().iterator().next().toString());
        Just_in_Case.LiveAlertPost  testCROWDED = new Just_in_Case.LiveAlertPost(PostType.CROWDED, BINGHAM, user, null);
        assertEquals("STUDY",testCROWDED.getCategorizations().iterator().next().toString());
        Just_in_Case.LiveAlertPost  testCLOSED = new Just_in_Case.LiveAlertPost(PostType.CLOSED, BINGHAM, user, null);
        assertEquals("STUDY",testCLOSED.getCategorizations().iterator().next().toString());
    }
}
