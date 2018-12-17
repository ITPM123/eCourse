<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">课程信息</div>
    <a-divider/>
    <courseinfolist :coursename="name"/>
    <router-view></router-view>
  </div>
</template>

<script>
import courseinfolist from "@/components/lists/courseinfolist.vue";
export default {
  name: "courseInfo",
  components: {
    courseinfolist
  },
  data() {
    return {
      name: "aaaaaa"
    };
  }
};
</script>

<style>
</style>
