<template>
    <div class="logInContainer">
        <div class="topHalf">
            <h3>Just in Case</h3>
        </div>
        <div class="bottomHalf">
            <div id="logInText">
                <p v-if="!this.newUser"> Log In</p>
                <p v-else>Welcome!</p>
            </div>
            <div class="inputContainer">
                <div class="emailContainer">
                    <label for="email"><b>Case Email: </b></label><br>
                    <input id="email" v-model="this.user.caseID" type="text" placeholder="Enter Your Case Email" name="email" required>
                    <div id="emptyEmail" class="hasError">Please enter you case email</div>
                    <div id="wrongEmail" class="hasError">There is no account associated with this email! Try again!</div>
                    <div id="invalidEmail" class="hasError">Invalid Case email! Try again!</div>

                </div>

                <div v-if="this.newUser" class="userName">
                    <label for="uname"><b>User Name: </b></label><br>
                    <input v-model="this.user.userName" type="text" placeholder="Create a Username" name="uname" required>
                </div>

                <div class="passwordContainer">
                    <label for="psw"><b>Password: </b></label><br>
                    <input id="password" v-model="this.user.password" type="password" placeholder="Enter Password" name="psw" required>
                    <div id="emptyPassword" class="hasError">Please enter a password</div>
                    <div id="wrongPassword" class="hasError">Incorrect password! Try again!</div>
                </div>

                <div id="noUser" class="hasError">Unable to log you in right now.<br>Please try again later! </div>

                <button v-if="!this.newUser" v-on:click="newAccount()" type="submit" id="login">Login</button>
                <button v-if="!this.newUser" v-on:click="setNewUser()" id="newUser">New User?</button>
                <button v-if="this.newUser" v-on:click="newAccount()" type="submit" id="createAccount">Create Account</button>
                
            </div>
        </div>
    </div>  


</template>

<script>
import TutorialDataService from '../services/TutorialDataService';

export default {
    name: 'login',
    data() {
        return {
            newUser: false,
            user: {
                caseID: "",
                userName: "",
                isAdmin: 0,
                postAnon: 0,
            },
            caseEmailVerif: new RegExp("^[a-z]{3}[0-9]+@case+.edu"),
        }
    },

    methods: {
        setNewUser(){
            this.newUser = true;
        },

        newAccount(){
            console.log("CALLING NEW ACCOUNT");
           
            let error = false;
           console.log("trying to log in ?");

           // check for a valid case email first
            if (!this.caseEmailVerif.test(this.user.caseID)){
                document.getElementById("invalidEmail").style.display = "block"; 
                error = true;
            }
            else {
                document.getElementById("invalidEmail").style.display = "none";
            }
            // check that there's a password 
            if (this.user.password == ""){
                document.getElementById("emptyPassword").style.display = "block";
                error = true;
            }
            else {
                document.getElementById("emptyPassword").style.display = "none";
            }
        
            if (!error){
                // if a new user 
                if (this.newUser){
                    console.log("new user?");
                    // if valid, add to DB 
                    console.log(this.user);
                    TutorialDataService.addUser(this.user)
                    .then(response => {
                        console.log("ADD USER:");
                        console.log(response.data);
                        this.newUser = false;
                    })
                    .catch(e => {
                        console.log(e);
                    }); 
                }

                // if a returning user 
                else {
                    // check DB for the email inputted 
                    TutorialDataService.checkUser(this.user.caseID)
                    .then(response => {
                    
                        if (response.data == "")
                            document.getElementById("wrongEmail").style.display = "block";

                        else{
                            this.user = response.data;
                            // check password
                            TutorialDataService.checkPassword(this.user.caseID, this.user.password)
                            .then(response => {
                                if (response.data){
                                    this.$emit("logged-in", this.user);
                                }
                                else
                                    document.getElementById("wrongPassword").style.display = "block";
                            })
                            .catch(e =>{
                                console.log(e);
                                document.getElementById("wrongPassword").style.display = "block";
                            })
                        }
                    })
                    .catch(e => {
                        console.log(e);
                        document.getElementById("noUser").style.display = "block";
                    }); 
                }
            }
        }
    }

}

</script>


<style>
   
    .logInContainer {
        position: relative;
        z-index: 11;
        width: 100%;
        height: 100vh;
    }

    .topHalf {
        width: 100%;
        height: 40%;
        background-color:rgba(113, 59, 136, 0.603);
        text-align: center;
        font-family: monospace;
       
        padding: .8rem 0rem .8rem .4rem;
        border-bottom: solid rgb(206, 206, 206);
        vertical-align: bottom;
    }

    .topHalf h3 {
        font-size: 3rem;
        position: absolute;
        bottom: 60%;
        text-align: center;;
        width: 100%;
    }

    .bottomHalf {
        background-color: antiquewhite;
        width: 100%;
        height: 60%;
        align-content: center;
        text-align: center;
    }

    .bottomHalf #logInText{
        font-size: 1.5rem;
        font-weight: bold;
       
    }

    .inputContainer {
        margin: 0 auto;
        align-items: center;
        display: flex;
        justify-content: center;
        flex-direction: column;
        border-style: inset;
        width: 43%;
        height: max-content;
        padding: 10px;
        border-radius: 10px;
        background-color: rgb(228, 210, 194);   
    }
    

    .emailContainer, .passwordContainer, .userName, #login, #createAccount{
       margin-top: 2%;
    }

    .inputContainer input {
        border-radius: 10px;
        padding: .4rem;
        border: solid rgb(193, 193, 193);
    }

    .inputContainer button {
        border-radius: 10px;
        padding: .4rem;
        border: solid rgb(193, 193, 193);
    }

    #newUser {
        border: none;
        background-color: transparent;
    }
    #newUser:hover {
        color:rgb(14, 14, 83);
        font-style: italic;
        /* font-style: underline; */
    }
    
    button:hover{
        opacity: .6;
    }

    .hasError {
        display: none;
        color:red;

    }
</style>
