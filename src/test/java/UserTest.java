import java.util.*;
import org.junit.*;
import java.io.*;
import java.sql.*;
import static org.junit.Assert.*;

import com.csds393.User;

public class UserTest{
    User user = new User("Administrator", "Liyi", "testtest");
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
            stmt.executeUpdate("DELETE FROM just_in_case.user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        assertEquals(1,user.getPostAnon());
    }

    @Test
    public void testPostAnon_False() {
        user.setPostAnon(0);
        assertEquals(0,user.getPostAnon());
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
