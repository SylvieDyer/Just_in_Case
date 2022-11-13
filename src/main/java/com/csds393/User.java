package com.csds393;
import java.sql.*;
import java.io.*;
import java.util.*;

public class User {
    private String userType;
    private String name;
    private String caseID;
    private int postAnon;
    private Connection conn;

    public User(String userType, String name, String caseID){
        this.userType = userType;
        this.name = name;
        this.caseID = caseID;
        postAnon = 1;

        int isAdmin = 0;
        if (userType.equals("Administrator")) {
            isAdmin = 1;
        }

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

    public void changePostSettings(int b) {
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
    
    public int getPostAnon(){
        return postAnon;
    }

    public void setPostAnon(int anon) {
        postAnon = anon;
    }


    private void openConnection() throws FileNotFoundException {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        Scanner fr = new Scanner(new File(("./././untracked.txt")));
        String PASS = fr.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

