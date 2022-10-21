package backend;
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

    public ArrayList<LiveAlertPost> parseFeed() {
        //prar do ur database thingy - what is this?????
        return null;
    }

    public boolean addPost(LiveAlertPost p){
        alertFeed.add(p);
        return true; 
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



}