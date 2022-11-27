import http from "../http-common";

class TutorialDataService {
  getAll() {
    console.log("hi, calling getAll()");
    return http.get("/feed");
  }

  create(post) {
    return http.post("/feed", post);
  }

}

export default new TutorialDataService();
