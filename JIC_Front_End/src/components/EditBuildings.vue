<template>
    <form class="popUp" id="editBuildings">
        <h1>Remove Buildings<i id="postClose" class="fa fa-close" v-on:click="close()"></i></h1>
        <fieldset>
            <legend>Select the buildings you'd like to remove</legend>
            
            <div class="removeList" v-for="(building, index) in allBuildings" :key="index">
                <input class="checkOption" type="checkbox" :id=building.buildingName :name=building.buildingName>
                <label :for=building.buildingName>{{ building.buildingName }}</label>
            </div>
        </fieldset>
        <button type="submit" id="submit" v-on:click="editBuilding()">Submit</button>
    </form>
</template>


<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
    name: 'editBuilding',
    data() {
    return {
       allBuildings: [],
       removeBuildings: [],
    }
    },
    methods: {
        // pull the live alert posts from the DB
        retrieveBuildings() {
        TutorialDataService.getBuildings()
            .then(response => {
            this.allBuildings = response.data;
            console.log(response.data);
            console.log("ALL: "+this.allBuildings);
            })
            .catch(e => {
            console.log(e);
            });
        },

        editBuilding() {
            let checkOptions = document.getElementsByClassName("checkOption");
            for (let i = 0; i < checkOptions.length; i++){
                if (checkOptions[i].checked == true){
                    TutorialDataService.removeBuilding(checkOptions[i].name)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    }); 
                }
            }
            if (checkOptions.length > 0)
                this.$emit("edit-buildings");
        },

        close(){ 
            document.getElementById("editBuildings").style.display = "none";

        }
    },
    mounted() {
        this.retrieveBuildings();
    }
}
</script>

<style>
.removeList, .removeList label{
    font-size: 1rem;
}

.removeList input {
    margin-left: 1rem;
    margin-right: .5rem;
    size: 1rem;

}



</style>