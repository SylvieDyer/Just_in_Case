package com.csds393;
import java.sql.Timestamp;
import java.util.*;
import java.sql.*;
import java.io.*;

public class LiveAlertPost {
    /*class represents an alert post a user can make that will display on live feed */
    private long postID;
    private PostType postType;
    private Location location;
    //private HashSet<Categorization> categorizations = new HashSet<Categorization>();
    //private User user;
    private Timestamp date;
    private int numUpvotes;
    private int numDownvotes;

    public LiveAlertPost() {

    }

    //Constructor initializes a post with post type, location, user, and current datetime
    public LiveAlertPost(PostType postType, Location location, int numUpvotes, int numDownvotes) {
        this.postType = postType;
        this.location = location;
        this.date = new Timestamp(System.currentTimeMillis());
        this.numUpvotes = numUpvotes;
        this.numDownvotes = numDownvotes;
        //updateCategorization();   
    }

    public LiveAlertPost(PostType postType, Location location, Timestamp date, int numUpvotes, int numDownvotes) {
        this.postType = postType;
        this.location = location;
        this.date = date;
        this.numUpvotes = numUpvotes;
        this.numDownvotes = numDownvotes;
        //updateCategorization();   
    }

    public LiveAlertPost(long postID, PostType postType, Location location, Timestamp date, int numUpvotes, int numDownvotes) {
        this.postID = postID;
        this.postType = postType;
        this.location = location;
        this.date = date;
        //new Timestamp(System.currentTimeMillis());
        this.numUpvotes = numUpvotes;
        this.numDownvotes = numDownvotes;
        //updateCategorization();   
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LiveAlertPost ){
            LiveAlertPost p = (LiveAlertPost) obj;
            if (p.getPostType() == this.getPostType() && p.getLocation() == this.getLocation()){
                return true;
            }
        }
        return false;
    }


    public long getPostID() {
        return this.postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public PostType getPostType() {
        return this.postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getNumUpvotes() {
        return this.numUpvotes;
    }

    public void setNumUpvotes(int numUpvotes) {
        this.numUpvotes = numUpvotes;
    }

    public int getNumDownvotes() {
        return this.numDownvotes;
    }

    public void setNumDownvotes(int numDownvotes) {
        this.numDownvotes = numDownvotes;
    }


    // public HashSet<Categorization> getCategorizations(){
    //     return categorizations;
    // }



    // private void updateCategorization() {
    //     switch(postType){
    //         case EXCESSIVE_RAIN:
    //         case EXCESSIVE_SNOW:
    //             categorizations.add(Categorization.WEATHER);
    //             break;
    //         case SUSPICIOUS_BEHAVIOR:
    //             categorizations.add(Categorization.SAFETY);
    //             break;
    //         case CROWDED:
    //         case CLOSED:
    //             categorizations.add(Categorization.STUDY);
    //             break;
    //     }
    // }
}
