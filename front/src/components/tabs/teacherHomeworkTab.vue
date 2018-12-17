<template>
  <div>
    <a-tabs @change="callback" defaultActiveKey="1">
      <a-tab-pane tab="全部作业" key="1">
        <homeworkList/>
      </a-tab-pane>
      <a-tab-pane tab="已截止" key="2">
        <particalHomeworkList/>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
import homeworkList from "@/components/lists/homeworkList.vue";
import particalHomeworkList from "@/components/lists/particalHomeworkList.vue";

export default {
  name: "setprofil",
  data: function() {
    return {};
  },
  methods: {},
  components: {
    homeworkList,
    particalHomeworkList
  }
};
</script>