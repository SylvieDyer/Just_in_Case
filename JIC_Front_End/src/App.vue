<template>
  <!-- <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">bezKoder</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/tutorials" class="nav-link">Tutorials</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Add</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      hello
      <router-view />
    </div>
  </div> -->
  <div id="app">
    <AddBuilding @add-building="addBuildingBtn"/>
    <form class="create" id="createPost">
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
        <button id="addBuilding" v-on:click="addBuilding()">Add Building</button>
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
        <tr class="postFeed" id="postFeed">
          <router-view/>
          <!-- <Building :buildingName=this.selectedBuilding /> -->
        </tr>
        <!--Building Pages-->
    
          <!--Post Button-->
    <button class="postBtn" id="postBtn" v-on:click="createPost()">
        +
    </button>
       
      </div>
      <!--Building Hub Section [RIGHT]-->
      <div class="buildingHub">
        <!--Two Columns of Buttons-->
        <!-- <div class="biRow">
          <div class="biCol" id="leftCol"> 
            OLD WAY TO GET ROUTE FOR EACH BUILDING:
            <router-link :to="'/buildingid/' + this.selectedBuildingID">
              <button  class="buildingSelect" v-on:click="selectBuilding(665)">Building B Using ID</button>
            </router-link>
            <a th:href="@{buildingB}" >
              <button type="submit" class="buildingSelect" >Building A</button>
            </a>
            
            <a th:href="@{buildingE}" >
              <button type="submit" class="buildingSelect" >Building E</button>
            </a>
            <a th:href="@{buildingG}" >
              <button type="submit" class="buildingSelect" >Building G</button>
            </a>
          </div>

          <div class="biCol" id="rightCol">
            <router-link :to="'/buildingname/' + this.selectedBuildingName">
              <button type="submit" class="buildingSelect" v-on:click="selectBuilding('Building C')">Building C Using Name </button>
            </router-link>
            
            <a th:href="@{buildingD}" >
              <button type="submit" class="buildingSelect" >Building D</button>
            </a>
            <a th:href="@{buildingF}" >
              <button type="submit" class="buildingSelect" >Building F</button>
            </a>
            <a th:href="@{buildingH}" >
              <button type="submit" class="buildingSelect" >Building H</button>
            </a>
          </div>
        </div> -->


        <div class="biRow" v-for="(building, index) in buildings" :key="index">
          <router-link :to="'/buildingid/' + building[`buildingID`]" >
              <button  class="buildingSelect">{{ building.buildingName }}</button>
          </router-link>

          <!-- WITH PAIRS-->
        <!-- <div class="biRow" v-for="(buildingPair, index) in buildingPairs" :key="index"> -->
       

          <!-- <router-link :to="'/buildingid/' + buildingPair[0][`buildingID`]" >
              <button  class="buildingSelect">{{ buildingPair[0].buildingName }}</button>
          </router-link> -->

          <!-- <router-link :to="'/buildingid/' + buildingPair[1][`buildingID`]" >
              <button  class="buildingSelect">{{ buildingPair[1].buildingName }}</button>
          </router-link> -->

        </div>
      </div> 
    </div>
  </div>
</template>

<script>
import TutorialDataService from "./services/TutorialDataService";
 import AddBuilding from "./components/AddBuilding.vue"

// import { onMounted } from 'vue'; 
export default {
  name: 'app',
  //selectedBuilding: 'A',
  components: {
    AddBuilding
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
    }
  },
  mounted() {
    this.setButtons();
    // this.$root.$on("new-building", (building) => {
    //   console.log("trying to add; "+ building.name);
    // });

    // let externalScript = document.createElement('script');
    // externalScript.setAttribute('src', './script.js');
    // document.head.appendChild(externalScript); 
  },
  methods: {
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
          console.log("ADDING POST: "+response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },

    selectBuilding(val){
      this.selectedBuildingID = val;
      this.selectedBuildingName = val;
      console.log("ID: " + this.selectedBuildingID);
      console.log("NAME: " + this.selectedBuildingName);

    },
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
      console.log("POST BUTTON CLICKED");
      document.getElementById("createPost").style.display = "block";
    },

    addBuilding() {
      document.getElementById("createBuilding").style.display = "block";
    },
    closePost(){
      document.getElementById("createPost").style.display = "none";
    },
    addBuildingBtn(){
     this.setButtons()
    },

    setButtons(){
      TutorialDataService.getBuildings()
        .then(response => {
          console.log(response.data);
          this.buildings = response.data;
          for (let i = 0; i < response.data.length; i += 2) {
            let chunk = response.data.slice(i, i + 2);
            console.log(chunk);
            this.buildingPairs.push(chunk);
          }
          // this.buildings.forEach(buildingPair => {
          //   // console.log("PAIR:" +buildingPair);
          //   // console.log("index 0: ");
          //   // console.log(buildingPair[0]);

          //   // console.log("index 1: ");
          //   // console.log(buildingPair[1]);


            
          // });
          console.log(this.buildings);
        })

        .catch(e => {
          console.log(e);
        });
    },

  

    // showBuilding(buildingName){
    //   this.$router.push('/buildinghub')
    // }
  },
  
  
}
</script>