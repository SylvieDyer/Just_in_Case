<template>
    <AddBuilding @add-building="setButtons"/>
    <EditBuildings @edit-buildings="setButtons"/>
    <form class="popUp" id="createPost">
      <h1>New Post <i id="postClose" class="fa fa-close" v-on:click="closePost()"></i></h1>
      <div>
          New Alert!<br><br>
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

        <div class="postAnonContainer">
          <input type="checkBox" v-if="this.postAnon == 0"  v-model="this.anonymousPost" name="postUser" id="postUser"/>
          <input type="checkbox" v-if="this.postAnon == 1" v-model="this.anonymousPost" name="postUser" id="postUser" checked/>
          <label for="postUser">Post Anonymously?</label>
        </div>
        <button type="submit" id="submit" v-on:click="newPost()">Submit</button>
    </form>

    <div class="grid">

      <div class="header">
        <div id="title">Just in Case</div>
        <div id="userInfo">
          <button v-on:click="logOut()">Log Out</button>
          <div>Logged in as: {{ this.caseID }}</div>
        </div>
      </div>
          
      <!--Feed/Building Page Section [LEFT]-->
      <div class="mainFeed" id="mainFeed">
        <!--Feed Filter Options-->
        <div class="filters">
          <!-- <router-link to="/feed"> -->
            <button class="filter" id="defaultOpen" v-on:click="setFeed()"><h3>Live Feed</h3></button>
          <!-- </router-link> -->
          <button class="filter" onclick="showFeed(event, 'study')">Study Spots</button>
          <button class="filter" onclick="showFeed(event, 'food')">Food</button>
        </div>
          <Feed v-if="this.feed"/>
          <Building v-if="this.building" :buildingID="this.selectedBuildingID"/>
        
        <button class="postBtn" id="postBtn" v-on:click="createPost()">
            +
        </button>
          
      </div>
      <!--Building Hub Section [RIGHT]-->
      <div class="buildingHub">
        <div class="biRow">
          <div class="biCol" v-for="(building, index) in buildings.slice(0, buildings.length / 2 + 1)" :key="index">
                <!-- <router-link :to="'/buildingid/' + building[`buildingID`]" > -->
                <!-- <router-link :to="{path: `/buildingid/${building.buildingID}`}" > -->
                    <button  class="buildingSelect" v-on:click="setBuilding(building.buildingID)">{{ building.buildingName }}</button>
                <!-- </router-link>   -->
          </div>  
          <div class="biCol" v-for="(building, index) in buildings.slice(buildings.length / 2 + 1)" :key="index">
                <!-- <router-link :to="'/buildingid/' + building[`buildingID`]" > -->
                <!-- <router-link :to="{path: `/buildingid/${building.buildingID}`}" > -->
                    <button  class="buildingSelect" v-on:click="setBuilding(building.buildingID)">{{ building.buildingName }}</button>
                <!-- </router-link> -->
          </div>  
          <div class="biCol" v-if="buildings.length % 2 != 0"> </div>
        </div>
        <div v-if="this.isAdmin == 1" id="buildingEditors">
          <button id="addBuilding" v-on:click="addBuilding()">Add Building</button>
          <button id="removeBuilding" v-on:click="editBuildings()">Edit Buildings</button>
        </div>
      </div> 
    </div>
</template>



<script>
import TutorialDataService from "../services/TutorialDataService";
import AddBuilding from "./AddBuilding.vue";
import EditBuildings from "./EditBuildings.vue";
import Feed from "./Feed.vue";
import Building from "./Building.vue";

export default {
name: 'home',
props: {
    caseID: String,
    userName: String,
    password: String,
    isAdmin: Number,
    postAnon: Number,
},

components: {
  AddBuilding,
  EditBuildings,
  Feed,
  Building
},
data() {
  return {
    post: {
      location: "ADELBERT_HALL",
      // numDownVotes: 0,
      // numUpVotes: 0,
      postType: "EXCESSIVE_RAIN",
    },
    
    submitted: false,
    selectedBuildingID: null,
    selectedBuildingName: null,
    buildingPairs: [],
    buildings: [],
    anonymousPost: true,
    feed: true,
    building: false,
    
  }
},
mounted() {
// this.getFeed();
  this.setButtons(); 


},
methods: {
  login(user){
    document.getElementById("login").style.display = "none";
    this.user = user;
  },

  setLoggedIn(val){
    this.loggedIn = val;
  },

  logOut(){
    this.loggedIn = false;
    this.$emit("log-out");
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

    // if posting anonymously 
    if (this.anonymousPost){
      TutorialDataService.createPostAnon(newPost)
        .then(response => {
          this.post.id = response.newPost.id;
          this.submitted = true;
          this.feed = true;
        })
        .catch(e => {
          console.log(e);
        });
    }
    else {
      TutorialDataService.createPost(newPost, this.user)
        .then(response => {
          this.post.id = response.newPost.id;
          this.submitted = true;
          this.feed = true;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },

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
  
  setBuilding(buildingID){
    this.feed = false;
    this.selectedBuildingID = buildingID;
     // Removing my-component from the DOM
     this.building = false;

    this.$nextTick(() => {
    // Adding the component back in
    this.building = true;
    });

  },

  setButtons(){
    console.log("SETTING BUTTONS");
    TutorialDataService.getBuildings()
      .then(response => {
        this.buildings = response.data;
      })

      .catch(e => {
        console.log(e);
      });
  },
  setFeed() {
    this.feed = true;
    this.building = false;
    // this.$router.push({path: `/feed/${this.caseID}`, params:{caseID: this.caseID}});
  }

},


}
</script>