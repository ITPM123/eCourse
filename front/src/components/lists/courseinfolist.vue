<template>
  <div>
    <a-row :gutter="0">
      <a-col :span="8">
        <a-card title="课程名称" :bordered="false">
          <p>{{courseInfo.name}}</p>
        </a-card>
        <!-- <a-card title="课程概述" :bordered=false>
          <p>{{courseInfo.description}}</p>
        </a-card>-->
        <a-card title="课程概述" :bordered="false">
          <p>{{courseInfo.overview}}</p>
        </a-card>
        <a-card title="课程大纲" :bordered="false">
          <p>{{courseInfo.outline}}</p>
        </a-card>
        <a-card title="授课目标" :bordered="false">
          <p>{{courseInfo.teaching_goal}}</p>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>


<script>
import axiox from "axios";

export default {
  name: "courseinfolist",
  mounted() {
    this.init();
  },

  data: function() {
    return {
      courseInfo: {
        name: " ",
        description: " ",
        overview: " ",
        outline: " ",
        teaching_goal: " "
      }
    };
  },

  methods: {
    init() {
      console.log("课程信息页");
      console.log(this.$store.state.courseInfo);
      this.courseInfo = this.$store.state.courseInfo;
    }
  }
};
</script>