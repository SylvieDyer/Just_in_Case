package com.csds393;
import java.util.*;

import com.csds393.PostType;

import java.io.*;
import java.sql.*;

/* class that stores all posts */
public class Feed {

    private List<LiveAlertPost> alertFeed; //stores posts
    private List<Categorization> category; //stores categories to filter posts
    private Connection conn;

    public Feed(){
        category = new ArrayList<Categorization>();
        category.add(Categorization.LIVE_ALERT);
        alertFeed = new ArrayList<LiveAlertPost>();

        try {
            openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.livealertpost");
            clearUsersFromDatabase();
            User user = new User("User", "User", "user");

            while(rs.next()) {
                String postTypeString = rs.getString("postType");
                PostType postType = PostType.DEFAULT;
                for(PostType pt : PostType.values()) {
                    if (postTypeString == pt.toString()) {
                        postType = pt;
                    }
                }
                
                String locationString = rs.getString("location");
                Location location = Location.DEFAULT;
                for (Location l : Location.values()) {
                    if(locationString == l.toString()) {
                        location = l;
                    }
                }
                System.out.println(location.toString());
                alertFeed.add(new LiveAlertPost(postType, location, user));
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public ArrayList<LiveAlertPost> parseFeed() {
    //     return null;
    // }

    public List<LiveAlertPost> getFeed() {
        return alertFeed;
    }

    public boolean addPost(LiveAlertPost p){
        for (LiveAlertPost post : alertFeed){
            if (post.equals(p))
                return false;
        }
        return alertFeed.add(p);
        /* I'm sorry I'm not entirely sure how yall wanna do this */
    }

    public void setCategory(List<Categorization> c){
        category = c;
    }

    public List<LiveAlertPost> filterFeed(List<Categorization> c) {
        ArrayList<LiveAlertPost> posts = new ArrayList<>();
        for(Categorization cat: c){
            for(LiveAlertPost post : alertFeed){
                if(post.getCategorizations().contains(cat)){
                    posts.add(post);
                }
            }
        }
        return posts;
    }

    private void openConnection() throws FileNotFoundException {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        Scanner fr = new Scanner(new File("././untracked.txt"));
        String PASS = fr.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearUsersFromDatabase() {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}