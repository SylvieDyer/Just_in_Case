<template>
    <form class="create" id="createBuilding">
        <h1>New Building <i id="postClose" class="fa fa-close" v-on:click="close()"></i></h1>
     
        <input class="addBuildingInput" type="text" placeholder="Building Name" v-model="newBuilding.name"/><br>
        <textarea class="addBuildingInput" placeholder="Description of the building" v-model="newBuilding.description" rows="10"></textarea>

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
    }
    },
    methods: {
        // send info in new post 
        addBuilding() {
            var newBuilding = {
                buildingName: this.newBuilding.name,
                description: this.newBuilding.description,
            };
            alert(this.newBuilding.name);
            console.log(newBuilding);
            TutorialDataService.createBuilding(newBuilding)
            .then(response => {
                newBuilding = response.data;
                this.submitted = true;
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