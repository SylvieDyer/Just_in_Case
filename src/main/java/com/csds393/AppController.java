package com.csds393;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
@CrossOrigin
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

    @PostMapping(path = "/feed/{id}", consumes = "application/json", produces = "application/json")
    public LiveAlertPost createPostByUserId(@RequestBody LiveAlertPost post, @PathVariable("id") String caseID) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.addLiveAlertPostWithID(post, caseID);
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

    @GetMapping("/buildinghub")
    public List<Building> getBuildingHub() {
        DbUtils dbUtils = new DbUtils();
        return dbUtils.getBuildingHub();
    }

    @GetMapping("/buildingid/{id}")
    public Building getBuildingByID(@PathVariable("id") long id) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getBuildingByID(id);
    }

    @GetMapping("/buildingname/{name}")
    public Building getBuildingByID(@PathVariable("name") String name) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getBuildingByName(name);
    }

    @PostMapping(path = "/buildinghub", consumes = "application/json", produces = "application/json")
    public Building createBuilding(@RequestBody Building building) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.addBuilding(building, null);
    }

    @PostMapping(path = "/building/{id}", consumes = "application/json", produces = "application/json")
    public Building createBuilding(@RequestBody Building post, @PathVariable("id") String buildingID) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.addBuilding(post, null);
    }

    // @DeleteMapping(path = "/building", consumes = "application/json", produces = "application/json")
    // public long deleteBuilding(@RequestBody Building building) {
    //     DbUtils dbUtils = new DbUtils(); 
    //     return dbUtils.deleteBuilding(building);
    // }

    @DeleteMapping(path = "/building", consumes = "application/json", produces = "application/json")
    public long deleteBuilding(@RequestBody String building) {
        DbUtils dbUtils = new DbUtils(); 

        return dbUtils.deleteBuilding(dbUtils.getBuildingByName(building));
    }

    @GetMapping("/facilityById/{id}")
    public List<Facility> getFacilitiesByBuildingID(@PathVariable("id") long id) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getFacilitiesByID(id);
    }

    @GetMapping("/facilityByName/{name}")
    public List<Facility> getFacilitiesByBuildingName(@PathVariable("name") String name) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getFacilitiesByName(name);
    }

    @PostMapping(path = "/facility/{id}", consumes = "application/json", produces = "application/json")
    public Facility createFacilityByBuildingId(@RequestBody Facility facility, @PathVariable("id") long buildingID) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.addFacilityByID(facility, buildingID);
    }

    @DeleteMapping(path = "/facility", consumes = "application/json", produces = "application/json")
    public long deleteFacility(@RequestBody Facility facility) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.deleteFacility(facility);
    }

    @PutMapping(path = "/facility/status/{status}", consumes = "application/json", produces = "application/json")
    public Facility updateStatus(@RequestBody Facility facility,  @PathVariable("status") String status) {
        DbUtils dbUtils = new DbUtils();
        Status statusVal = Status.NOT_BUSY;
            for(Status s : Status.values()) {
                if(status.equals(s.toString())) {
                    statusVal = s;
                }
            } 
        return dbUtils.updateStatus(facility, statusVal);
    }

    @GetMapping("/userPost/{id}")
    public String getCaseIDByPost(@PathVariable("id") long postID) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getUserByPost(postID);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String caseID) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.getUserByID(caseID);
    }

    @GetMapping("/user/isAdmin/{id}")
    public boolean isAdmin(@PathVariable("id") String caseID) {
        DbUtils dbUtils = new DbUtils(); 
        return (dbUtils.getUserByID(caseID).getIsAdmin() % 2 != 0);
    }

    @GetMapping("/user/postAnon/{id}")
    public boolean isAnonPost(@PathVariable("id") String caseID) {
        DbUtils dbUtils = new DbUtils(); 
        return (dbUtils.getUserByID(caseID).getPostAnon() % 2 != 0);
    }

    @GetMapping("/user/{id}/{password}")
    public boolean doesIDMatchPass(@PathVariable("id") String caseID, @PathVariable("password") String password) {
        DbUtils dbUtils = new DbUtils(); 
        return (dbUtils.getUserByID(caseID).getPassword().equals(password));
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public User addUser(@RequestBody User user) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.addUser(user);
    }

    @DeleteMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public boolean deleteUser(@RequestBody User user) {
        DbUtils dbUtils = new DbUtils(); 
        return dbUtils.deleteUser(user);
    }


    public static void main(String[] args) {
        SpringApplication.run(AppController.class, args);
    }

    // @DeleteMapping("/feed/{name}") {

    // }
    
}
