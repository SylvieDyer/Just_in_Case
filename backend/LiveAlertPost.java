package backend;
import java.sql.Timestamp;
import java.util.*;
import java.sql.*;

public class LiveAlertPost {
    /*class represents an alert post a user can make that will display on live feed */
    private PostType postType;
    private Location location;
    private HashSet<Categorization> categorizations = new HashSet<Categorization>();
    private User user;
    private Timestamp date;
    private Connection conn;

    //Constructor initializes a post with post type, location, user, and date
    public LiveAlertPost(PostType postType, Location location, User user, Timestamp date) {
        this.postType = postType;
        this.location = location;
        this.user = user;
        this.date = date;
        updateCategorization();

        // openConnection();

        // try(Statement stmt = conn.createStatement()) {
        //     stmt.executeUpdate("INSERT INTO just_in_case.building VALUES ('" + 1 + "', '" + buildingName + "', '" + description + "')"); 
        // } catch(SQLException e) {
        //     e.printStackTrace();
        // }
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
