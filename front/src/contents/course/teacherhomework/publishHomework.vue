<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">发布作业</div>
    <a-divider style="margin-bottom:-2px"/>
    <publishHomework></publishHomework>
    <router-view></router-view>
  </div>
</template>

<script>
//学生作业列表
import publishHomework from "@/components/forms/publishHomework.vue";
import axios from "axios";

export default {
  name: "homeworkPublisher",
  components: {
    publishHomework
  }
};
</script>

<style>
</style>
