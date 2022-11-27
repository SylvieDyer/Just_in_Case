package com.csds393;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
// @RequestMapping("/api")
public class AppController {

    @GetMapping("/feed")
    public List<LiveAlertPost> getFeed() {
        DbUtils dbUtils = new DbUtils();
        return dbUtils.getFeed();
    }

    @PostMapping(path = "/feed", consumes = "application/json", produces = "application/json")
    public LiveAlertPost createPost(@RequestBody LiveAlertPost post) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.addLiveAlertPost(post, null);
    }

    @DeleteMapping(path = "/feed", consumes = "application/json", produces = "application/json")
    public long deletePost(@RequestBody LiveAlertPost post) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.deleteLiveAlertPost(post);
    }

    @GetMapping("/post/{id}")
    public LiveAlertPost getPostByID(@PathVariable("id") long id) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getPost(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppController.class, args);
    }

    // @DeleteMapping("/feed/{name}") {

    // }
    
}
