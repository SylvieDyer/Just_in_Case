// package com.csds393;  
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;  
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.ModelAndView;

// import java.io.Console;
// import java.util.ArrayList;
// import java.util.List;

// import org.apache.maven.model.Model;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.stereotype.Controller;  

// @RestController
// @SpringBootApplication
// public class DemoController{ 
//     Feed feed = new Feed();
//     public static void main(String[] args) {
//         SpringApplication.run(DemoController.class, args);
//     }

//     @RequestMapping(value="/")
//     String home(Model model) {
//         // System.out.println(feed.getFeed());
//         // modelAndView.addObject("feed", feed.getFeed());
//         return "Hello World";
//     }
//     // ModelAndView modelAndView = new ModelAndView("redirect:/"); 
    
//     // BuildingHub bh = new BuildingHub();

//     // /* fake buildings */
//     // List<String> facilities = new ArrayList<String>(){
//     //     {
//     //         add("Study Room A");
//     //         add("Study Room B");
//     //     }
//     // };
//     // Building A = new Building("Building A", "This is building A, A is for Apple and Anaconda.", facilities);
    
//     // Building B = new Building("Building B", "This is building B, B is for Banana and Balls", facilities);
    
//     // Building C = new Building("Building C", "This is building C, C is for Candy and Carrots", facilities);

//     // Building D = new Building("Building D", "This is building D, D is for Doorknob and Doom", facilities);

//     // Building E = new Building("Building E", "This is building E, E is for Elephant and Energy", facilities);

//     // Building F = new Building("Building F", "This is building F, F is for Flamingo and Flare", facilities);

//     // Building G = new Building("Building G", "This is building G, G is for Grape and Green", facilities);

//     // Building H = new Building("Building H", "This is building H, H is for Hell and Handy", facilities);

//     // @RequestMapping("/")  
//     // public ModelAndView index(){  
//     //     System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//     //     System.out.println("entering first mapping");
//     //     modelAndView.addObject("feed", feed.getFeed());
//     //     modelAndView.setViewName("index");
//     //     //returns to index.html
//     //     return modelAndView;      
//     // }  

//     // // when form is submitted (action save)
//     // @RequestMapping(value="/", method=RequestMethod.POST)  
//     // public ModelAndView newPost(@ModelAttribute LiveAlertPost post){  
//     //     System.out.println(post.getLocation());
//     //     feed.addPost(post);
        
//     //     modelAndView.setViewName("index");      
//     //     modelAndView.addObject("feed", feed.getFeed());    

//     //     return modelAndView;  
//     // }
   
//     // @RequestMapping(value="/buildingA", method=RequestMethod.GET)
//     // public ModelAndView showBuildingA(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = A;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }

//     // @RequestMapping(value="/buildingB", method=RequestMethod.GET)
//     // public ModelAndView showBuildingB(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = B;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }

//     // @RequestMapping(value="/buildingC", method=RequestMethod.GET)
//     // public ModelAndView showBuildingC(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = C;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }

//     // @RequestMapping(value="/buildingE", method=RequestMethod.GET)
//     // public ModelAndView showBuildingE(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = E;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }

//     // @RequestMapping(value="/buildingD", method=RequestMethod.GET)
//     // public ModelAndView showBuildingD(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = D;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }
//     // @RequestMapping(value="/buildingF", method=RequestMethod.GET)
//     // public ModelAndView showBuildingF(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = F;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }
//     // @RequestMapping(value="/buildingG", method=RequestMethod.GET)
//     // public ModelAndView showBuildingG(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = G;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }

//     // @RequestMapping(value="/buildingH", method=RequestMethod.GET)
//     // public ModelAndView showBuildingPage(@ModelAttribute Building building){
//     //     ModelAndView buildingModelAndView = new ModelAndView("buildingInfo"); 
//     //     building = H;
//     //     buildingModelAndView.addObject("building", building);
//     //     return buildingModelAndView;
//     // }
//     // @RequestMapping(value = "/buildingA")
//     // public String getBuilding(@ModelAttribute Building building) 
//     // {				
//     //    System.out.println("hell0???");
//     //    List<String> facs = new ArrayList<>();
//     //        facs.add("Study Room A");
//     //        facs.add("Study Room B");
//     //        building = new Building("Building A", "This is building A, blah blah blah blah", facs);
//     //     //    buildingModelAndView.addObject("building", building);
//     //    return "buildingInfo";		
//     // }



//     /////////////////////////////////////////////////////////////////////////////////////
//     // @RequestMapping(value="/building/get/{name}") 
//     // public String getBuildingByName(@PathVariable(value = "name") String name) {
//     //     Building building = bh.getBuildingByName(name);
//     //     return building.toString();
//     // }

//     // @RequestMapping(value="/building/post")
//     // public boolean postBuilding(Building b) {
//     //     return bh.addBuilding(b);
//     // }

// }  