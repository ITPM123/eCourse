<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">添加学生</div>
    <a-divider/>
    <addstudent/>
    <router-view></router-view>
  </div>
</template>


<script>
import addstudent from "@/components/forms/addstudent.vue";
import axios from "axios";

export default {
  name: "addStudent",
  data: function() {
    return {};
  },
  components: {
    addstudent
  }
};
</script>
