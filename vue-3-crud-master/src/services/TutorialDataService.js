import http from "../http-common";

class TutorialDataService {
  getAll() {
    console.log("hi, calling getAll()");
    return http.get("/feed");
  }
}

export default new TutorialDataService();
