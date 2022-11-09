package com.csds393;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.csds393.backend.TestForPrar;

 
@RestController
@EnableAutoConfiguration
public class BuildingController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BuildingController.class, args);
        System.out.println("\nTEST FOR PRAR OUTPUT:\n");
        TestForPrar.run();
    }

}
