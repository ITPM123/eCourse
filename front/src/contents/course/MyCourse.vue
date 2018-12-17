<template>
  <div>
    <index/>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">我的课程</div>
    <a-divider/>
    <coursecard/>
  </div>
</template>

<script>
import index from "@/components/Carousel/index.vue";
import coursecard from "@/components/lists/coursecard.vue";
import axios from "axios";

export default {
  name: "myCourse",

  components: {
    index,
    coursecard
  },

  methods: {

    /** */
    clickHandle() {
      this.$store.commit("changeContent", "courseInfo");
      // this.$store.commit('selectCourse',courseId)
    }
  }
};
</script>

<style>
</style>
