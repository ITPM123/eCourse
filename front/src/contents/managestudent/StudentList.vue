<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">学生列表</div>
    <a-divider style="margin-bottom:-2px"/>
    <studentlist/>
    <router-view></router-view>
  </div>
</template>

<script>
import studentlist from "@/components/tables/studenttable.vue";
export default {
  name: "studentList",
  components: {
    studentlist
  }
};
</script>

<style>
</style>
