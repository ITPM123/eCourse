<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-top:20px;margin-left:20px">审核详情</div>
    <!-- <a-card v-model="detail" title="审批详情" class="card">
      <div class="block">
        <div class="part">审批人：{{detail.reviewer}}</div>
        <div class="part">申报编号：{{detail.aid}}</div>
        <div class="part">
          <span>审核状态：</span>
          <a-tag v-if="detail.isBack" color="#E74C3C">驳回</a-tag>
          <a-tag v-else color="#1ABC9C">通过</a-tag>
        </div>
      </div>
      <div class="block">
        <div class="part">审批时间：{{detail.reviewTime}}</div>
        <div class="part">申报时间：{{detail.applyTime}}</div>
      </div>
    </a-card> -->

    <!-- <a-card v-model="applicant" title="申报人信息" class="card">
      <div class="block">
        <div class="part">申报人：{{applicant.name}}</div>
        <div class="part">工号：{{applicant.id}}</div>
        <div class="part">职称：{{applicant.rank}}</div>
      </div>
      <div class="block">
        <div class="part">所属学院：{{applicant.institute}}</div>
        <div class="part">工作邮箱：{{applicant.email}}</div>
        <div class="part">联系地址：{{applicant.address}}</div>
      </div>
    </a-card> -->

    <a-card v-model="course" title="课程信息" class="card">
      <div style="min-width:20rem;min-height:3rem">课程名称：{{course.name}}</div>
      <div class="block">
        <div style="min-width:5rem">课程概述：</div>
        <div>{{course.overview}}</div>
      </div>
      <div class="block">
        <div style="min-width:5rem">课程大纲：</div>
        <div>{{course.outline}}</div>
      </div>
      <div class="block">
        <div style="min-width:5rem">授课对象</div>
        <div>{{course.teaching_goal}}</div>
      </div>
    </a-card>

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
</style>

<script>
export default {
  name: "ReviewResult",
  data: function() {
    return {
      // applicant:{
      //   p
      // },
      course:{
        name:null,
        overview:null,
        outline:null,
        teaching_goal:null
      },
      detail:{
        feedback:null,
        result:null,
      },
      title
    };
  },
  mounted(){
    this.init();
  },
  methods: {
    init(){
      let that = this
      let param= new FormData();
      param.append("review_id",that.$store.state. reviewResult.review_id)
      axios({
        url: "/reviewCourse/getReview",
        method: "post",
        data: param
      })
      .then(response=>{
        console.log(response)
        that.course=response.data.course
        that.detail=response.data
      })
    }
  }
};
</script>
