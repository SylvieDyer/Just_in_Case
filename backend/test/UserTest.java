import static org.junit.Assert.*;
import org.junit.Test;
import backend.User;

public class UserTest{
    User user = new User("Administrator", "Liyi", "lxc596");

    @Test
    public void testIsAdmin_True(){
        assertTrue(user.isAdmin());
    }

    @Test
    public void testIsAdmin_False(){
        User user2 = new User("User", "Prarthana", "pjg83");
        assertFalse(user2.isAdmin());
    }

    @Test
    public void testPostAnon_True() {
        assertTrue(user.getPostAnon());
    }

    @Test
    public void testPostAnon_False() {
        user.setPostAnon(false);
        assertFalse(user.getPostAnon());
    }

    @Test
    public void testGetName() {
        assertEquals("Liyi", user.getName());
    }
       
    @Test
    public void testGetCaseID() {
        assertEquals("lxc596", user.getCaseID());
    }
}
