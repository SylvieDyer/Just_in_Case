<template>
    <form class="popUp" id="createBuilding">
        <h1>New Building <i id="postClose" class="fa fa-close" v-on:click="close()"></i></h1>
     
        <input class="addBuildingInput" type="text" placeholder="Building Name" v-model="newBuilding.name"/><br>
        <textarea class="addBuildingInput" placeholder="Description of the building" v-model="newBuilding.description" rows="10"></textarea>
        <select id="facilities">
            <input type="checkbox" id="studyArea" name="studyArea">
            <label for="studyArea">Study Area</label><br>
            <input type="checkbox" id="food" name="food">
            <label for="food">Food & Drink</label><br>
            <input type="checkbox" id="eatingArea" name="eatingArea">
            <label for="eatingArea">Eating Area</label><br>
            <input type="checkbox" id="emptyRooms" name="emptyRooms">
            <label for="emptyRooms">Empty Classrooms</label><br>
        </select>
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
        facilities : {
            studyArea: "",
            food: "",
            eatingArea: "",
            emptyRooms: "",
        }
    }
    },
    methods: {
        // send info in new post 
        addBuilding() {
            var newBuilding = {
                buildingName: this.newBuilding.name,
                description: this.newBuilding.description,
            };
            alert("adding: "+this.newBuilding.name);
            console.log(newBuilding);
            TutorialDataService.createBuilding(newBuilding)
            .then(response => {
                newBuilding = response.data;
                this.submitted = true;

                // add facilities if applicable
                for (const facility in this.facilities){
                    TutorialDataService.addFacilities(this.newBuilding.id, facility)
                    .then(response => {
//   console.log(`${property}: ${object[property]}`);
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    })
                }
            })
            .catch(e => {
                console.log(e);
            }); 
             this.$emit("add-building");
        },

        close(){ 
            document.getElementById("createBuilding").style.display = "none";
        }
    }
}
</script>