<template>
  <div>
    <!--教师个人信息-->
    <a-card v-if="userInfo.isTeacher" title="个人信息" class="card">
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">姓名：</span>
          <a-input placeholder="请输入姓名.." v-model="userInfo.name"></a-input>
        </div>
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">工号：</span>
          <a-input placeholder="请输入工号.." v-model="userInfo.id"></a-input>
        </div>
      </div>
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">职称：</span>
          <a-input placeholder="请输入职称.." v-model="userInfo.rank"></a-input>
        </div>
        <div class="part">
          <span style="min-width:5rem;margin-top:5px">所属学院：</span>
          <a-input placeholder="请输入学院.." v-model="userInfo.institute"></a-input>
        </div>
      </div>
    </a-card>

    <!--学生个人信息-->
    <a-card v-else title="个人信息" class="card">
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">姓名：</span>
          <a-input placeholder="请输入姓名.." v-model="userInfo.name"></a-input>
        </div>
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">学号：</span>
          <a-input placeholder="请输入学号.." v-model="userInfo.id"></a-input>
        </div>
      </div>
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">学院：</span>
          <a-input placeholder="请输入学院.." v-model="userInfo.institute"></a-input>
        </div>
        <div class="part">
          <span style="min-width:5rem;margin-top:5px">专业：</span>
          <a-input placeholder="请输入专业.." v-model="userInfo.marjor"></a-input>
        </div>
      </div>
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">年级：</span>
          <a-input placeholder="请输入学院.." v-model="userInfo.grade"></a-input>
        </div>
        <div class="part">
          <span style="min-width:5rem;margin-top:5px">班级：</span>
          <a-input placeholder="请输入专业.." v-model="userInfo.class"></a-input>
        </div>
      </div>
    </a-card>

    <!--联系方式-->
    <a-card title="联系方式" class="card">
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">地址：</span>
          <a-input placeholder="请输入地址..." v-model="userInfo.address"></a-input>
        </div>
      </div>
      <div class="block">
        <div class="part">
          <span style="min-width:3rem;margin-top:5px">邮箱：</span>
          <a-input placeholder="请输入邮箱..." v-model="userInfo.email"></a-input>
        </div>
      </div>
    </a-card>
    <!--标签-->
    <a-card title="我的标签" class="card">
      <div class="block">
        <div>
          <template v-for="(tag, index) in userInfo.tags">
            <a-tooltip v-if="tag.length > 20" :key="tag" :title="tag">
              <a-tag
                :key="tag"
                :closable="index !== 0"
                :afterClose="() => handleClose(tag)"
              >{{`${tag.slice(0, 20)}...`}}</a-tag>
            </a-tooltip>
            <a-tag
              v-else
              :key="tag"
              :closable="index !== 0"
              :afterClose="() => handleClose(tag)"
            >{{tag}}</a-tag>
          </template>
          <a-input
            v-if="inputVisible"
            ref="input"
            type="text"
            size="small"
            :style="{ width: '78px' }"
            :value="inputValue"
            @change="handleInputChange"
            @blur="handleInputConfirm"
            @keyup.enter="handleInputConfirm"
          />
          <a-tag v-else @click="showInput" style="background: #fff; borderStyle: dashed;">
            <a-icon type="plus"/>New Tag
          </a-tag>
        </div>
      </div>

      <div class="block">
        <div class="part">
          <span style="min-width:5rem;margin-top:5px">个性签名：</span>
          <a-textarea
            placeholder="请输入文本.."
            :autosize="{minRows:2,maxRows:4}"
            v-model="userInfo.signature"
          />
        </div>
      </div>
    </a-card>
    <a-button style="margin-left:20px" type="primary" :size="large">修改</a-button>
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
  margin-bottom: 2rem;
}
</style>

<script>
var userInfo = {
  //共有部分
  isTeacher: true,
  name: "张三",
  id: "SCUT0001",
  institute: "软件学院",
  address: "华南理工大学大学城校区",
  email: "scut@outlook.com",
  tags: ["机器学习", "数据挖掘"],
  signature: "生活就像海洋，只有意志坚强的人才能到达彼岸",

  //教师特有
  rank: "副教授",

  //学生特有
  major: "软件工程",
  grade: "2016",
  class: "2"
};

export default {
  name: "BasicSetting",
  data: function() {
    return {
      userInfo,
      inputVisible: false,
      inputValue: ""
    };
  },
  methods: {
    //与标签相关的操作
    handleClose(removedTag) {
      const tags = this.userInfo.tags.filter(tag => tag !== removedTag);
      //console.log(tags)
      this.userInfo.tags = tags;
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(function() {
        this.$refs.input.focus();
      });
    },

    handleInputChange(e) {
      this.inputValue = e.target.value;
    },

    handleInputConfirm() {
      const inputValue = this.inputValue;
      let tags = this.userInfo.tags;
      if (inputValue && tags.indexOf(inputValue) === -1) {
        tags = [...tags, inputValue];
      }
      //console.log(tags)
      Object.assign(this, {
        inputVisible: false,
        inputValue: ""
      });
      userInfo.tags = tags;
    }

    //其他操作
  }
};
</script>