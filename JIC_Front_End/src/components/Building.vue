<template>
    <!--Building Pages-->
    <div style="display: block" class="buildingPage">    
        <h3>{{ this.building.buildingName }}</h3>
        <br>
        <br>
        <p>{{ this.building.description }}</p>
        <!--Will Hold the Dynamic Statuses-->
    </div>
</template>


<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
  name: "tutorials-list",
  props: {
    buildingID: Number,
  },
  data() {
    return {
      building: {
        buildingName: null,
        description: null,
        facilities: [],
      },
      routeParam: 0,
    };
  },
  methods: {
    changeRouteParam(){
      if (this.$route.params.id != this.routeParam){
        this.getBuildingByID(this.$route.params.id);
      }
    },
    // pull from DB by ID
    getBuildingByID(id) {
        console.log("GETTING BUILDING BY ID..: "+ id);
      TutorialDataService.getBuildingByID(id)
        .then(response => {
          console.log(response.data);
            this.building.buildingName = response.data.buildingName;
            this.building.description = response.data.description;

            console.log(id);
            // getting the facilities
           TutorialDataService.getFacilities(id)
           .then(response => {
            console.log("got facilities: ");
            console.log(response.data);
              this.building.facilities = response.data;
           })
           .catch(e => {
            console.log(e);
           })
        })
        .catch(e => {
          console.log(e);
        });
    },

    // getBuildingByName(name){
    //     console.log("GETTING BUILDING BY NAME..: "+ name);
    //   TutorialDataService.getBuildingByName(name)
    //     .then(response => {
    //         this.building.buildingName = response.data.buildingName;
    //         this.building.description = response.data.description;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },

    // get building
    // getBuilding(){
    //     this.buildings.forEach(building => {
    //         if (building.buildingName == this.buildingName){
    //             this.building = building;
    //         }
    //     });
    // },

    refreshList() {
      this.retrieveBuildings();
    //   this.currentTutorial = null;
    //   this.currentIndex = -1;
    },

    
    
  },
  mounted() {
    // this.getBuildingByID(this.$route.params.id);
    this.getBuildingByID(this.buildingID);
  }
};
</script>