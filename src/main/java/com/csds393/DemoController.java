package com.csds393;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.stereotype.Controller;  
@Controller  
public class DemoController 
{  
    ModelAndView modelAndView = new ModelAndView("redirect:/"); 
    Feed feed = new Feed();
    @RequestMapping("/")  
    public String index()
    {  
        // LiveAlertPost test = new LiveAlertPost(PostType.CROWDED, Location.ADELBERT_HALL, new User("User", "Prarthana", "pjg83"));
        // feed.addPost(test);
        // modelAndView.addObject("feed", feed.getFeed());
        System.out.println("entering first mapping");
        //returns to index.html
        return"index";      
    }  

    // when form is submitted (action save)
    @RequestMapping(value="/createPost", method=RequestMethod.POST)  
    public ModelAndView newPost(@ModelAttribute LiveAlertPost post){  
        System.out.println(post.getLocation());
        feed.addPost(post);
        
        modelAndView.setViewName("index");      
        modelAndView.addObject("feed", feed.getFeed());    
       
        return modelAndView;  
        }
}  