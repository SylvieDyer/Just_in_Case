package com.csds393;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.stereotype.Controller;  
@Controller  
public class DemoController 
{  
@RequestMapping("/")  
public String index()
{  
    System.out.println("entering first mapping");
//returns to index.html
return"index";  
}  

// when form is submitted (action save)
@RequestMapping(value="/createPost", method=RequestMethod.POST)  
public ModelAndView newPost(@ModelAttribute LiveAlertPost post){  

    System.out.println(post.getLocation());
    ModelAndView modelAndView = new ModelAndView();  
    modelAndView.setViewName("user-data");      
    modelAndView.addObject("post", post);    
    
    return modelAndView;  
    }
}  