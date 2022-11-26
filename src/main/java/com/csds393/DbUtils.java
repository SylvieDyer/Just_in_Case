package com.csds393;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

import org.springframework.lang.Nullable;

public class DbUtils {
    Connection conn;
    public DbUtils() {
        try {
            openConnection();
            initDefaultUser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public List<LiveAlertPost> getFeed() {
        List<LiveAlertPost> alertFeed = new ArrayList<LiveAlertPost>();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.livealertpost");
            while(rs.next()) {
                String postTypeString = rs.getString("postType");
                PostType postType = PostType.DEFAULT;
                for(PostType pt : PostType.values()) {
                    if (postTypeString.equals(pt.toString())) {
                        postType = pt;
                    }
                }
                    
                String locationString = rs.getString("location");
                Location location = Location.DEFAULT;
                for (Location l : Location.values()) {
                    if(locationString.equals(l.toString())) {
                        location = l;
                    }
                }
                alertFeed.add(new LiveAlertPost(postType, location, rs.getInt("numUpvotes"), rs.getInt("numDownvotes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        
        return alertFeed;
    }

    public LiveAlertPost addLiveAlertPost(LiveAlertPost liveAlertPost, @Nullable User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "INSERT INTO just_in_case.livealertpost(postType, location, time, numUpvotes, numDownvotes)"
                + " VALUES ('" + liveAlertPost.getPostType() + "', '" + liveAlertPost.getLocation() + "', " 
                + "NOW()" + ", '" + liveAlertPost.getNumUpvotes() + "', '" 
                + liveAlertPost.getNumDownvotes() + "')", stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (user == null) {
                user = new User("DEFAULT", "DEFAULT", 0, 1);
            }
            if(rs.next()) {
                long postID = rs.getLong(1);
                stmt.executeUpdate("INSERT INTO just_in_case.posts(postID, caseID)"
                + " VALUES ('" + postID + "', '" + user.getCaseID() + "')");
                stmt.close();
                // TODO: fetch liveAlertPost from db by postId
                liveAlertPost.setPostID(postID);
                return liveAlertPost;
            } else {
                stmt.close();
                return null;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return null;
    }

    public boolean deleteLiveAlertPost(LiveAlertPost liveAlertPost) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.livealertpost WHERE just_in_case.livealertpost.postType = '" 
                         + liveAlertPost.getPostType() + "' AND just_in_case.livealertpost.location = '" 
                         + liveAlertPost.getLocation()+ "'");
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return true;
            }
            return false;            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return false;
    }

    private User initDefaultUser() {
        User user = new User("DEFAULT", "DEFAULT", 0, 1);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.user WHERE just_in_case.user.caseID = 'DEFAULT';");
            if(!rs.next()) {
                stmt.executeUpdate("INSERT INTO just_in_case.user(caseID, userName, postAnon, isAdmin)"
                + " VALUES ('" + user.getCaseID() + "', '" + user.getUserName() + "', '" 
                + user.getPostAnon() + "', '" + user.getIsAdmin() + "')");
            }
            stmt.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    private void openConnection() throws FileNotFoundException {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        // Scanner fr = new Scanner(new File("././untracked.txt"));
        // String PASS = fr.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, "Q92PftE262EThy7IH7rB");
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
