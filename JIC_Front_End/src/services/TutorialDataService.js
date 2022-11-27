import http from "../http-common";

class TutorialDataService {
  getFeed() {
    return http.get("/feed");
  }

  createPost(post) {
    return http.post("/feed", post);
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

}

export default new TutorialDataService();
