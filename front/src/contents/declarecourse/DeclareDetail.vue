<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">申报信息</div>
    <a-divider/>
    <declaredetai/>
    <router-view></router-view>
  </div>
</template>

<script>
import declaredetai from "@/components/lists/declaredetai.vue";
export default {
  name: "declareDetail",
  components: {
    declaredetai
  }
};
</script>

<style>
</style>
