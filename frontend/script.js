
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
document.getElementById("defaultOpen").click();

// filter out the posts 
function showFeed(e, selection){
    // declare variables
    var i, samplePost, filter, buildingPage;

    // gets elements with class="samplePost"
    samplePost = document.getElementsByClassName("samplePost");
    for (i = 0; i < samplePost.length; i++){
        samplePost[i].style.display = "none";
    }

    // get all feed filters (class="filter") and remove class "Active", change style
    filter = document.getElementsByClassName("filter");
    for (i = 0; i < filter.length; i++) {
        filter[i].className = filter[i].className.replace(" active", "");
        filter[i].style = "color: grey";
    }

    // show current tab + add active label to button of tab
    document.getElementById(selection).style.display = "block";
    e.currentTarget.className += " active";
    e.currentTarget.style = "color: black";

     /* REMOVING BUILDING PAGE */
    // get all building pages (class="buildingPage") and hide them 
    buildingPage = document.getElementsByClassName("buildingPage");
    for (x = 0; x < buildingPage.length; x++){
        buildingPage[x].style.display = "none";
    }

    // get all building buttons  (class="buildingSelect") and remove style and active status
    buildingSelect = document.getElementsByClassName("buildingSelect");
    for (x = 0; x < buildingSelect.length; x++){
        buildingSelect[x].className = buildingSelect[x].className.replace("active", "");
    }
}

// show building hub
function showBuilding(b, building){
    // declare variables
    var x, buildingPage, buildingSelect, alertFeed;

    // get all building pages (class="buildingPage") and hide them 
    buildingPage = document.getElementsByClassName("buildingPage");
    for (x = 0; x < buildingPage.length; x++){
        buildingPage[x].style.display = "none";
    }

    // get all building buttons  (class="buildingSelect") and remove style and active status
    buildingSelect = document.getElementsByClassName("buildingSelect");
    for (x = 0; x < buildingSelect.length; x++){
        buildingSelect[x].className = buildingSelect[x].className.replace("active", "");
    }

    // show building page and add active label to button of selected building
    document.getElementById(building).style.display = "block";
    b.currentTarget.className += "active";

    /* REMOVING FEED SECTION / STYLING */
    // hide all the feed posts
    alertFeed = document.getElementsByClassName("samplePost");
    for (x = 0; x < alertFeed.length; x++){
        alertFeed[x].style.display = "none";
    }

    // get all feed filters (class="filter") and remove class "Active", change style
    filter = document.getElementsByClassName("filter");
    for (i = 0; i < filter.length; i++) {
        filter[i].className = filter[i].className.replace(" active", "");
        filter[i].style = "color: grey";
    }
}

// when user wants to create new post, form appears
document.getElementById("postBtn").onclick = function() {
  document.getElementById("createPost").style.display = "block";
}

// to discared a new post
document.getElementById("postClose").onclick = function () {
    document.getElementById("createPost").style.display = "none";
}


// to submit a new post
document.getElementById("submit").onclick = function () {
    let postType = document.getElementById("postType").value;
    let location = document.getElementById("location").value;
    // submit to database!! TODO
    document.getElementById("createPost").style.display = "none";
   
    $('.postFeed').prepend("<div id='home' class='samplePost' style='display: block'>hello</div>");
    showFeed($("#submit").onclick, 'home');
    //$("#mainFeed").append($('<div id="home" class="samplePost">postType location</div>'));
}


