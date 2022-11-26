package com.csds393;
import java.util.*;

import com.csds393.PostType;

import java.io.*;
import java.sql.*;

/* class that stores all posts */
public class Feed {

    private List<LiveAlertPost> alertFeed; //stores posts
    private List<Categorization> category; //stores categories to filter posts

    public Feed(){
        category = new ArrayList<Categorization>();
        category.add(Categorization.LIVE_ALERT);
        alertFeed = new ArrayList<LiveAlertPost>();
        // this.conn = conn;
        // try {
        //     Statement stmt = conn.createStatement();
        //     //clearUsersFromDatabase();
        //     ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.livealertpost");

        //     while(rs.next()) {
        //         String postTypeString = rs.getString("postType");
        //         PostType postType = PostType.DEFAULT;
        //         for(PostType pt : PostType.values()) {
        //             if (postTypeString.equals(pt.toString())) {
        //                 postType = pt;
        //             }
        //         }
                
        //         String locationString = rs.getString("location");
        //         Location location = Location.DEFAULT;
        //         for (Location l : Location.values()) {
        //             if(locationString.equals(l.toString())) {
        //                 location = l;
        //             }
        //         }
        //         alertFeed.add(new LiveAlertPost(postType, location, user));
        //     }

        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
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
    }

    public void setCategory(List<Categorization> c){
        category = c;
    }

    // public List<LiveAlertPost> filterFeed(List<Categorization> c) {
    //     ArrayList<LiveAlertPost> posts = new ArrayList<>();
    //     for(Categorization cat: c){
    //         for(LiveAlertPost post : alertFeed){
    //             if(post.getCategorizations().contains(cat)){
    //                 posts.add(post);
    //             }
    //         }
    //     }
    //     return posts;
    // }

    // public void clearUsersFromDatabase() {
    //     Statement stmt;
    //     try {
    //         stmt = conn.createStatement();
    //         stmt.executeUpdate("DELETE FROM just_in_case.user");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public boolean isEmpty() {
        if(this.alertFeed.size() <= 0) {
            return true;
        }
        return false;
    }

}