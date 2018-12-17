<template>
  <div>
    <index/>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">课程班级</div>
    <a-divider/>
    <studenttable/>
    <router-view></router-view>
  </div>
</template>

<script>
import index from "@/components/Carousel/index.vue";
import studenttable from "@/components/tables/classstudenttable.vue";

export default {
  name: "classStudent",
  components: {
    studenttable
  },
  methods: {}
};
</script>

<style>
</style>
