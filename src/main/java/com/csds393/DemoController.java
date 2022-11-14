package com.csds393;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;  
@Controller  
public class DemoController{  
    ModelAndView modelAndView = new ModelAndView("redirect:/"); 
    Feed feed = new Feed();
    @RequestMapping("/")  
    public String index(){  
        System.out.println("entering first mapping");
        modelAndView.addObject("feed", feed.getFeed());
        //returns to index.html
        return"index";      
    }  

    // when form is submitted (action save)
    @RequestMapping(value="/", method=RequestMethod.POST)  
    public ModelAndView newPost(@ModelAttribute LiveAlertPost post){  
        System.out.println(post.getLocation());
        feed.addPost(post);
        
        modelAndView.setViewName("index");      
        modelAndView.addObject("feed", feed.getFeed());    

        return modelAndView;  
    }
   
    @RequestMapping(value="/buildingA", method=RequestMethod.GET)
    public ModelAndView showBuildingPage(@ModelAttribute Building building){
        System.out.println("found building A");
        ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
        List<String> facs = new ArrayList<>();
        facs.add("Study Room A");
        facs.add("Study Room B");
        building = new Building("Building A", "This is building A, blah blah blah blah", facs);
        buildingModelAndView.addObject("building", building);

        return buildingModelAndView;
    }
    // @RequestMapping(value = "/buildingA")
    // public String getBuilding(@ModelAttribute Building building) 
    // {				
    //    System.out.println("hell0???");
    //    List<String> facs = new ArrayList<>();
    //        facs.add("Study Room A");
    //        facs.add("Study Room B");
    //        building = new Building("Building A", "This is building A, blah blah blah blah", facs);
    //     //    buildingModelAndView.addObject("building", building);
    //    return "buildingInfo";		
    // }

   
}  