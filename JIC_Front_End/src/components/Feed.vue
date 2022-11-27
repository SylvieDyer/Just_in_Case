<template>
    <div class="postFeed" id="postFeed">
      <ul style="list-style: none;">
        <li class="samplePost"
          v-for="post in feed.slice().reverse()"
          :key="post.date" 
        >
          {{ post.postID }} <br>
          New Alert! <br>
          Reports of <b>{{ post.postType }}</b> at <b>{{ post.location }}</b> <br>
          <br>
          <p style="text-align: right">posted at: {{parseDate(post.date)}}</p>
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
      TutorialDataService.getFeed()
        .then(response => {
          this.feed = response.data;
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

    parseDate: function(date){
     console.log(typeof(date));
      let y = date.substring(0, 4);
      let m = date.substring(5, 7);
      let d = date.substring(8, 10);
      let t = date.substring(11, 16);
      return m + '/'+ d + '/' + y + ' @' + t;
    }
    
  },
  mounted() {
    this.retrieveLiveAlertPosts();
  }
};
</script>