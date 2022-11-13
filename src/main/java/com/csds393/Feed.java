package com.csds393;
import java.util.*;

/* class that stores all posts */
public class Feed {

    private List<LiveAlertPost> alertFeed; //stores posts
    private List<Categorization> category; //stores categories to filter posts

    public Feed(){
        category = new ArrayList<Categorization>();
        category.add(Categorization.LIVE_ALERT);
        alertFeed = new ArrayList<LiveAlertPost>();
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



}