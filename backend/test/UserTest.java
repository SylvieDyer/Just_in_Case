import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import backend.User;

public class UserTest{
    @Test
    public void testInitialization(){
        User user = new User("administrator", "Liyi", "lxc596");
        assertTrue(user.getPostAnon());
        assertEquals("administrator", user.getUserType());
        assertEquals("Liyi", user.getName());
        assertEquals("lxc596", user.getCaseID());
    }
}
