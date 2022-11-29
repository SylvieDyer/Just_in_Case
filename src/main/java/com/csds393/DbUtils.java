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

    public DbUtils(Connection conn) {
        this.conn = conn;
        initDefaultUser();
    }

    private LiveAlertPost getLiveAlertPostFromResultSet(ResultSet rs) {
        String postTypeString;
        try {
            postTypeString = rs.getString("postType");
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
            return new LiveAlertPost(rs.getLong("postID"), postType, location, 
                rs.getTimestamp("time"), rs.getInt("numUpvotes"), 
                rs.getInt("numDownvotes"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private Building getBuildingFromResultSet(ResultSet rs) {
        try {
            return new Building(rs.getLong("buildingID"), rs.getString("buildingName"),  
            rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Facility getFacilityFromResultSet(ResultSet rs) {
        try {
            String statusString =  rs.getString("status");
            Status status = Status.NOT_BUSY;
            for(Status s : Status.values()) {
                if(statusString.equals(s.toString())) {
                    status = s;
                }
            } 
            return new Facility(rs.getLong("facilityID"), rs.getString("facilityName"),  
                status, rs.getTimestamp("statusLastUpdated"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private User getUserFromResultSet(ResultSet rs) {
        try {
            return new User(rs.getString("caseID"), rs.getString("userName"),  
            rs.getInt("postAnon"), rs.getInt("isAdmin"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveAlertPost getPost(long postID) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ postID + "'");
            if(rs.next()) {
                return getLiveAlertPostFromResultSet(rs);
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

    public List<LiveAlertPost> getFeed() {
        List<LiveAlertPost> alertFeed = new ArrayList<LiveAlertPost>();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.livealertpost");
            while(rs.next()) {
                //TODO: Null handling
                alertFeed.add(getLiveAlertPostFromResultSet(rs));
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
                user = new User("DEFAULT", "DEFAULT", 0, 1, "pass");
            }
            if(rs.next()) {
                long postID = rs.getLong(1);
                stmt.executeUpdate("INSERT INTO just_in_case.posts(postID, caseID)"
                + " VALUES ('" + postID + "', '" + user.getCaseID() + "')");

                ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ postID + "'");
                if(insertedResultSet.next()) {
                    return getLiveAlertPostFromResultSet(insertedResultSet);
                }                
                conn.close();
                return null;
            } else {
                conn.close();
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

    public LiveAlertPost addLiveAlertPostWithID(LiveAlertPost liveAlertPost, String caseID) {
        try {
            Statement stmt = conn.createStatement();
            User user = getUserByID(caseID);
            stmt.executeUpdate(
                "INSERT INTO just_in_case.livealertpost(postType, location, time, numUpvotes, numDownvotes)"
                + " VALUES ('" + liveAlertPost.getPostType() + "', '" + liveAlertPost.getLocation() + "', " 
                + "NOW()" + ", '" + liveAlertPost.getNumUpvotes() + "', '" 
                + liveAlertPost.getNumDownvotes() + "')", stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()) {
                long postID = rs.getLong(1);
                stmt.executeUpdate("INSERT INTO just_in_case.posts(postID, caseID)"
                + " VALUES ('" + postID + "', '" + user.getCaseID() + "')");

                ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ postID + "'");
                if(insertedResultSet.next()) {
                    return getLiveAlertPostFromResultSet(insertedResultSet);
                }                
                conn.close();
                return null;
            } else {
                conn.close();
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

    /** Returns id of deleted post. If no post deleted successfully, returns -1. */
    public long deleteLiveAlertPost(LiveAlertPost liveAlertPost) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.livealertpost WHERE just_in_case.livealertpost.postID = '" 
                         + liveAlertPost.getPostID() + "'", stmt.RETURN_GENERATED_KEYS);
            ResultSet deletedResultSet = stmt.executeQuery(
                "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ liveAlertPost.getPostID() + "'");
            if(deletedResultSet.next()) {
                conn.close();
                return -1;
            }
            conn.close();
            return liveAlertPost.getPostID();            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return -1;
    }

    public Building getBuildingByID(long buildingID) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.building WHERE buildingID='"+ buildingID + "'");
            if(rs.next()) {
                return getBuildingFromResultSet(rs);
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

    /* Returns the FIRST building with given name */
    public Building getBuildingByName(String buildingName) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.building WHERE buildingName='"+ buildingName + "'");
            if(rs.next()) {
                return getBuildingFromResultSet(rs);
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

    public List<Building> getBuildingHub() {    
        List<Building> buildingHub = new ArrayList<Building>();   
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.building");
            while(rs.next()) {
                buildingHub.add(getBuildingFromResultSet(rs));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }

        return buildingHub;
        
    }

    public Building addBuilding(Building building, @Nullable List<Facility> facilities) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "INSERT INTO just_in_case.building(buildingName, description)"
                + " VALUES ('" + building.getBuildingName() + "', '" + building.getDescription() + "')", 
                stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()) {
                long buildingID = rs.getLong(1);
                if(facilities != null && !facilities.isEmpty()) {
                    for (Facility facility : facilities) {
                        stmt.executeUpdate("INSERT INTO just_in_case.within(buildingID, facilityID)"
                        + " VALUES ('" + buildingID + "', '" + facility.getFacilityID() + "')");
                    }
                }

                ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.building WHERE buildingID='"+ buildingID + "'");
                if(insertedResultSet.next()) {
                    return getBuildingFromResultSet(insertedResultSet);
                }                
                conn.close();
                return null;
            } else {
                conn.close();
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

    /** Returns id of deleted building. If no building deleted successfully, returns -1. */
    public long deleteBuilding(Building building) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.building WHERE just_in_case.building.buildingID = '" 
                         + building.getBuildingID() + "'", stmt.RETURN_GENERATED_KEYS);
            ResultSet deletedResultSet = stmt.executeQuery(
                "SELECT * FROM just_in_case.building WHERE buildingID='"+ building.getBuildingID() + "'");
            if(deletedResultSet.next()) {
                conn.close();
                return -1;
            }
            conn.close();
            return building.getBuildingID();            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return -1;
    }

    public List<Facility> getFacilitiesByID(long buildingID) {
        List<Facility> facilities = new ArrayList<Facility>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.facility " +  
                "WHERE just_in_case.facility.facilityID = just_in_case.within.facilityID " + 
                "AND just_in_case.within.buildingID = '" + buildingID + "'");
            while(rs.next()) {
                facilities.add(getFacilityFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return facilities;
    }

    public List<Facility> getFacilitiesByName(String buildingName) {
        Building building = getBuildingByName(buildingName);
        List<Facility> facilities = new ArrayList<Facility>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.facility " +  
                "WHERE just_in_case.facility.facilityID = just_in_case.within.facilityID " + 
                "AND just_in_case.within.buildingID = '" + building.getBuildingID() + "'");
            while(rs.next()) {
                facilities.add(getFacilityFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return facilities;
    }

    public Facility addFacility(Facility facility, Building building) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "INSERT INTO just_in_case.facility(facilityName, status, statusLastUpdated)"
                + " VALUES ('" + facility.getFacilityName() + "', '" + facility.getStatus() + "', " 
                + "NOW()" + "')", stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
        
            if(rs.next()) {
                long facilityID = rs.getLong(1);
                stmt.executeUpdate("INSERT INTO just_in_case.within(buildingID, facilityID)"
                + " VALUES ('" + building.getBuildingID() + "', '" + facilityID + "')");

                ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.facility WHERE facilityID='" + facilityID + "'");
                if(insertedResultSet.next()) {
                    return getFacilityFromResultSet(insertedResultSet);
                }                
                conn.close();
                return null;
            } else {
                conn.close();
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

    public Facility addFacilityByID(Facility facility, long buildingID) {
        Building building = getBuildingByID(buildingID);
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "INSERT INTO just_in_case.facility(facilityName, status, statusLastUpdated)"
                + " VALUES ('" + facility.getFacilityName() + "', '" + facility.getStatus() + "', " 
                + "NOW()" + "')", stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
        
            if(rs.next()) {
                long facilityID = rs.getLong(1);
                stmt.executeUpdate("INSERT INTO just_in_case.within(buildingID, facilityID)"
                + " VALUES ('" + building.getBuildingID() + "', '" + facilityID + "')");

                ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.facility WHERE facilityID='" + facilityID + "'");
                if(insertedResultSet.next()) {
                    return getFacilityFromResultSet(insertedResultSet);
                }                
                conn.close();
                return null;
            } else {
                conn.close();
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

    public long deleteFacility(Facility facility) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.facility WHERE just_in_case.facility.facilityID = '" 
                         + facility.getFacilityID() + "'", stmt.RETURN_GENERATED_KEYS);
            ResultSet deletedResultSet = stmt.executeQuery(
                "SELECT * FROM just_in_case.facility WHERE just_in_case.facility.facilityID='"
                + facility.getFacilityID() + "'");
            if(deletedResultSet.next()) {
                conn.close();
                return -1;
            }
            conn.close();
            return facility.getFacilityID();            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
        return -1;
    }

    public Facility updateStatus(Facility facility, Status status) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "UPDATE just_in_case.facility SET status = '" +  facility.getStatus().toString() 
                + "', statusLastUpdated = " + "NOW()" +
                " WHERE just_in_case.facility.facilityID = '" + facility.getFacilityID() + "'",
                stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
        
            if(rs.next()) {
                long facilityID = rs.getLong(1);
                
                ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.facility WHERE facilityID='" + facilityID + "'");
                if(insertedResultSet.next()) {
                    return getFacilityFromResultSet(insertedResultSet);
                }                
                conn.close();
                return null;
            } else {
                conn.close();
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

    public User getUserByID(String caseID) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.app_users WHERE caseID='"+ caseID + "'");
            if(rs.next()) {
                return getUserFromResultSet(rs);
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

    public String getUserByPost(long postID) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT just_in_case.app_users.caseID" 
                + " FROM just_in_case.app_users JOIN just_in_case.posts" 
                + " WHERE just_in_case.app_users.caseID = just_in_case.posts.caseID"
                + " AND just_in_case.posts.postID = '" + postID + "'");
            if(rs.next()) {
                return rs.getString("caseID");
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

    public User addUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "INSERT INTO just_in_case.app_users(caseID, userName, postAnon, isAdmin, password)"
                + " VALUES ('" + user.getCaseID() + "', '" + user.getUserName() 
                + "', '" + user.getPostAnon() + "', '" + user.getIsAdmin()  
                + "', '" + user.getPassword() + "')");
            
            ResultSet insertedResultSet = stmt.executeQuery(
                "SELECT * FROM just_in_case.app_users WHERE caseID='"+ user.getCaseID() + "'");
            if(insertedResultSet.next()) {
                return getUserFromResultSet(insertedResultSet);             
            } else {
                conn.close();
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

    public Boolean deleteUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.app_users WHERE just_in_case.app_users.caseID = '" 
                         + user.getCaseID() + "'", stmt.RETURN_GENERATED_KEYS);
            ResultSet deletedResultSet = stmt.executeQuery(
                "SELECT * FROM just_in_case.app_users WHERE just_in_case.app_users.caseID='"+ user.getCaseID() + "'");
            if(deletedResultSet.next()) {
                conn.close();
                return false;
            }
            conn.close();
            return true;            
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
        User user = new User("DEFAULT", "DEFAULT", 0, 1, "pass");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.app_users WHERE just_in_case.app_users.caseID = 'DEFAULT';");
            if(!rs.next()) {
                stmt.executeUpdate("INSERT INTO just_in_case.app_users(caseID, userName, postAnon, isAdmin)"
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
