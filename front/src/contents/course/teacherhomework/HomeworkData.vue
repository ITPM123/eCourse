<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">作业数据</div>
    <homeworkData/>
    <router-view></router-view>
  </div>
</template>

<script>
import homeworkData from "@/components/diagram/homeworkData.vue";
export default {
  name: "dataHomework",
  components: {
    homeworkData
  }
};
</script>

<style>
</style>
