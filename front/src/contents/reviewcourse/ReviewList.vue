<template>
  <div>
    <div style="font-weight:bold;font-size:2em">未审核列表</div>
    <!-- <a-divider style="margin-bottom:-1px"/> -->
    <reviewtable/>
    <router-view></router-view>
  </div>
</template>

<script>
import reviewtable from "@/components/tables/reviewtable.vue";
import axios from "axios";

export default {
  name: "reviewList",
  components: {
    reviewtable
  }
};
</script>

<style>
</style>
