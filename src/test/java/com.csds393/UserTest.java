import java.util.*;
import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;
import com.csds393.User;

public class UserTest {
    @Test
    public void testUser(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        assertEquals("lxc596",user.getCaseID());
        assertEquals("Liyi",user.getUserName());
        assertEquals(1,user.getIsAdmin());
        assertEquals(1,user.getPostAnon());
        assertEquals("abc123",user.getPassword());
    }

    @Test
    public void testGetCaseID(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        assertEquals("lxc596",user.getCaseID());
    }

    @Test
    public void testGetUserName(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        assertEquals("Liyi",user.getUserName());
    }

    @Test
    public void testGetIsAdmin(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        assertEquals(1,user.getIsAdmin());
    }

    @Test
    public void testGetPostAnon(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        assertEquals(1,user.getPostAnon());
    }

    @Test
    public void testGetPassword(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        assertEquals("abc123",user.getPassword());
    }

    @Test
    public void testSetCaseID(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        user.setCaseID("aaa111");
        assertEquals("aaa111",user.getCaseID());
    }

    @Test
    public void testSetUserName(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        user.setUserName("Chen");
        assertEquals("Chen",user.getUserName());
    }

    @Test
    public void testSetIsAdmin(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        user.setIsAdmin(0);
        assertEquals(0,user.getIsAdmin());
    }

    @Test
    public void testSetPostAnon(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        user.setPostAnon(0);
        assertEquals(0,user.getPostAnon());
    }

    @Test
    public void testSetPassword(){
        User user = new User("lxc596","Liyi",1,1,"abc123");
        user.setPassword("bbb@2222");
        assertEquals("bbb@2222",user.getPassword());
    }
}
