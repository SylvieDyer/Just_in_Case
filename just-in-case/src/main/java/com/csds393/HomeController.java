package com.csds393;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

 
@RestController
@EnableAutoConfiguration
public class HomeController {
    
    @RequestMapping("/")
    public String test() {
        // should be displying the index.html file
        return "index";
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HomeController.class, args);
        System.out.println("\nRUNNING HOME CONTROLLER:\n");
    }
}
