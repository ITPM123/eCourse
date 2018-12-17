<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">教师列表</div>
    <a-divider style="margin-bottom:-2px"/>
    <teacherlist/>
    <router-view></router-view>
  </div>
</template>

<script>
import teacherlist from "@/components/tables/teachertable.vue";
export default {
  name: "teacherList",
  components: {
    teacherlist
  }
};
</script>

<style>
</style>
