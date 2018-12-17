<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">课程作业</div>
    <a-divider/>
    <teacherHomeworkTab></teacherHomeworkTab>
    <router-view></router-view>
  </div>
</template>

<script>
//学生作业列表
//import homeworktable from  '@/components/tables/homeworktable.vue'
import teacherHomeworkTab from "@/components/tabs/teacherHomeworkTab.vue";
import axios from "axios";

export default {
  name: "HomeworkList",
  components: {
    teacherHomeworkTab
  }
};
</script>

<style>
</style>
