package com.csds393;
import java.sql.Timestamp;
import java.util.*;
import java.sql.*;
import java.io.*;

public class LiveAlertPost {
    /*class represents an alert post a user can make that will display on live feed */
    private PostType postType;
    private Location location;
    private HashSet<Categorization> categorizations = new HashSet<Categorization>();
    private User user;
    private Timestamp date;
    private Connection conn;

    //Constructor initializes a post with post type, location, user, and current datetime
    public LiveAlertPost(PostType postType, Location location, User user) {
        this.postType = postType;
        this.location = location;
        this.user = user;
        this.date = new Timestamp(System.currentTimeMillis());
        updateCategorization();

        try {
            openConnection();
            try(Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(
                    "INSERT INTO just_in_case.livealertpost(postType, location, time, numUpvotes, numDownvotes)"
                    + " VALUES ('" + postType + "', '" + location + "', '" + date + "', '" + 0 + "', '" + 0 + "')"); 
                // stmt.executeUpdate(
                //     "INSERT INTO just_in_case.posts"
                //     + " VALUES ('" +  + "', '" + user.getCaseID()); 
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }        
    }

    private void openConnection() throws FileNotFoundException {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        Scanner fr = new Scanner(new File("../untracked.txt"));
        String PASS = fr.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCategorization() {
        switch(postType){
            case EXCESSIVE_RAIN:
            case EXCESSIVE_SNOW:
                categorizations.add(Categorization.WEATHER);
                break;
            case SUSPICIOUS_BEHAVIOR:
                categorizations.add(Categorization.SAFETY);
                break;
            case CROWDED:
            case CLOSED:
                categorizations.add(Categorization.STUDY);
                break;
        }
    }
    
    public HashSet<Categorization> getCategorizations(){
        return categorizations;
    }

    public PostType getPostType(){
        return postType;
    }

    public Location getLocation(){
        return location;
    }

    public User getUser(){
        return user;
    }

    public Timestamp getDate(){
        return date;
    }
}
