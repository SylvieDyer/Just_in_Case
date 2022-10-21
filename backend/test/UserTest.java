import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
public class UserTest{
    @Test
    public void testInitialization(){
        Just_in_Case.User user = new Just_in_Case.User("administrator", "Liyi", "lxc596");
        assertTrue(user.getPostAnon());
        assertEquals("administrator", user.getUserType());
        assertEquals("Liyi", user.getName());
        assertEquals("lxc596", user.getCaseID());
    }
}
