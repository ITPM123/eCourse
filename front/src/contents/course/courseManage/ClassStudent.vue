<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">班级学生列表</div>
    <a-divider/>
    <classstudenttable/>
    <router-view></router-view>
  </div>
</template>

<script>
import classstudenttable from "@/components/tables/classstudenttable.vue";
import axios from "axios";

export default {
  name: "classStudent1",
  components: {
    classstudenttable
  }
  
};
</script>

<style>
</style>
