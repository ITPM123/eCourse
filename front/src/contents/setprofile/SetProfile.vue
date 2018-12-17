<template>
  <div>
    <setprofil/>
    <router-view></router-view>
  </div>
</template>

<script>
import setprofil from "@/components/tabs/setprofil.vue";
import axios from "axios";

export default {
  name: "setProfile",
  components: {
    setprofil
  }
};
</script>

<style>
</style>
