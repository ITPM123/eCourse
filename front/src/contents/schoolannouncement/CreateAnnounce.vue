<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">发布公告</div>
    <a-divider style="margin-bottom:-2px"/>
    <createannounce/>
    <router-view></router-view>
  </div>
</template>

<script>
import createannounce from "@/components/forms/createannounce";

export default {
  name: "createAnnounce",
  components: {
    createannounce
  }
};
</script>

<style>
</style>
