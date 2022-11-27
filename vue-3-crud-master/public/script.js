document.addEventListener('DOMContentLoaded', () => {

    console.log("IN JAVASCRIPT");

    // let Location = new Java.type(Location);
    // //fill the select options with values from the ENUM Location [NOT WORKING]
    // $(function(){
    //     var $select = $("#location");
    //     for (location in Location.values()){
    //       $select.append($('<option></option>').val(location).html(location));
    //     }
    //   });


    // will include filters for the feed and possibly when choosing a building hub
    // Get the element with id="defaultOpen" and click on it : for some reason doesn't occur when in js file
    // 
    

    // // filter out the posts 
    // function showFeed(e, selection){
    //     console.log("SHOW FEED CAL");
    //     // declare variables
    //     var i, samplePost, filter, buildingPage;

    //     // gets elements with class="samplePost"
    //     samplePost = document.getElementsByClassName("samplePost");
    //     for (i = 0; i < samplePost.length; i++){
    //         if (samplePost[i].id != selection)
    //             samplePost[i].style.display = "none";
    //         else   
    //             samplePost[i].style.display = "block";
    //     }

    //     // get all feed filters (class="filter") and remove class "Active", change style
    //     filter = document.getElementsByClassName("filter");
    //     for (i = 0; i < filter.length; i++) {
    //         filter[i].className = filter[i].className.replace(" active", "");
    //         filter[i].style = "color: grey";
    //     }

    //     // show current tab + add active label to button of tab
    // // document.getElementById(selection).style.display = "block";
    //     e.currentTarget.className += " active";
    //     e.currentTarget.style = "color: black";

    //     /* REMOVING BUILDING PAGE */
    //     // get all building pages (class="buildingPage") and hide them 
    //     buildingPage = document.getElementsByClassName("buildingPage");
    //     for (x = 0; x < buildingPage.length; x++){
    //         buildingPage[x].style.display = "none";
    //     }

    //     // get all building buttons  (class="buildingSelect") and remove style and active status
    //     buildingSelect = document.getElementsByClassName("buildingSelect");
    //     for (x = 0; x < buildingSelect.length; x++){
    //         buildingSelect[x].className = buildingSelect[x].className.replace("active", "");
    //     }
    // }

    // show building hub
    // function showBuilding(b, building){
    //     // declare variables
    //     var x, buildingPage, buildingSelect, alertFeed;

    //     // get all building pages (class="buildingPage") and hide them 
    //     buildingPage = document.getElementsByClassName("buildingPage");
    //     for (x = 0; x < buildingPage.length; x++){
    //         buildingPage[x].style.display = "none";
    //     }

    //     // get all building buttons  (class="buildingSelect") and remove style and active status
    //     buildingSelect = document.getElementsByClassName("buildingSelect");
    //     for (x = 0; x < buildingSelect.length; x++){
    //         buildingSelect[x].className = buildingSelect[x].className.replace("active", "");
    //     }

    //     // show building page and add active label to button of selected building
    //     document.getElementById(building).style.display = "block";
    //     b.currentTarget.className += "active";

    //     /* REMOVING FEED SECTION / STYLING */
    //     // hide all the feed posts
    //     alertFeed = document.getElementsByClassName("samplePost");
    //     for (x = 0; x < alertFeed.length; x++){
    //         alertFeed[x].style.display = "none";
    //     }

    //     // get all feed filters (class="filter") and remove class "Active", change style
    //     filter = document.getElementsByClassName("filter");
    //     for (i = 0; i < filter.length; i++) {
    //         filter[i].className = filter[i].className.replace(" active", "");
    //         filter[i].style = "color: grey";
    //     }
    // }

    // when user wants to create new post, form appears
    document.getElementById("postBtn").onclick = function() {
        console.log("POST BUTTON CLICKED");
    document.getElementById("createPost").style.display = "block";
    }

    // to discared a new post
    document.getElementById("postClose").onclick = function () {
        document.getElementById("createPost").style.display = "none";
    }


    // to submit a new post
    document.getElementById("submit").onclick = function () {
        let postType = document.getElementById("postType").textContent;
        let location = document.getElementById("location").textContent;
        // submit to database!! TODO
        document.getElementById("createPost").style.display = "none";
        const post = document.createElement("div");
        post.id = "home";
        post.class = "samplePost";
        const postContent = document.createTextNode(""+postType +" " + location);
        post.append(postContent);
    
        document.getElementById('postFeed').prepend(post);
        console.log("POSTING");
        showFeed($("#submit").onclick, 'home');
    }
});