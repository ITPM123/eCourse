<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">课程公告</div>
    <a-divider style="margin-bottom:-2px"/>
    <courseAnnouncementTable/>
    <!-- <router-view></router-view> -->
  </div>
</template>

<script>
import courseAnnouncementTable from "@/components/tables/courseAnnouncementTable.vue";
import axios from "axios";
export default {
  name: "courseAnnounceList",
  components: {
    courseAnnouncementTable
  }
};
</script>

<style>
</style>
