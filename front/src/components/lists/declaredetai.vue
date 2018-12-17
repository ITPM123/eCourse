<template>
  <div>
    <!--申报情况-->
    <a-card v-model="detail" title="审批详情" class="card">
      <div class="block">
        <div class="part">申报编号：{{detail.aid}}</div>
        <div class="part">申报时间：{{detail.applyTime}}</div>
        <div class="part">
          <span>审核状态：</span>
          <span v-if="detail.hasReviewed">
            <a-tag v-if="detail.isBack" color="#E74C3C">驳回</a-tag>
            <a-tag v-else color="#1ABC9C">通过</a-tag>
          </span>
          <a-tag v-else color="#3498DB">审核中</a-tag>
        </div>
      </div>
      <div class="block" v-if="detail.hasReviewed">
        <span class="part">审批时间：{{detail.reviewTime}}</span>
        <div v-if="detail.isBack" style="display:flex;flex-direction:row">
          <span style="min-width:5rem">反馈建议：</span>
          <div>{{detail.feedback}}</div>
        </div>
      </div>
    </a-card>
    <!--申报人信息-->
    <a-card title="申报人信息" class="card">
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">姓名：{{teacherInfo.name}}</span>
          <!-- <a-input v-model="teacherInfo.name" disabled="true"></a-input> -->
        </div>
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">工号：{{teacherInfo.id}}</span>
          <!-- <a-input v-model="teacherInfo.id" disabled="true"></a-input> -->
        </div>
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">学院：{{teacherInfo.institute}}</span>
          <!-- <a-input v-model="teacherInfo.institute" disabled="true"></a-input> -->
        </div>
      </div>
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">职称：{{teacherInfo.rank}}</span>
          <!-- <a-input v-model="teacherInfo.rank" disabled="true"></a-input> -->
        </div>
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">邮箱：{{teacherInfo.email}}</span>
          <!-- <a-input v-model="teacherInfo.email" disabled="true"></a-input> -->
        </div>
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">地址：{{teacherInfo.address}}</span>
          <!-- <a-input v-model="teacherInfo.address" disabled="true"></a-input> -->
        </div>
      </div>
    </a-card>
    <!--课程信息-->
    <a-card title="课程信息" class="card">
      <div style="display:flex;flex-direction:row">
        <div style="display:felx;flex-direction:column;width:70%">
          <div>
            <div class="part">
              <span style="min-width:5rem;">课程名称：</span>
              <div>{{courseInfo.name}}</div>
              <!-- <a-input v-model="courseInfo.name" disabled="true" placeholder="请输入课程名称.."></a-input> -->
            </div>
          </div>
          <div>
            <div class="part">
              <span style="min-width:5rem;">课程概述：</span>
              <div>{{courseInfo.summary}}</div>
              <!-- <a-textarea placeholder="请输入课程概述.." disabled="true" v-model="courseInfo.summary" :autosize="{minRows:4,maxRows:6}"></a-textarea> -->
            </div>
          </div>
        </div>
        <!--头像-->
        <!-- <div class="clearfix" style="margin-left:100px">
                    <img src="../../assets/logo.png"/>
        </div>-->
      </div>
      <div>
        <div class="part">
          <span style="min-width:5rem;">课程大纲：</span>
          <div>{{courseInfo.outline}}</div>
          <!-- <a-textarea placeholder="请输入课程大纲.." disabled="true" v-model="courseInfo.outline" :autosize="{minRows:4,maxRows:6}"></a-textarea> -->
        </div>
      </div>
      <div>
        <div class="part">
          <span style="min-width:5rem;">授课目标</span>
          <div>{{courseInfo.target}}</div>
          <!-- <a-textarea placeholder="请输入授课目标" disabled="true" v-model="courseInfo.target" :autosize="{minRows:4,maxRows:6}"></a-textarea> -->
        </div>
      </div>
    </a-card>
  </div>
</template>

<style>
.card {
  margin: 20px 20px;
}
.part {
  display: flex;
  flex-direction: row;
  margin-right: 2rem;
}

.block {
  display: flex;
  flex-direction: row;
  height: 2rem;
  /* margin-bottom:2rem; */
}
</style>

<script>
var teacherInfo = {
  //共有部分
  name: "张三",
  id: "SCUT0001",
  institute: "软件学院",
  address: "华南理工大学大学城校区",
  email: "scut@outlook.com",
  tags: ["机器学习", "数据挖掘"],
  signature: "生活就像海洋，只有意志坚强的人才能到达彼岸",

  //教师特有
  rank: "副教授"
};

var courseInfo = {
  name: "",
  summary:" ",
  outline: "",
  target: ""
};

var detail = {
  //申报信息
  aid: " ",
  hasReviewed: true,
  applyTime: "",

  //审核信息
  isBack: true,
  feedback:" ",
  reviewer: "",
  reviewTime: ""
};

export default {
  name: "declaredetai",
  data: function() {
    return {
      teacherInfo,
      courseInfo,
      detail
    };
  },
  methods: {}
};
</script>