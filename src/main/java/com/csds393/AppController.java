package com.csds393;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public static void main(String[] args) {
        SpringApplication.run(AppController.class, args);
    }

    // @DeleteMapping("/feed/{name}") {

    // }
    
}
