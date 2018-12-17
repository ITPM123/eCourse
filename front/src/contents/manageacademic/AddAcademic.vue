<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">添加教员</div>
    <a-divider/>
    <addacademic/>
    <router-view></router-view>
  </div>
</template>


<script>
import addacademic from "@/components/forms/addacademic.vue";
export default {
  name: "addAcademic",
  data: function() {
    return {};
  },
  components: {
    addacademic
  }
};
</script>