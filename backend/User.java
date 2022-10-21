package backend;
import java.sql.*;
import java.io.*;
import java.util.*;

public class User {
    private String userType;
    private String name;
    private String caseID;
    private boolean postAnon;
    private Connection conn;

    public User(String userType, String name, String caseID){
        this.userType = userType;
        this.name = name;
        this.caseID = caseID;
        postAnon = true;

        boolean isAdmin = userType.equals("Administrator");

        try {
            openConnection();
            try(Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("INSERT INTO just_in_case.user VALUES ('" 
                + caseID + "', '" + name + "', '" + postAnon + "', '" + isAdmin + "')"); 
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }        
    }

    public void changePostSettings(boolean b) {
        postAnon = b;
    }

    public boolean isAdmin(){
        return userType.equals("Administrator");
    }
    
    public String getName(){
        return name;
    }

    public String getCaseID(){
        return caseID;
    }
    
    public boolean getPostAnon(){
        return postAnon;
    }

    public void setPostAnon(boolean anon) {
        postAnon = anon;
    }


    private void openConnection() throws FileNotFoundException {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        Scanner fr = new Scanner(new File("./untracked.txt"));
        String PASS = fr.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

