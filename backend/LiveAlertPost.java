package Just_in_Case;
import java.sql.Timestamp;
import java.util.*;
public class LiveAlertPost {
    /*class represents an alert post a user can make that will display on live feed */
    private PostType postType;
    private Location location;
    private HashSet<Categorization> categorizations;
    private User user;
    private Timestamp date;

    //Constructor initializes a post with post type, location, user, and date
    public LiveAlertPost(PostType postType, Location location, User user, Timestamp date) {
        this.postType = postType;
        this.location = location;
        this.user = user;
        this.date = date;
        updateCategorization();
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


}
