<template>
    <!--Building Pages-->
    <div style="display: block" class="buildingPage">    
      <h3>Test File to see all buildings</h3>
        <!--Will Hold the Dynamic Statuses-->
       
       <ul style="list-style: none;">
        <li class="samplePost"
          v-for="building in buildings"
          :key="building.buildingName" 
        >        
          {{ building.buildingName }} <br>
          {{ building.description }}
        </li>
      </ul>
    </div>
</template>


<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
  name: "tutorials-list",

  data() {
    return {
      buildings: [],
    //   building: {
    //     buildingName: null,
    //     description: null,
    //   },
    //   currentTutorial: null,
    //   currentIndex: -1,
  
    };
  },
  methods: {
    // pull the live alert posts from the DB
    retrieveBuildings() {
      TutorialDataService.getBuildings()
        .then(response => {
           this.buildings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


    

    refreshList() {
      this.retrieveBuildings();
    //   this.currentTutorial = null;
    //   this.currentIndex = -1;
    },

    
    
  },
  mounted() {
    this.retrieveBuildings();

  }
};
</script>