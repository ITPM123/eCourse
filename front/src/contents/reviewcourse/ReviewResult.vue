<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">审核详情</div>
    <a-divider/>
    <reviewresult/>
    <router-view></router-view>
  </div>
</template>

<script>
import reviewresult from "@/components/lists/reviewresult.vue";
export default {
  name: "reviewResulter",
  components: {
    reviewresult
  },
  methods: {}
};
</script>

<style>
</style>
