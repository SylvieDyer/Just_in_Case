<template>
    <div class="postFeed" id="postFeed">
        <div id="home" class="samplePost">This is a sample general live alert at a sample location</div>
        <h4>Tutorials List</h4>
      <ul class="postFeed" id="postFeed">
        <li class="samplePost"
          v-for="(post, index) in feed"
          :key="index"
        >
          {{ post.postID }} <br>
          {{ post.postType }} <br>
          {{ post.location }}<br>
        </li>
      </ul>
    </div>
</template>

<script>
import TutorialDataService from "../services/TutorialDataService";

export default {
  name: "tutorials-list",
  data() {
    return {
      feed: [],
      currentTutorial: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveLiveAlertPosts() {
      TutorialDataService.getAll()
        .then(response => {
          this.feed = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveLiveAlertPosts();
      this.currentTutorial = null;
      this.currentIndex = -1;
    },

    setActiveTutorial(tutorial, index) {
      this.currentTutorial = tutorial;
      this.currentIndex = tutorial ? index : -1;
    },

    removeAllTutorials() {
      TutorialDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    
  },
  mounted() {
    this.retrieveLiveAlertPosts();
  }
};
</script>