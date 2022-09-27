// will include filters for the feed and possibly when choosing a building hub
// Get the element with id="defaultOpen" and click on it : for some reason doesn't occur when in js file
document.getElementById("defaultOpen").click();

// filter out the posts 
function showFeed(e, selection){
    // declare variables
    var i, samplePost, filter;

    // gets elements with class="samplePost"
    samplePost = document.getElementsByClassName("samplePost");
    for (i = 0; i < samplePost.length; i++){
        samplePost[i].style.display = "none";
    }

    // get all elements with class="filter" and remove class "Active"
    filter = document.getElementsByClassName("filter");
    for (i = 0; i < filter.length; i++) {
        filter[i].className = filter[i].className.replace(" active", "");
    }

    // show current tab + add active label to button of tab
    document.getElementById(selection).style.display = "block";
    e.currentTarget.className += " active";
}