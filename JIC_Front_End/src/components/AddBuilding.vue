<template>
    <form class="popUp" id="createBuilding">
        <h1>New Building <i id="postClose" class="fa fa-close" v-on:click="close()"></i></h1>
     
        <input class="addBuildingInput" type="text" placeholder="Building Name" v-model="newBuilding.buildingName"/><br>
        <textarea class="addBuildingInput" placeholder="Description of the building" v-model="newBuilding.description" rows="10"></textarea>
 
        <div class="facilityList">
            Facilities in Progress<br>
            <input type="checkbox" id="studyArea" name="studyArea" :value="this.studyArea" v-model="this.facilities" disabled>
            <label for="studyArea">Study Area</label><br>
            <input type="checkbox" id="food" name="food" :value="this.food" v-model="this.facilities" disabled>
            <label for="food">Food & Drink</label><br>
            <input type="checkbox" id="eatingArea" name="eatingArea" :value="this.eatingArea" v-model="this.facilities" disabled>
            <label for="eatingArea">Eating Area</label><br>
            <input type="checkbox" id="emptyRooms" name="emptyRooms" :value="this.emptyRooms" v-model="this.facilities" disabled>
            <label for="emptyRooms">Empty Classrooms</label><br>
        </div> 
        <div> {{ this.facilities[0] }}</div>

        <button type="submit" id="submit" v-on:click="addBuilding()">Submit</button>
    </form>
</template>


<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
    name: 'addBuilding',
    data() {
        return {
            newBuilding: {
                id: null,
                buildingName: "",
                description: "",
            },
            facilities : [],
            studyArea: {
                facilityID: null,
                facilityName: "Study Area",
                status: "Empty",
                TimeStamp: new Date().getDate(),
            },
            food: {
                facilityID: null,
                facilityName: "Food & Drink",
                status: "Empty",
                TimeStamp: new Date().getDate(),
            },
            eatingArea: {
                facilityID: null,
                facilityName: "Eating Area",
                status: "Empty",
                TimeStamp: new Date().getDate(),
            },
            emptyRooms: {
                facilityID: null,
                facilityName: "Empty Classrooms",
                status: "Empty",
                TimeStamp: new Date().getDate(),
            },
        }
    },
    methods: {
        // send info in new post 
        addBuilding() {
            // var newBuilding = {
            //     buildingName: this.newBuilding.name,
            //     description: this.newBuilding.description,
            // };
        
            TutorialDataService.createBuilding(this.newBuilding)
            .then(response => {
                this.newBuilding = response.data;

                // // if no facilities, add building 
                // if (this.facilities.length == 0){
                //     alert("no facilities");
                //     return;
                // }
                
                // for (let i = 0; i < this.facilities.length; i++){
                //     TutorialDataService.addFacilities(this.facilities[i], this.newBuilding.buildingId)
                //     .then(response => {
                //         alert("adding facilities");
                //         alert(this.facilities[i]);
                //         console.log(response.data);
                //     })
                //     .catch(e => {
                //         alert("FAILED TO ADD FACILITIES ");
                //         console.log(e);
                //     })
                // }


            })
            .catch(e => {
                alert("FAILED TO ADD BUILDING");
                console.log(e);
            }); 
             
        },

        close(){ 
            document.getElementById("createBuilding").style.display = "none";
        }
    }
}
</script>
<style>

.facilityList, .facilityList label{
    font-size: 1rem;
}

.facilityList input {
    margin-left: 1rem;
    margin-right: .5rem;
    size: 1rem;
}

</style>