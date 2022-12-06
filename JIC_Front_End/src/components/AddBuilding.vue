<template>
    <form class="popUp" id="createBuilding">
        <h1>New Building <i id="postClose" class="fa fa-close" v-on:click="close()"></i></h1>
     
        <input class="addBuildingInput" type="text" placeholder="Building Name" v-model="newBuilding.name"/><br>
        <textarea class="addBuildingInput" placeholder="Description of the building" v-model="newBuilding.description" rows="10"></textarea>
 
        <div class="facilityList">
            <input type="checkbox" id="studyArea" name="studyArea" value="Study Area" v-model="this.facilities">
            <label for="studyArea">Study Area</label><br>
            <input type="checkbox" id="food" name="food" value="Food & Drink" v-model="this.facilities">
            <label for="food">Food & Drink</label><br>
            <input type="checkbox" id="eatingArea" name="eatingArea" value="Eating Area" v-model="this.facilities">
            <label for="eatingArea">Eating Area</label><br>
            <input type="checkbox" id="emptyRooms" name="emptyRooms" value="Empty Classrooms" v-model="this.facilities">
            <label for="emptyRooms">Empty Classrooms</label><br>
        </div> 

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
            name: "",
            description: "",
        },
        facilities : [],
    }
    },
    methods: {
        // send info in new post 
        addBuilding() {
            var newBuilding = {
                buildingName: this.newBuilding.name,
                description: this.newBuilding.description,
            };
           
            TutorialDataService.createBuilding(newBuilding)
            .then(response => {
                alert("ADDING BUILDING");
                newBuilding = response.data;
                this.submitted = true;

                // if no facilities, add building 
                if (this.facilities.length === 0){
                    alert("no facilities");
                    // this.$emit("add-building");
                    return;
                }
                
                // add facilities if applicable
                for (let i = 0; i < this.facilities.length; i++){
                    TutorialDataService.addFacilities(this.newBuilding.buildingId, this.facilities[i])
                    .then(response => {
                        alert(this.facilities[i]);

                        console.log(response.data);
                        // this.$emit("add-building");
                    })
                    .catch(e => {
                        alert("FAILED TO ADD FACILITIES ");
                        console.log(e);
                    })
                }
                
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