<template>
  <div id="app">
    <LogIn id="login" @logged-in="login" />
    <AddBuilding @add-building="setButtons"/>
    <EditBuildings @edit-buildings="setButtons"/>
    <form class="popUp" id="createPost">
      <h1>New Post <i id="postClose" class="fa fa-close" v-on:click="closePost()"></i></h1>
      <div>
          Alert! 
          <select v-model="post.postType" name="postType" id="postType">
              <option value="EXCESSIVE_RAIN">There is excessive rain on </option>
              <option value="EXCESSIVE_SNOW">There is excessive snow on </option>
              <option value="CROWDED">It is crowded at </option>
              <option value="CLOSED">The following location is closed- </option>
          </select>
          <select v-model="post.location" name="location" id="location">
              <option value="ADELBERT_HALL">Adelbert Hall</option>
              <option value="ADELBERT_GYM">Adelbert Gym</option>
              <option value="WOLSTEIN_HALL">Wolstein Hall</option>
              <option value="AMASA_STONE_CHAPEL">Amasa Stone Chapel</option>
              <option value="ART_STUDIO">Art Studio</option>
              <option value="BOOKSTORE">Bookstore</option>
              <option value="BELLFLOWER_HALL">Bellflower Hall</option>
              <option value="Bingham">Bingham</option>
          </select>
        </div>
        <button type="submit" id="submit" v-on:click="newPost()">Submit</button>
    </form>

    <div class="grid">

      <div class="header">
        Just in Case
        <div id="userInfo">
          <button v-on:click="logOut()">Log Out</button>
          {{ this.user.email }}
          {{this.user.admin }}
        </div>
      </div>
          
      <!--Feed/Building Page Section [LEFT]-->
      <div class="mainFeed" id="mainFeed">
        <!--Feed Filter Options-->
        <div class="filters">
          <router-link to="/feed">
            <button class="filter" id="defaultOpen"><h3>Live Feed</h3></button>
          </router-link>
          <button class="filter" onclick="showFeed(event, 'study')">Study Spots</button>
          <button class="filter" onclick="showFeed(event, 'food')">Food</button>
        </div>
        <!-- <div class="postFeed" id="postFeed"> -->
          <router-view/>
          <!-- <Building :buildingName=this.selectedBuilding /> -->
        <!-- </div> -->
        <!--Building Pages-->
    
          <!--Post Button-->
        <button class="postBtn" id="postBtn" v-on:click="createPost()">
            +
        </button>
          
      </div>
      <!--Building Hub Section [RIGHT]-->
      <div class="buildingHub">
        <div class="biRow">
          <div class="biCol" v-for="(building, index) in buildings.slice(0, buildings.length / 2 + 1)" :key="index">
            <!-- <template v-if="index <= buildings.length / 2"> -->
                <router-link :to="'/buildingid/' + building[`buildingID`]" >
                    <button  class="buildingSelect">{{ building.buildingName }}</button>
                </router-link>  
            <!-- </template> -->
          </div>  
          <div class="biCol" v-for="(building, index) in buildings.slice(buildings.length / 2 + 1)" :key="index">
            <!-- <template v-if="index > buildings.length / 2"> -->
                <router-link :to="'/buildingid/' + building[`buildingID`]" >
                    <button  class="buildingSelect">{{ building.buildingName }}</button>
                </router-link>  
            <!-- </template> -->
          </div>  
          <div class="biCol" v-if="buildings.length % 2 != 0"> </div>
        </div>
        <div v-if="this.user.admin" id="buildingEditors">
          <button id="addBuilding" v-on:click="addBuilding()">Add Building</button>
          <button id="removeBuilding" v-on:click="editBuildings()">Edit Buildings</button>
        </div>
      </div> 
    </div>
  </div>
</template>



<script>
import TutorialDataService from "./services/TutorialDataService";
 import AddBuilding from "./components/AddBuilding.vue";
 import EditBuildings from "./components/EditBuildings.vue";
 import LogIn from './components/LogIn.vue';



// import { onMounted } from 'vue'; 
export default {
  name: 'app',
  //selectedBuilding: 'A',
  components: {
    AddBuilding,
    EditBuildings,
    LogIn
  },
  data() {
    return {
      post: {
        location: "ADELBERT_HALL",
        // numDownVotes: 0,
        // numUpVotes: 0,
        // postID: 0,
        postType: "EXCESSIVE_RAIN",
      },
      submitted: false,
      selectedBuildingID: null,
      selectedBuildingName: null,
      buildingPairs: [],
      buildings: [],
      user: {
        caseID: "",
        userName: "",
        passWord: "",
        isAdmin: false,
      },
      loggedIn: false,
    }
  },
  mounted() {
    this.setButtons();
  },
  methods: {
    login(user){
      console.log("login in app called");
      document.getElementById("login").style.display = "none";
      this.user = user;
      this.loggedIn = true;
    
    },

    logOut(){
      this.loggedIn = false;
    },
    // send info in new post 
    newPost() {
      console.log(this.post);
      var newPost = {
        location: this.post.location,
        numDownVotes: this.post.numDownVotes,
        numUpVotes: this.post.numUpVotes,
        postID: this.post.postID,
        postType: this.post.postType,
      };

      TutorialDataService.create(newPost)
        .then(response => {
          this.post.id = response.newPost.id;
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },

    // selectBuilding(val){
    //   this.selectedBuildingID = val;
    //   this.selectedBuildingName = val;
    //   console.log("ID: " + this.selectedBuildingID);
    //   console.log("NAME: " + this.selectedBuildingName);

    // },
    // // filter out the posts 
    // showFeed(e, selection){
    //     console.log("SHOW FEED CAL");
    //     // declare variables
    //     var i, x, samplePost, filter, buildingPage, buildingSelect;

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
    //     // e.currentTarget.className += " active";
    //     // e.currentTarget.style = "color: black";

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
    // },

    // when user wants to create new post, form appears
    createPost() {
      document.getElementById("createPost").style.display = "block";
    },

    closePost(){
      document.getElementById("createPost").style.display = "none";
    },

    addBuilding() {
      document.getElementById("createBuilding").style.display = "block";
    },

    editBuildings() {
      document.getElementById("editBuildings").style.display = "block";
    },
   

    setButtons(){
      console.log("SETTING BUTTONS");
      TutorialDataService.getBuildings()
        .then(response => {
          console.log(response.data);
          this.buildings = response.data;
        })

        .catch(e => {
          console.log(e);
        });
    },
  },
  
  
}
</script>