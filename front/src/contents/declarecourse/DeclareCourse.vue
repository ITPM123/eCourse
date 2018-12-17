<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">申报课程</div>
    <a-divider/>
    <declarecourse/>
    <router-view></router-view>
  </div>
</template>

<script>
import declarecourse from "@/components/forms/declarecourse.vue";
import axios from "axios";
export default {
  name: "declareCourse",
  components: {
    declarecourse
  }
};
</script>

<style>
</style>
