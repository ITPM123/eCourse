<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">公告列表</div>
    <a-divider style="margin-bottom:-2px"/>
    <announcementtable/>
    <router-view></router-view>
  </div>
</template>

<script>
import announcementtable from "@/components/tables/announcementtable.vue";
import axios from "axios";
export default {
  name: "announceList",
  components: {
    announcementtable
  }
};
</script>

<style>
</style>
