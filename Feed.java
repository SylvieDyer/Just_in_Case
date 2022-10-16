package Just_in_Case;
import java.util.*;

/* class that stores all posts */
public class Feed {

    private List<LiveAlertPost> alertFeed; //stores posts
    private List<Categorization> category; //stores categories to filter posts

    public Feed(){
        category = new ArrayList<Categorization>();
        category.add(Categorization.LIVE_ALERT);
        alertFeed = parseFeed();
    }

    private ArrayList<LiveAlertPost> parseFeed() {
        //prar do ur database thingy
        return null;
    }

    private boolean addPost(LiveAlertPost p){
        alertFeed.add(p);
        return true; 
        /* I'm sorry I'm not entirely sure how yall wanna do this */
    }

    private void setCategory(List<Categorization> c){
        category = c;
    }

    private List<LiveAlertPost> filterFeed(List<Categorization> c) {
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



}