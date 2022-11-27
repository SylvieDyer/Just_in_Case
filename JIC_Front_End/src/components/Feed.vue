<template>
    <div class="postFeed" id="postFeed">
      <ul style="list-style: none;">
        <li class="samplePost"
          v-for="(post, index) in feed"
          :key="index" 
        >
          {{ post.postID }} <br>
          New Alert! <br>
          Reports of <b>{{ post.postType }}</b> at <b>{{ post.location }}</b> <br>
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
      title: "",
    };
  },
  methods: {
    // pull the live alert posts from the DB
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
    
  },
  mounted() {
    this.retrieveLiveAlertPosts();
  }
};
</script>