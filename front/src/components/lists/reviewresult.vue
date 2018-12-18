<template>
  <div>
     <a-row :gutter="0">
      <a-col :span="4">
        <a-card title="课程名称" :bordered="false" class="card1">
          <p>{{course.name}}</p>
        </a-card>
     
        <a-card title="课程概述" :bordered="false" class="card1">
          <p>{{course.overview}}</p>
        </a-card>
        <a-card title="课程大纲" :bordered="false"  class="card1">
          <p>{{course.outline}}</p>
        </a-card>
        <a-card title="授课目标" :bordered="false"  class="card1">
          <p>{{course.teaching_goal}}</p>
        </a-card>
      </a-col>
    </a-row>

    <a-card v-model="course" title="审核详情" class="card">
      <div class="block">
        <div style="min-width:5rem">审核结果：</div>
        <div>{{detail.result}}</div>
      </div>
      <div class="block">
        <div style="min-width:5rem">审核意见：</div>
        <div>{{detail.feedback}}</div>
      </div>
    </a-card>
  </div>
</template>
<style>
.card {
  margin: 50px 20px;
}
.part {
  min-width: 15rem;
}
.block {
  display: flex;
  flex-direction: row;
  margin-bottom: 2rem;
}
.card1{
  min-width: 1600px;
}
</style>

<script>
import axios from "axios";

export default {
  name: "ReviewResult",
  data: function() {
    return {
      course: {
        name: null,
        overview: null,
        outline: null,
        teaching_goal: null
      },
      detail: {
        feedback: null,
        result: null
      }
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      let that = this;
      let param = new FormData();
      param.append("review_id", that.$store.state.reviewResult);
      axios({
        url: "/reviewCourse/getReview",
        method: "post",
        data: param
      }).then(response => {
        console.log(response);
        that.course = response.data.course;
        that.detail = response.data;
      });
    }
  }
};
</script>
