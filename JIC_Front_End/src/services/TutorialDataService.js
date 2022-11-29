import http from "../http-common";

class TutorialDataService {
  getFeed() {
    return http.get("/feed");
  }

  createPostAnon(post) {
    return http.post("/feed", post);
  }

  createPost(post, user) {
    alert("CREATING public POST ");
    return http.post("/feed", post, user);
  }

  getBuildings(){
    return http.get("/buildinghub");
  }

  getBuildingByName(name) {
    return http.get(`/buildingname/${name}`);
  }

  getBuildingByID(id) {
    return http.get(`/buildingid/${id}`);
  }

  createBuilding(building) {
    return http.post("/buildinghub", building);
  }

  deleteBuilding(building) {
    console.log("ENTERING REMOE");
    return http.delete("/building", building);
  }

  addUser(user){
    return http.post("/user", user);
  }

  checkUser(caseEmail){
    return http.get(`/user/${caseEmail}`);
  }

  isAdmin(caseEmail){
    return http.get(`user/isAdmin/${caseEmail}`);

  }

  checkPassword(caseEmail, password){
    return http.get(`user/${caseEmail}/${password}`);
  }

}

export default new TutorialDataService();
